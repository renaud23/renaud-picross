package hama;

import java.util.Set;

import com.renaud.picross.colorResolver.DistanceResolver;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;

public class HamaGenerator {

	private static DistanceResolver distance = new DistanceSimple();

	public static Hama compute(PixelTable tableIn, Set<Perle> nuancier) {
		// PixelTable tableOut = new PixelTable(tableIn.getLargeur(), tableIn.getHauteur());
		Hama hama = new Hama(tableIn.getLargeur(), tableIn.getHauteur());
		for (int i = 0; i < tableIn.getHauteur(); i++) {
			for (int j = 0; j < tableIn.getLargeur(); j++) {
				Couleur colorIn = tableIn.getPixel(j, i);
				Perle perle = solve(colorIn, nuancier);
				hama.setPerle(perle, j, i);
			}
		}
		return hama;
	}

	private static Perle solve(Couleur in, Set<Perle> nuancier) {
		double normal = 9999999;
		Perle who = Perle.BLANC;
		for (Perle m : nuancier) {
			if (in.getAlpha() == 0) {
				return Perle.BLANC;
			}
			else
				if (in.equals(m.getCouleur())) {
					return m;
				}
				else
					if (!in.equals(m)) {
						double nor = distance.getdistance(in, m.getCouleur());
						if (nor < normal) {
							who = m;
							normal = nor;
						}

					}
		}
		return who;
	}

}
