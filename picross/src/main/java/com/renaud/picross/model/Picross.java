package com.renaud.picross.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Picross {

	private int largeur;
	private int hauteur;
	private Couleur[] pixels;
	private int nbColors;
	private List<Couleur> couleurs;

	private List<Collection<Groupe>> lignes = new ArrayList<>();
	private List<Collection<Groupe>> colonnes = new ArrayList<>();

	public Picross() {}

	public Picross(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.pixels = new Couleur[this.largeur * this.hauteur];
		for (int i = 0; i < this.largeur * this.hauteur; i++){
			this.pixels[i] = Couleur.NOIR;
		}
	}

	public void validate() {
		this.pixels = new Couleur[this.largeur * this.hauteur];
	}

	public void setLigne(int index, Collection<Groupe> groupes) {
		lignes.add(index, groupes);
	}

	public Collection<Groupe> getLigne(int index) {
		return lignes.get(index);
	}

	public void setColonne(int index, Collection<Groupe> groupes) {
		colonnes.add(index, groupes);
	}

	public Collection<Groupe> getColonne(int index) {
		return colonnes.get(index);
	}

	public void setColor(Couleur c, int x, int y) {
		pixels[x + y * largeur] = c;
	}

	public Couleur getPixel(int x, int y) {
		return pixels[x + y * largeur];
	}

	public int getLargeur() {
		return largeur;
	}

	public Couleur[] getColors() {
		return pixels;
	}

	public void setColors(Couleur[] colors) {
		this.pixels = colors;
	}

	public int getNbColors() {
		return nbColors;
	}

	public void setNbColors(int nbColors) {
		this.nbColors = nbColors;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	public List<Couleur> getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(List<Couleur> couleurs) {
		this.couleurs = couleurs;
	}
}
