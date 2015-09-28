package com.renaud.picross.model;

import java.awt.Color;

public class Groupe {
	private Color couleur;
	private int taille;
	boolean continu;
	private int index;
	
	


	@Override
	public String toString() {
		return "Groupe [couleur=" + couleur + ", taille=" + taille
				+ ", continu=" + continu + ", index=" + index + "]";
	}

	public void incremente(){
		taille++;
	}

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public boolean isContinu() {
		return continu;
	}

	public void setContinu(boolean continu) {
		this.continu = continu;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
}
