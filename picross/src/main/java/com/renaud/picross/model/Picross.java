package com.renaud.picross.model;




public class Picross{


	private int largeur;
	private int hauteur;
	private Couleur[] colors;
	private int nbColors;
	

	
	public Picross() {}
	
	public Picross(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
		
		this.colors = new Couleur[this.largeur * this.hauteur];
	}
	
	public void validate(){
		this.colors = new Couleur[this.largeur * this.hauteur];
	}
	
	public void setColor(Couleur c, int x,int y){
		colors[x + y * largeur] = c;
	}
	
	public Couleur getPixel(int x,int y){
		return colors[x + y * largeur];
	}

	public int getLargeur() {
		return largeur;
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
	
	
	
}
