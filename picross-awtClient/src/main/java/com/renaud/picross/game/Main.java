package com.renaud.picross.game;

import java.awt.Image;

import com.renaud.picross.model.PixelTable;
import com.renaud.picross.tools.SimpleImageLoader;
import com.renaud.picross.view.Fenetre;
import com.renaud.picross.view.IDrawable;

import hama.Hama;
import hama.HamaGenerator;
import hama.Nuancier;
import hama.Reducer;
import hama.drawer.HamaDrawer;
import hama.drawer.PaletteDrawer;
import hama.drawer.PixelTableDrawer;
import hama.view.MouseZoneProvider;
import hama.view.PaletteView;

public class Main {

	public final static void main(String[] args) {
		int hamaLargeur = 100;
		int winWidth = 800;
		int winHeight = 600;
		int paletteHauteur = 10;

		Fenetre f = new Fenetre(winWidth, winHeight);
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/joconde.jpg");

		Nuancier nuancier = new Nuancier();
		PixelTable table = Reducer.reduce(image, hamaLargeur);
		Hama hama = HamaGenerator.compute(table, nuancier.getPerles());

		int hamaPerleSize = Math.min((winHeight - paletteHauteur) / hama.getHauteur(), (winWidth - hamaLargeur) / hama.getLargeur());
		PaletteDrawer paletteDrawer = new PaletteDrawer(nuancier, 0, 0, paletteHauteur);
		IDrawable hamaDrawer = new HamaDrawer(hama, hamaLargeur + 1, 32, hamaPerleSize, hamaPerleSize > 1 ? 1 : 0);
		// view
		PaletteView paletteView = new PaletteView(paletteDrawer, nuancier, 0, 0, 10);

		// draw opérations
		f.addDrawable(paletteDrawer);
		// f.addDrawable(new PaletteDrawer(hama.getCouleursUtilisees(), 0, 11, 20));
		f.addDrawable(new PixelTableDrawer(table, 0, 32, 1, 0));
		f.addDrawable(hamaDrawer);

		f.addMouseListener(MouseZoneProvider.getInstance());
		f.addMouseMotionListener(MouseZoneProvider.getInstance());
		f.start();
	}

}
