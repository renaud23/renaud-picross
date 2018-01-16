package com.renaud.picross.game;

import java.awt.Image;

import com.renaud.picross.model.PixelTable;
import com.renaud.picross.tools.SimpleImageLoader;
import com.renaud.picross.view.Fenetre;

import hama.Hama;
import hama.HamaDrawer;
import hama.HamaGenerator;
import hama.Nuancier;
import hama.Palette;
import hama.PixelTableDrawer;
import hama.Reducer;

public class Main {

	public final static void main(String[] args) {
		Fenetre f = new Fenetre(800, 600);
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/autoportrait.jpg");

		int largeur = 100;
		Nuancier nuancier = new Nuancier();
		PixelTable table = Reducer.reduce(image, largeur);
		Hama hama = HamaGenerator.compute(table, nuancier.getPerles());

		f.addDrawable(new Palette(nuancier.getCouleurs(), 0, 0, 10));
		f.addDrawable(new Palette(hama.getCouleursUtilisees(), 0, 11, 20));
		f.addDrawable(new PixelTableDrawer(table, 0, 32, 1, 0));
		f.addDrawable(new HamaDrawer(hama, largeur + 1, 32, 4, 1));

		f.start();
	}

}
