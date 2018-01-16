package hama.colorReducer;

import java.util.List;

import com.renaud.picross.colorResolver.DistanceResolver;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.model.Couleur;

public class ComplexeReducer implements ColorReducer {

	private DistanceResolver distance = new DistanceSimple();

	@Override
	public Couleur reduce(List<Couleur> couleurs) {
		while (couleurs.size() > 1) {
			Couleur ref = couleurs.get(0);
			double maxDist = Double.MAX_VALUE;
			Couleur candidat = null;
			for (int i = 1; i < couleurs.size(); i++) {
				double d = distance.getdistance(ref, couleurs.get(i));
				if (d < maxDist) {
					candidat = couleurs.get(i);
					maxDist = d;
				}
			}
			couleurs.remove(candidat);
			couleurs.remove(ref);
			couleurs.add(moy(candidat, ref));
		}

		return couleurs.size() > 0 ? couleurs.get(0) : Couleur.TRANSPARENT;
	}

	private Couleur moy(Couleur a, Couleur b) {
		int r = a.getR();
		r += b.getR();
		r /= 2;

		int g = a.getG();
		g += b.getG();
		g /= 2;

		int bb = a.getB();
		bb += b.getB();
		bb /= 2;

		int alpha = a.getAlpha() > 0 || b.getAlpha() > 0 ? 255 : 0;

		return new Couleur(r, g, bb, alpha);
	}

}
