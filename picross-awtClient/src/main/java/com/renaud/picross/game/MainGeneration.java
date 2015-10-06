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
import com.renaud.picross.view.tools.SimpleImageLoader;

public class MainGeneration {

	public final static void main(String[] args) {
		Fenetre f = new Fenetre(800, 600);
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage(System.getProperty("user.dir") + "/src/main/resources/ferrari.jpg");
		Picross picross = new Picross();
		ColorResolver resolver = new InspectorResolver(picross, new DistanceSimple(), 15, 0.1);
		Finalizer finalizer = new LighterFinalizer(0.4);
		f.addDrawable(addPicross(image, 10, 10, 20, 5, picross, resolver, finalizer));
		f.start();
	}

	private static PicrosseDrawer addPicross(Image image, int x, int y, int largeur, int pixelLargeur, Picross picross, ColorResolver colorResolver, Finalizer finalizer) {
		PicrossGeneratorImpl generator = new PicrossGeneratorImpl(new AWTPixelReader(image).getTable(), picross, largeur);
		generator.computeImage();
		colorResolver.resolve(picross);
		finalizer.finalize(picross);
		generator.computeNumber();
		return new PicrosseDrawer(picross, Color.black, x, y, largeur * pixelLargeur, picross.getHauteur() * pixelLargeur, pixelLargeur);
	}
}
