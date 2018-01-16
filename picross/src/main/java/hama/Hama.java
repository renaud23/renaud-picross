package hama;

import java.util.HashSet;
import java.util.Set;

import com.renaud.picross.model.Couleur;

public class Hama {

	private Perle[] perles;
	private int hauteur;
	private int largeur;

	public Hama(int largeur, int hauteur) {
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.perles = new Perle[largeur * hauteur];
	}

	public Set<Couleur> getCouleursUtilisees() {
		Set<Couleur> m = new HashSet<>();
		for (Perle p : perles) {
			m.add(p.getCouleur());
		}

		return m;
	}

	public Perle getPerle(int x, int y) {
		return this.perles[y * largeur + x];
	}

	public void setPerle(Perle perle, int x, int y) {
		this.perles[y * largeur + x] = perle;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

}
