package hama;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;
import com.renaud.picross.view.AWTPixelReader;

import hama.colorReducer.ColorReducer;
import hama.colorReducer.MoyenneReducer;
import hama.drawer.PixelTableDrawer;

public class ReducerDebug {

	private static ColorReducer colorReducer = new MoyenneReducer();

	public static PixelTable reduce(Image image, int largeur) {
		PixelTable pixels = new AWTPixelReader(image).getTable();

		double ratio = (double) pixels.getLargeur() / (double) pixels.getHauteur();

		int hauteur = (int) Math.round(largeur / ratio);

		// double ponderation = 0.9;
		// PixelTable pixelsInt = pixels;
		// int i = 1;
		// double largeurInt = pixels.getLargeur();
		// while (largeurInt > largeur) {
		// largeurInt *= ponderation;
		// if (largeurInt < largeur) {
		// largeurInt = largeur;
		// }
		// int hauteurInt = (int) Math.round(largeurInt / ratio);
		// pixelsInt = compute(pixelsInt, (int) largeurInt, hauteurInt);
		//
		// /**/
		// PixelTableDrawer dr = new PixelTableDrawer(pixelsInt, 0, 0, 5, 0);
		// dr.FillImage();
		//
		// Image img = dr.getBuffer().getImage();
		// // Create a buffered image with transparency
		// BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		//
		// // Draw the image on to the buffered image
		// Graphics2D bGr = bimage.createGraphics();
		// bGr.drawImage(img, 0, 0, null);
		// bGr.dispose();
		//
		// File outputfile = new File("d:/tmp/img/save_" + i++ + ".png");
		// try {
		// ImageIO.write(bimage, "png", outputfile);
		// }
		// catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		PixelTable pixelsFinal = compute(pixels, largeur, hauteur);

		return pixelsFinal;
	}

	private static PixelTable compute(PixelTable pixels, int largeur, int hauteur) {
		PixelTable finalTable = new PixelTable(largeur, hauteur);
		double xi = pixels.getLargeur() / (double) largeur;
		double yi = pixels.getHauteur() / (double) hauteur;

		for (int i = 0; i < hauteur; i++) {
			for (int j = 0; j < largeur; j++) {
				Couleur col = ReducerDebug.getZoneCouleur(pixels, j, i, xi, yi);
				finalTable.setPixel(col, j, i);
			}
		}

		return finalTable;
	}

	private static void save(String path, PixelTable table) {
		PixelTableDrawer dr = new PixelTableDrawer(table, 0, 0, 5, 0);
		dr.FillImage();

		Image img = dr.getBuffer().getImage();
		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		File outputfile = new File(path);
		try {
			ImageIO.write(bimage, "png", outputfile);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Couleur getZoneCouleur(PixelTable pixels, int xi, int yi, double largeur, double hauteur) {

		int startY = (int) Math.round(yi * hauteur);
		int startX = (int) Math.round(xi * largeur);
		int endY = (int) Math.round(Math.min(startY + hauteur, pixels.getHauteur()));
		int endX = (int) Math.round(Math.min(startX + hauteur, pixels.getLargeur()));

		save("d:/tmp/img/save_" + xi + "_" + yi + ".png", pixels.getSub(startX, startY, endX - startX, endY - startY));

		List<Couleur> candidats = new ArrayList<>();
		for (int i = startY; i < endY; i++) {
			for (int j = startX; j < endX; j++) {
				Couleur c = pixels.getPixel(j, i);
				candidats.add(c);
			}
		}
		return colorReducer.reduce(candidats);
	}
}
