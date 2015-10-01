package com.renaud.picross.game;

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
import com.renaud.picross.view.controller.RootController;
import com.renaud.picross.view.tools.SimpleImageLoader;

public class MainClient {

	public final static void main(String[] args) {
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/ferrari.jpg");
		Picross picross = new Picross();
		ColorResolver resolver = new InspectorResolver(picross, new DistanceSimple(), 5, 0.01);
		Finalizer finalizer = new LighterFinalizer(0.4);
		PicrossGeneratorImpl generator = new PicrossGeneratorImpl(new AWTPixelReader(image).getTable(), picross, 30);
		generator.computeImage();
		resolver.resolve(picross);
		finalizer.finalize(picross);
		generator.computeNumber();

		int largeur = 800;
		int hauteur = 600;
		Fenetre f = new Fenetre(largeur, hauteur);
		GameContext gx = new GameContext();
		RootController controller = new RootController(largeur, hauteur);
		f.getBuffer().addMouseListener(gx);
		f.getBuffer().addMouseMotionListener(gx);

		f.setSequence(gx);
		GameSequence game = new GameSequence(controller);
		gx.setSequence(game);
		game.setPicross(picross);

		f.addDrawable(gx);
		f.start();
	}
}
