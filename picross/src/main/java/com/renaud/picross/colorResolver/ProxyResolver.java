package com.renaud.picross.colorResolver;

import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;

public class ProxyResolver implements ColorResolver {

	private List<Couleur> model = new ArrayList<>();
	private DistanceResolver distance;

	public ProxyResolver(DistanceResolver distance) {
		this.distance = distance;
		// model.add(new Couleur(200, 250, 200));
		// model.add(new Couleur(250, 200, 200));
		// model.add(new Couleur(200, 200, 250));
		// model.add(new Couleur(250, 250, 200));
		// model.add(new Couleur(200, 250, 250));
		// model.add(new Couleur(250, 200, 250));

		// model.add(new Couleur(150, 200, 150));
		// model.add(new Couleur(200, 150, 150));
		// model.add(new Couleur(150, 150, 200));
		// model.add(new Couleur(200, 200, 150));
		// model.add(new Couleur(150, 200, 200));
		// model.add(new Couleur(200, 150, 200));
		//
		// model.add(new Couleur(100, 150, 100));
		// model.add(new Couleur(150, 100, 100));
		// model.add(new Couleur(100, 100, 150));
		// model.add(new Couleur(150, 150, 100));
		// model.add(new Couleur(100, 150, 150));
		// model.add(new Couleur(150, 100, 150));
	}

	public List<Couleur> getModel() {
		return model;
	}

	public void setModel(List<Couleur> model) {
		this.model = model;
	}

	public void resolve(Picross p) {
		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			double normal = 9999999;
			Couleur who = Couleur.NOIR;
			Couleur c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			for (Couleur m : model) {
				if (!c.equals(m)) {
					double nor = distance.getdistance(c, m);
					if (nor < normal) {
						who = m;
						normal = nor;
					}
				}
			}
			// System.out.println(who);
			p.setColor(who, j % p.getLargeur(), j / p.getLargeur());
		}
	}

}
