package com.renaud.picross.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class PixelTable implements Iterable<Couleur> {

	private Couleur[] couleurs;
	private int largeur;
	private int hauteur;
	private int taille;

	public PixelTable(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		taille = largeur * hauteur;
		couleurs = new Couleur[taille];
	}

	public Set<Couleur> getUseCouleurs() {
		Set<Couleur> coul = new HashSet<>();
		for (Couleur c : Arrays.asList(couleurs)) {
			coul.add(c);
		}
		return coul;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public void setPixel(Couleur c, int x, int y) {
		couleurs[x + y * largeur] = c;
	}

	public Couleur getPixel(int x, int y) {
		return couleurs[x + y * largeur];
	}

	@Override
	public Iterator<Couleur> iterator() {
		return Arrays.asList(couleurs).iterator();
	}

}
