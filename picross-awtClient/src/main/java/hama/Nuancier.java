package hama;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.renaud.picross.model.Couleur;

public class Nuancier {

	private List<Perle> perles;

	public Nuancier() {
		perles = new ArrayList<>();

		perles.add(Perle.NOIR);
		perles.add(Perle.JAUNE);
		perles.add(Perle.BLANC);
		perles.add(Perle.CREME);
		perles.add(Perle.ORANGE);
		perles.add(Perle.ROUGE);
		perles.add(Perle.ROSE);
		perles.add(Perle.VIOLET);
		perles.add(Perle.BLEU_FONCE);
		perles.add(Perle.BLEU);
		perles.add(Perle.VERT);
		perles.add(Perle.VERT_CLAIR);
		perles.add(Perle.MARRON);
		perles.add(Perle.GRIS);
		perles.add(Perle.CARAMEL);
		perles.add(Perle.MARRON_CLAIR);
		perles.add(Perle.CHAIR);
		perles.add(Perle.BEIGE);
		perles.add(Perle.VERT_FONCE);
		perles.add(Perle.LIE_DE_VIN);
		perles.add(Perle.TURQUOISE);
		perles.add(Perle.CERISE);
	}

	public List<Perle> getPerles() {
		return perles;
	}

	public List<Couleur> getCouleurs() {
		return perles.stream().map(p -> p.getCouleur()).collect(Collectors.toList());
	}
}
