package com.renaud.picross.game;

import java.awt.Color;
import java.awt.Image;

import com.renaud.picros.finalize.Finalizer;
import com.renaud.picros.finalize.LighterFinalizer;
import com.renaud.picross.colorResolver.ColorResolver;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.colorResolver.InspectorResolver;
import com.renaud.picross.generator.PicrossGeneratorImpl;
import com.renaud.picross.model.Picross;
import com.renaud.picross.view.AWTPixelReader;
import com.renaud.picross.view.Fenetre;
import com.renaud.picross.view.PicrosseDrawer;
import com.renaud.picross.view.controller.RootController;
import com.renaud.picross.view.tools.SimpleImageLoader;

public class MainClient {

	public final static void main(String[] args) {
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/ferrari.jpg");
		Image background = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/background/background_pourpre.png");
		
		Picross picross = new Picross();
		ColorResolver resolver = new InspectorResolver(picross, new DistanceSimple(), 5, 0.01);
		Finalizer finalizer = new LighterFinalizer(0.4);
		PicrossGeneratorImpl generator = new PicrossGeneratorImpl(new AWTPixelReader(image).getTable(), picross, 40);
		generator.computeImage();
		resolver.resolve(picross);
		finalizer.finalize(picross);
		generator.computeNumber();
		picross.computeCouleur();

		int largeur = 1600;
		int hauteur = 900;

		GameContext gx = new GameContext();
		RootController controller = new RootController(largeur, hauteur);
		GameSequence game = new GameSequence(controller);
		gx.setSequence(game);
		game.setPicross(picross);

		Fenetre f = new Fenetre(largeur, hauteur);
		f.setBackground(background);
		f.getBuffer().addMouseListener(gx);
		f.getBuffer().addMouseMotionListener(gx);
		f.setSequence(gx);
		f.addDrawable(gx);
		f.start();
		
		Fenetre f2 = new Fenetre(200, 200);
		f2.addDrawable(new PicrosseDrawer(picross, Color.black, 10, 10, 5 * picross.getLargeur(), picross.getHauteur() * 5, 5));
		f2.start();
	}
}
