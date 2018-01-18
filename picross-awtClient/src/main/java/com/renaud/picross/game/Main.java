package com.renaud.picross.game;

import java.awt.Image;

import com.renaud.picross.model.PixelTable;
import com.renaud.picross.tools.SimpleImageLoader;
import com.renaud.picross.view.Fenetre;

import hama.Nuancier;
import hama.Reducer;
import hama.drawer.HamaDrawer;
import hama.drawer.PaletteDrawer;
import hama.drawer.PixelTableDrawer;
import hama.view.HamaGame;
import hama.view.MouseZoneProvider;
import hama.view.PaletteView;
import hama.view.Store;

public class Main {

	public final static void main(String[] args) {
		int hamaLargeur = 57;
		int winWidth = 800;
		int winHeight = 600;
		int paletteHauteur = 20;

		Fenetre f = new Fenetre(winWidth, winHeight);
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/joconde.jpg");

		Nuancier nuancier = new Nuancier();
		PixelTable table = Reducer.reduce(image, hamaLargeur);
		HamaGame game = new HamaGame(table, nuancier);
		Store.addObserver(game);

		// palette
		PaletteDrawer paletteDrawer = new PaletteDrawer(nuancier, 0, 0, paletteHauteur);
		PaletteView paletteView = new PaletteView(paletteDrawer, nuancier, 0, 0, paletteHauteur);

		// hama
		int hamaPerleSize = Math.min((winHeight - paletteHauteur) / game.getHama().getHauteur(), (winWidth - hamaLargeur) / game.getHama().getLargeur());
		HamaDrawer hamaDrawer = new HamaDrawer(game.getHama(), hamaLargeur + 1, 32, hamaPerleSize, hamaPerleSize > 1 ? 1 : 0);

		// draw opérations
		f.addDrawable(paletteDrawer);
		// f.addDrawable(new PaletteDrawer(hama.getCouleursUtilisees(), 0, 11, 20));
		f.addDrawable(new PixelTableDrawer(table, 0, 32, 1, 0));
		f.addDrawable(hamaDrawer);

		Store.addObserver(hamaDrawer);

		f.addMouseListener(MouseZoneProvider.getInstance());
		f.addMouseMotionListener(MouseZoneProvider.getInstance());
		f.start();
	}

}
