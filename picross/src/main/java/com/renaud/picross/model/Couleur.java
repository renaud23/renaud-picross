package com.renaud.picross.model;

public class Couleur {

	public final static Couleur NOIR = new Couleur(0, 0, 0);
	public final static Couleur BLANC = new Couleur(255, 255, 255, 255);
	public final static Couleur TRANSPARENT = new Couleur(255, 255, 255, 0);
	public final static Couleur NULL = new Couleur(-1, -1, -1);

	public final static Couleur JAUNE = new Couleur(252, 200, 16);// 03
	public final static Couleur CREME = new Couleur(249, 237, 188);// 02
	public final static Couleur ORANGE = new Couleur(240, 100, 50);// 04
	public final static Couleur ROUGE = new Couleur(237, 30, 30);// 05
	public final static Couleur ROSE = new Couleur(238, 137, 152);// 06
	public final static Couleur VIOLET = new Couleur(90, 69, 148);// 07
	public final static Couleur BLEU_FONCE = new Couleur(2, 90, 156);// 08
	public final static Couleur BLEU = new Couleur(0, 115, 186);// 09
	public final static Couleur VERT = new Couleur(5, 127, 65);// 10
	public final static Couleur VERT_CLAIR = new Couleur(36, 183, 140);// 11
	public final static Couleur MARRON = new Couleur(58, 31, 26);// 12
	public final static Couleur GRIS = new Couleur(125, 137, 141);// 17
	public final static Couleur CARAMEL = new Couleur(147, 57, 40);// 20
	public final static Couleur MARRON_CLAIR = new Couleur(189, 108, 53);// 21
	public final static Couleur CHAIR = new Couleur(220, 170, 152);// 26
	public final static Couleur BEIGE = new Couleur(226, 177, 122);// 27
	public final static Couleur VERT_FONCE = new Couleur(39, 59, 42);// 28
	public final static Couleur LIE_DE_VIN = new Couleur(192, 29, 72);// 29

	private int r;
	private int g;
	private int b;
	private int alpha = 255;
	private int rgba;

	public Couleur(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		checkRgba();
	}

	public Couleur(int r, int g, int b, int alpha) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.alpha = alpha;
		checkRgba();
	}

	public Couleur() {}

	@Override
	public String toString() {
		return "Couleur [r=" + r + ", g=" + g + ", b=" + b + ", rgba=" + rgba + "]";
	}

	private void checkRgba() {
		this.rgba = (alpha << 24) | (r << 16) | (g << 8) | (b << 0);
	}

	public int getR() {
		return r;
	}

	public int getG() {
		return g;
	}

	public int getB() {
		return b;
	}

	public int getRgba() {
		return rgba;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public boolean equals(Object o) {
		boolean state = false;
		if (o instanceof Couleur) {
			Couleur c = (Couleur) o;
			state = c.r == r && c.g == g && c.b == b;
		}

		return state;
	}

	@Override
	public int hashCode() {
		return rgba;
	}

}
