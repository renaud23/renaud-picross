package com.renaud.picross.generator;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.util.concurrent.RejectedExecutionException;

import com.renaud.picros.finalize.Finalizer;
import com.renaud.picross.colorResolver.ColorResolver;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.colorResolver.ProxyResolver;
import com.renaud.picross.model.Picross;

public class PicrossGeneratorImpl implements PicrossGenerator{

	private Picross picross;

	private Image image;
	private int largeur;
	private int hauteur;
	private int[] pixels;

	private ColorResolver resolver;
	private Finalizer finalizer;

	public PicrossGeneratorImpl(Image image, Picross picross, int largeur) {
		this.image = image;
		this.picross = picross;
		this.largeur = largeur;
	}

	public void computeImage() {
		this.computeSize();
		this.picross.setLargeur(largeur);
		this.picross.setHauteur(hauteur);
		this.picross.validate();
		this.pixels = new int[image.getWidth(null) * image.getHeight(null)];
		PixelGrabber pg = new PixelGrabber(image, 0, 0, image.getWidth(null), image.getHeight(null), pixels, 0, image.getWidth(null));
		try {
			pg.grabPixels();
		}
		catch (InterruptedException e) {
			throw new RejectedExecutionException("Impossible de lire les pixels de l'image.", e);
		}
		this.compute();
	}

	private void computeSize() {
		double ratio = (double) this.image.getWidth(null) / (double) this.image.getHeight(null);
		this.hauteur = (int) (this.largeur / ratio);
	}

	private void compute() {
		int xi = image.getWidth(null) / this.largeur;
		int yi = image.getHeight(null) / this.hauteur;

		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				computeZone(j, i, xi, yi);
			}
		}
	}

	private void computeZone(int xi, int yi, int largeur, int hauteur) {
		double nbPixel = 0;
		double redTotal = 0, greenTotal = 0, blueTotal = 0;
		int startY = yi * hauteur;
		int startX = xi * largeur;
		for (int i = 0; i < hauteur; i++) {
			if ((i + startY) < image.getHeight(null)) {

				for (int j = 0; j < largeur; j++) {
					if ((j + startX) < image.getWidth(null)) {
						nbPixel++;

						int xx = startX + j;
						int yy = startY + i;
						int c = pixels[xx + yy * image.getWidth(null)]; // or pixels[x * width + y]
						int red = (c & 0x00ff0000) >> 16;
						int green = (c & 0x0000ff00) >> 8;
						int blue = c & 0x000000ff;

						redTotal += red;
						greenTotal += green;
						blueTotal += blue;
					}// if
				} // for
			}// if
		}// for

		redTotal /= nbPixel;
		greenTotal /= nbPixel;
		blueTotal /= nbPixel;

		Color c = new Color((int) redTotal, (int) greenTotal, (int) blueTotal);
		picross.setColor(c, xi, yi);
	}

}
