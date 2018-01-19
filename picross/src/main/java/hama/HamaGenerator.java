package hama;

import java.util.List;

import com.renaud.picross.colorResolver.DistanceMixte;
import com.renaud.picross.colorResolver.DistanceResolver;
import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;

public class HamaGenerator {

	private static DistanceResolver distance = new DistanceMixte();

	public static Hama compute(PixelTable tableIn, List<Perle> nuancier) {
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

	private static Perle solve(Couleur in, List<Perle> nuancier) {
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
