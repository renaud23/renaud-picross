package com.renaud.picross.generator;

import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.util.concurrent.RejectedExecutionException;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.PixelTable;



public class AWTPixelReader implements PixelReader {
	
	public AWTPixelReader(Image image) {
		this.image = image;
	}

	private Image image;

	@Override
	public PixelTable getTable() {
		if(image != null){
			int l = image.getWidth(null);
			int h = image.getHeight(null);
			int t = h * l;
			int[] data = new int[t];
			PixelTable pt = new PixelTable(l, h);
			PixelGrabber pg = new PixelGrabber(image, 0, 0, image.getWidth(null), h, data, 0, l);
			try {
				pg.grabPixels();
				for(int i=0;i<t;i++){
					pt.setPixel(new Couleur(), i % l, i / l);
				}
				
			}
			catch (InterruptedException e) {
				throw new RejectedExecutionException("Impossible de lire les pixels de l'image.", e);
			}
		
		}
		return null;
	}
}
