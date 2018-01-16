package hama;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;
import com.renaud.picross.view.AWTPixelReader;

import hama.colorReducer.ColorReducer;
import hama.colorReducer.MoyenneReducer;

public class Reducer {

	private static ColorReducer colorReducer = new MoyenneReducer();

	public static PixelTable reduce(Image image, int largeur) {
		PixelTable pixels = new AWTPixelReader(image).getTable();
		double ratio = (double) pixels.getLargeur() / (double) pixels.getHauteur();
		int hauteur = (int) Math.round(largeur / ratio);

		return compute(pixels, largeur, hauteur);
	}

	private static PixelTable compute(PixelTable pixels, int largeur, int hauteur) {
		PixelTable finalTable = new PixelTable(largeur, hauteur);
		int xi = (int) Math.floor(pixels.getLargeur() / (double) largeur);
		int yi = (int) Math.floor(pixels.getHauteur() / (double) hauteur);
		System.out.println(yi + " " + pixels.getHauteur() / (double) hauteur);
		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				Couleur col = Reducer.getZoneCouleur(pixels, j, i, xi, yi);
				finalTable.setPixel(col, j, i);
			}
		}

		return finalTable;
	}

	private static Couleur getZoneCouleur(PixelTable pixels, int xi, int yi, int largeur, int hauteur) {
		int startY = yi * hauteur;
		int startX = xi * largeur;

		List<Couleur> candidats = new ArrayList<>();
		for (int i = 0; i < hauteur; i++) {
			if ((i + startY) < pixels.getHauteur()) {
				for (int j = 0; j < largeur; j++) {
					if ((j + startX) < pixels.getLargeur()) {
						int xx = startX + j;
						int yy = startY + i;
						Couleur c = pixels.getPixel(xx, yy);
						candidats.add(c);

					} // if
				} // for
			} // if
		} // for

		return colorReducer.reduce(candidats);
	}
}
