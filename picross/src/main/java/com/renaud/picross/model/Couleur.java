package com.renaud.picross.model;

public class Couleur {
	private int r;
	private int  g;
	private int b;
	private int rgba;
	
	
	
	
	public Couleur(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
		checkRgba();
	}
	
	public Couleur() {}
	
	public Couleur(int rgb){
		setRgba(rgb);
	}
	
	@Override
	public String toString() {
		return "Couleur [r=" + r + ", g=" + g + ", b=" + b + ", rgba=" + rgba
				+ "]";
	}

	private void checkRgba(){
		this.rgba = (r << 16) | (g << 8) | (b << 0);
	}
	

	public int getR() {
		return r;
	}
	public void setR(int r) {
		this.r = r;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}

	public int getRgba() {
		return rgba;
	}

	public void setRgba(int rgba) {
		this.rgba = rgba;
		r = ((rgba >> 16) & 0xff);
		g = ((rgba >> 8) & 0xff);
		b = ((rgba >> 0) & 0xff);
	}
	
}
