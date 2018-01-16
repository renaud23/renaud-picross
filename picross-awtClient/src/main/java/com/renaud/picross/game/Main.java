package com.renaud.picross.game;

import java.awt.Image;

import com.renaud.picross.model.PixelTable;
import com.renaud.picross.tools.SimpleImageLoader;
import com.renaud.picross.view.Fenetre;

import hama.Hama;
import hama.Nuancier;
import hama.Palette;
import hama.PixelTableDrawer;
import hama.Reducer;

public class Main {

	public final static void main(String[] args) {
		Fenetre f = new Fenetre(800, 600);
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/joconde.jpg");

		int largeur = 50;
		PixelTable table = Reducer.reduce(image, largeur);
		PixelTable hama = Hama.compute(table);

		f.addDrawable(new Palette(Nuancier.getCouleurs(), 0, 0, 10));
		f.addDrawable(new Palette(hama.getUseCouleurs(), 0, 11, 20));
		f.addDrawable(new PixelTableDrawer(table, 0, 32, 1, 0));
		f.addDrawable(new PixelTableDrawer(hama, largeur + 1, 32, 5, 1));

		f.start();
	}

}
