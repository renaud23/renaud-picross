package com.renaud.picross.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.util.concurrent.RejectedExecutionException;

import com.renaud.picross.generator.PixelReader;
import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;

public class AWTPixelReader implements PixelReader {

	private Image image;

	public AWTPixelReader(Image image) {
		this.image = image;
	}

	@Override
	public PixelTable getTable() {
		if (image != null) {
			int l = image.getWidth(null);
			int h = image.getHeight(null);
			int t = h * l;
			int[] data = new int[t];
			PixelTable pt = new PixelTable(l, h);
			PixelGrabber pg = new PixelGrabber(image, 0, 0, l, h, data, 0, l);
			try {
				pg.grabPixels();
				for (int i = 0; i < t; i++) {
					Color color = new Color(data[i]);
					pt.setPixel(new Couleur(color.getRed(), color.getGreen(), color.getBlue()), i % l, i / l);
				}
				return pt;
			}
			catch (InterruptedException e) {
				throw new RejectedExecutionException("Impossible de lire les pixels de l'image.", e);
			}

		}
		return null;
	}
}
