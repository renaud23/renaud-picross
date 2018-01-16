package hama;

import java.util.HashSet;
import java.util.Set;

import com.renaud.picross.model.Couleur;

public class Nuancier {

	public static Set<Couleur> getCouleurs() {
		Set<Couleur> nuanciers = new HashSet<>();
		nuanciers.add(Couleur.NOIR);
		nuanciers.add(Couleur.JAUNE);

		nuanciers.add(Couleur.BLANC);
		nuanciers.add(Couleur.CREME);
		nuanciers.add(Couleur.ORANGE);
		nuanciers.add(Couleur.ROUGE);
		nuanciers.add(Couleur.ROSE);
		nuanciers.add(Couleur.VIOLET);
		nuanciers.add(Couleur.BLEU_FONCE);
		nuanciers.add(Couleur.BLEU);
		nuanciers.add(Couleur.VERT);
		nuanciers.add(Couleur.VERT_CLAIR);
		nuanciers.add(Couleur.MARRON);
		nuanciers.add(Couleur.GRIS);
		nuanciers.add(Couleur.CARAMEL);
		nuanciers.add(Couleur.MARRON_CLAIR);
		nuanciers.add(Couleur.CHAIR);
		nuanciers.add(Couleur.BEIGE);
		nuanciers.add(Couleur.VERT_FONCE);
		nuanciers.add(Couleur.LIE_DE_VIN);

		return nuanciers;
	}
}
