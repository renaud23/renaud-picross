package com.renaud.picross.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		for (int i = 0; i < picross.getHauteur(); i++) {
			List<Couleur> couleurs = new ArrayList<>();
			for (int j = 0; j < picross.getLargeur(); j++) {
				couleurs.add(picross.getPixel(j, i));
			}
			picross.setLigne(i, checkGroupe(couleurs, i));
		}
		for (int j = 0; j < picross.getLargeur(); j++) {
			List<Couleur> couleurs = new ArrayList<>();
			for (int i = 0; i < picross.getHauteur(); i++) {
				couleurs.add(picross.getPixel(j, i));
			}
			picross.setColonne(j, checkGroupe(couleurs, j));
		}
	}

	private Collection<Groupe> checkGroupe(List<Couleur> couleurs, int index) {
		Map<Couleur, Groupe> map = new HashMap<>();
		Groupe cur = null;
		Couleur next = null;
		for (int j = 0; j < couleurs.size(); j++) {
			Couleur col = couleurs.get(j);
			if (map.containsKey(col)) {
				cur = map.get(col);
				cur.setContinu(false);
			} else {
				cur = new Groupe();
				cur.setCouleur(col);
				cur.setIndex(index);
				cur.setContinu(true);
				map.put(col, cur);
			}
			next = col;
			cur.incremente();
			while (col.equals(next) && j < (couleurs.size() - 1)) {
				next = couleurs.get(j + 1);
				if (col.equals(next)) {
					j++;
					cur.incremente();
				}
			}
		}
		return map.values();
	}
}
