package com.renaud.picross.model;

import java.awt.Color;

public class Picross {
	private int largeur;
	private int hauteur;
	private Color[] colors;
	
	public Picross(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.colors = new Color[this.largeur * this.hauteur];
	}
	
	public void setColor(Color c, int x,int y){
		colors[x + y * largeur] = c;
	}
	
	public Color getPixel(int x,int y){
		return colors[x + y * largeur];
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}
	
	
}
