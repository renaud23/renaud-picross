package com.renaud.picross.generator;

import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Groupe;
import com.renaud.picross.model.Picross;
import com.renaud.picross.model.PixelTable;

public class PicrossGeneratorImpl implements PicrossGenerator {

	private Picross picross;

	private int largeur;
	private int hauteur;

	private PixelTable pixelTable;

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
		this.compute();
	}

	private void computeSize() {
		double ratio = (double) this.pixelTable.getLargeur() / (double) this.pixelTable.getHauteur();
		this.hauteur = (int) Math.round(this.largeur / ratio);
	}

	private void compute() {
		int xi = (int) Math.round(this.pixelTable.getLargeur() / (double) this.largeur);
		int yi = (int) Math.round(Math.round(this.pixelTable.getHauteur() / (double) this.hauteur));
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
			if ((i + startY) < pixelTable.getHauteur()) {

				for (int j = 0; j < largeur; j++) {
					if ((j + startX) < pixelTable.getLargeur()) {
						nbPixel++;

						int xx = startX + j;
						int yy = startY + i;
						Couleur c = pixelTable.getPixel(xx, yy);
						redTotal += c.getR();
						greenTotal += c.getG();
						blueTotal += c.getB();
					}// if
				} // for
			}// if
		}// for

		redTotal /= nbPixel;
		greenTotal /= nbPixel;
		blueTotal /= nbPixel;

		Couleur nc = new Couleur((int) redTotal, (int) greenTotal, (int) blueTotal);
		picross.setColor(nc, xi, yi);
	}

	@Override
	public void computeNumber() {
		List<List<Groupe>> horizontaux = new ArrayList<>();
		// lignes
		// for(int i=0;i<picross.getHauteur();i++){
		// Map<Color,Groupe> map = new HashMap<>();
		// int j = 0;
		// Color c;
		// while(j < (picross.getLargeur())){
		// c = picross.getPixel(j, i);
		//
		// Groupe gr = null;
		// if(!map.keySet().contains(c)){
		// gr = new Groupe();
		// gr.setCouleur(c);
		// gr.setContinu(true);
		// gr.setIndex(i);
		// map.put(c, gr);
		//
		// } else {
		// gr = map.get(c);
		// map.get(c).setContinu(false);
		// }
		//
		//
		//
		//
		// j++;
		// }
		// for(Color g : map.keySet()){
		// System.out.println(map.get(g));
		// }
		// System.out.println("$$$$$$$$$$");
		// }// for

	}

}
