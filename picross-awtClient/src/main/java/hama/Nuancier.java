package hama;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.renaud.picross.model.Couleur;

public class Nuancier {

	private List<Perle> perlesRef;
	private List<Perle> perles;

	public Nuancier() {
		perlesRef = new ArrayList<>();
		perles = new ArrayList<>();

		perlesRef.add(Perle.NOIR);
		perlesRef.add(Perle.JAUNE);
		perlesRef.add(Perle.BLANC);
		perlesRef.add(Perle.CREME);
		perlesRef.add(Perle.ORANGE);
		perlesRef.add(Perle.ROUGE);
		perlesRef.add(Perle.ROSE);
		perlesRef.add(Perle.VIOLET);
		perlesRef.add(Perle.BLEU_FONCE);
		perlesRef.add(Perle.BLEU);
		perlesRef.add(Perle.VERT);
		perlesRef.add(Perle.VERT_CLAIR);
		perlesRef.add(Perle.MARRON);
		perlesRef.add(Perle.GRIS);
		perlesRef.add(Perle.CARAMEL);
		perlesRef.add(Perle.MARRON_CLAIR);
		perlesRef.add(Perle.CHAIR);
		perlesRef.add(Perle.BEIGE);
		perlesRef.add(Perle.VERT_FONCE);
		perlesRef.add(Perle.LIE_DE_VIN);
		perlesRef.add(Perle.TURQUOISE);
		perlesRef.add(Perle.CERISE);
		perlesRef.add(Perle.GRIS_CLAIR);
		perlesRef.add(Perle.GRIS_FONCE);

		perles.addAll(perlesRef);
	}

	public boolean estUtilisee(Perle perle) {
		return perles.contains(perle);
	}

	public boolean estUtilisee(Couleur c) {

		for (Perle p : perles) {
			if (p.getCouleur().equals(c)) {
				return true;
			}
		}
		return false;
	}

	public void ajouter(Perle perle) {
		this.perles.add(perle);
	}

	public void supprimer(Perle perle) {
		perles.remove(perle);
	}

	public List<Perle> getPerles() {
		return perles;
	}

	public List<Couleur> getCouleurs() {
		return perles.stream().map(p -> p.getCouleur()).collect(Collectors.toList());
	}

	public List<Couleur> getCouleursRef() {
		return perlesRef.stream().map(p -> p.getCouleur()).collect(Collectors.toList());
	}

	public void setPerlesRef(List<Perle> perlesRef) {
		this.perlesRef = perlesRef;
	}
}
