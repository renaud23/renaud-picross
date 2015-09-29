package com.renaud.picross.model;

public class Couleur {

	public final static Couleur NOIR = new Couleur(0, 0, 0);
	
	
	private int r;
	private int g;
	private int b;
	private int rgba;

	public Couleur(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		checkRgba();
	}

	public Couleur() {}

	@Override
	public String toString() {
		return "Couleur [r=" + r + ", g=" + g + ", b=" + b + ", rgba=" + rgba
			+ "]";
	}

	private void checkRgba() {
		this.rgba = (r << 16) | (g << 8) | (b << 0);
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
