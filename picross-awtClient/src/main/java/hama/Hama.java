package hama;

import java.util.Set;

import com.renaud.picross.colorResolver.DistanceResolver;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;

public class Hama {

	private static DistanceResolver distance = new DistanceSimple();
	private final static Set<Couleur> model = Nuancier.getCouleurs();

	public static PixelTable compute(PixelTable tableIn) {
		PixelTable tableOut = new PixelTable(tableIn.getLargeur(), tableIn.getHauteur());
		for (int i = 0; i < tableIn.getHauteur(); i++) {
			for (int j = 0; j < tableIn.getLargeur(); j++) {
				Couleur colorIn = tableIn.getPixel(j, i);
				tableOut.setPixel(solve(colorIn), j, i);
			}
		}
		return tableOut;
	}

	private static Couleur solve(Couleur in) {
		double normal = 9999999;
		Couleur who = Couleur.BLANC;
		for (Couleur m : model) {
			if (in.equals(m)) {
				return m;
			}
			else
				if (!in.equals(m) && in.getAlpha() > 0) {
					double nor = distance.getdistance(in, m);
					if (nor < normal) {
						who = m;
						normal = nor;
					}

				}
		}
		return who;
	}

}
