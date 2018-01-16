package hama.colorReducer;

import java.util.List;

import com.renaud.picross.model.Couleur;

public class MoyenneReducer implements ColorReducer {

	@Override
	public Couleur reduce(List<Couleur> couleurs) {
		double redTotal = 0, greenTotal = 0, blueTotal = 0, alphaTotal = 0;
		for (Couleur c : couleurs) {
			redTotal += c.getR();
			greenTotal += c.getG();
			blueTotal += c.getB();
			alphaTotal += c.getAlpha();
		}
		redTotal /= couleurs.size();
		greenTotal /= couleurs.size();
		blueTotal /= couleurs.size();
		alphaTotal /= couleurs.size();

		if (alphaTotal > 125) {
			alphaTotal = 255;
		}
		else {
			alphaTotal = 0;
		}
		return new Couleur((int) redTotal, (int) greenTotal, (int) blueTotal, (int) alphaTotal);
	}

}
