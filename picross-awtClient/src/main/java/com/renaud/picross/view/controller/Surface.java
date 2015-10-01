package com.renaud.picross.view.controller;

public class Surface {

	public final static Surface SURFACE_VIDE = new Surface(0, 0, 0, 0);

	private int x;
	private int y;
	private int largeur;
	private int hauteur;

	public Surface() {}

	public Surface(int x, int y, int largeur, int hauteur) {
		this.x = x;
		this.y = y;
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public boolean isIn(int x, int y) {
		return x > this.x && x < (this.x + largeur) && y > this.y && y < (this.y + this.hauteur);
	}

	public boolean intersect(Surface s) {
		return false;
	}

	public boolean equals(Object o) {
		boolean is = false;
		if (o instanceof Surface) {
			Surface s = (Surface) o;
			is = s.x == x && s.y == y && s.largeur == largeur && s.hauteur == hauteur;
		}

		return is;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

}
