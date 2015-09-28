package com.renaud.picross.generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;

import com.renaud.picros.finalize.Finalizer;
import com.renaud.picross.colorResolver.ColorResolver;
import com.renaud.picross.model.Groupe;
import com.renaud.picross.model.Picross;
import com.renaud.picross.model.PixelTable;

public class PicrossGeneratorImpl implements PicrossGenerator{

	private Picross picross;

	private int largeur;
	private int hauteur;
	private int[] pixels;
	
	private PixelTable pixelTable;

	private ColorResolver resolver;
	private Finalizer finalizer;

	public PicrossGeneratorImpl(PixelTable pixelTable, Picross picross, int largeur) {
		this.pixelTable = pixelTable;
		this.picross = picross;
		this.largeur = largeur;
	}

	public void computeImage() {
		this.computeSize();
		this.picross.setLargeur(largeur);
		this.picross.setHauteur(hauteur);
		this.picross.validate();
		
//		this.pixels = new int[image.getWidth(null) * image.getHeight(null)];
//		PixelGrabber pg = new PixelGrabber(image, 0, 0, image.getWidth(null), image.getHeight(null), pixels, 0, image.getWidth(null));
//		try {
//			pg.grabPixels();
//		}
//		catch (InterruptedException e) {
//			throw new RejectedExecutionException("Impossible de lire les pixels de l'image.", e);
//		}
		
		this.compute();
	}

	private void computeSize() {
		double ratio = (double) this.pixelTable.getLargeur() / (double) this.pixelTable.getHauteur();
		this.hauteur = (int) (this.largeur / ratio);
	}

	private void compute() {
		int xi = this.pixelTable.getLargeur() / this.largeur;
		int yi = this.pixelTable.getHauteur() / this.hauteur;

		for (int i = 0; i < this.hauteur; i++) {
			for (int j = 0; j < this.largeur; j++) {
				computeZone(j, i, xi, yi);
			}
		}
	}

	private void computeZone(int xi, int yi, int largeur, int hauteur) {
//		double nbPixel = 0;
//		double redTotal = 0, greenTotal = 0, blueTotal = 0;
//		int startY = yi * hauteur;
//		int startX = xi * largeur;
//		for (int i = 0; i < hauteur; i++) {
//			if ((i + startY) < image.getHeight(null)) {
//
//				for (int j = 0; j < largeur; j++) {
//					if ((j + startX) < image.getWidth(null)) {
//						nbPixel++;
//
//						int xx = startX + j;
//						int yy = startY + i;
//						int c = pixels[xx + yy * image.getWidth(null)]; // or pixels[x * width + y]
//						int red = (c & 0x00ff0000) >> 16;
//						int green = (c & 0x0000ff00) >> 8;
//						int blue = c & 0x000000ff;
//
//						redTotal += red;
//						greenTotal += green;
//						blueTotal += blue;
//					}// if
//				} // for
//			}// if
//		}// for
//
//		redTotal /= nbPixel;
//		greenTotal /= nbPixel;
//		blueTotal /= nbPixel;
//
//		Color c = new Color((int) redTotal, (int) greenTotal, (int) blueTotal);
//		picross.setColor(c, xi, yi);
	}

	@Override
	public void computeNumber() {
		List<List<Groupe>> horizontaux = new ArrayList<>();
		// lignes
//		for(int i=0;i<picross.getHauteur();i++){
//			Map<Color,Groupe> map = new HashMap<>();
//			int j = 0;
//			Color c;
//			while(j < (picross.getLargeur())){
//				c = picross.getPixel(j, i);
//	
//				Groupe gr = null;
//				if(!map.keySet().contains(c)){
//					gr = new Groupe();
//					gr.setCouleur(c);
//					gr.setContinu(true);
//					gr.setIndex(i);
//					map.put(c, gr);
//					
//				} else {
//					gr = map.get(c);
//					map.get(c).setContinu(false);	
//				}
//				
//				
//				
//				
//				j++;
//			}
//			for(Color g : map.keySet()){
//				System.out.println(map.get(g));
//			}
//			System.out.println("$$$$$$$$$$");
//		}// for
		
	}

}
