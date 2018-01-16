package hama;

import com.renaud.picross.model.Couleur;

public class Perle {

	public final static Perle NOIR = new Perle(Couleur.NOIR, "18", "noir");
	public final static Perle BLANC = new Perle(Couleur.BLANC, "01", "blanc");

	public final static Perle JAUNE = new Perle(Couleur.JAUNE, "03", "jaune");// 03
	public final static Perle CREME = new Perle(Couleur.CREME, "02", "creme");// 02
	public final static Perle ORANGE = new Perle(Couleur.ORANGE, "04", "orange");// 04
	public final static Perle ROUGE = new Perle(Couleur.ROUGE, "05", "rouge");// 05
	public final static Perle ROSE = new Perle(Couleur.ROSE, "06", "rose");// 06
	public final static Perle VIOLET = new Perle(Couleur.VIOLET, "07", "violet");// 07
	public final static Perle BLEU_FONCE = new Perle(Couleur.BLEU_FONCE, "08", "bleu foncé");// 08
	public final static Perle BLEU = new Perle(Couleur.BLEU, "09", "bleu");// 09
	public final static Perle VERT = new Perle(Couleur.VERT, "10", "vert");// 10
	public final static Perle VERT_CLAIR = new Perle(Couleur.VERT_CLAIR, "11", "vert clair");// 11
	public final static Perle MARRON = new Perle(Couleur.MARRON, "12", "marron");// 12
	public final static Perle GRIS = new Perle(Couleur.GRIS, "17", "gris");// 17
	public final static Perle CARAMEL = new Perle(Couleur.CARAMEL, "20", "caramel");// 20
	public final static Perle MARRON_CLAIR = new Perle(Couleur.MARRON_CLAIR, "21", "marron clair");// 21
	public final static Perle CHAIR = new Perle(Couleur.CHAIR, "26", "chair");// 26
	public final static Perle BEIGE = new Perle(Couleur.BEIGE, "27", "beige");// 27
	public final static Perle VERT_FONCE = new Perle(Couleur.VERT_FONCE, "28", "vert fonce");// 28
	public final static Perle LIE_DE_VIN = new Perle(Couleur.LIE_DE_VIN, "29", "lie de vin");// 29
	public final static Perle TURQUOISE = new Perle(Couleur.TURQUOISE, "31", "turquoise");// 31
	public final static Perle CERISE = new Perle(Couleur.CERISE, "33", "cerise");// 33

	private Couleur couleur;
	private String code;
	private String nom;

	public Perle() {}

	public Perle(Couleur couleur, String code, String nom) {
		this.couleur = couleur;
		this.code = code;
		this.nom = nom;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
