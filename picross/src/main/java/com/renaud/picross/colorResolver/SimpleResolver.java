package com.renaud.picross.colorResolver;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;

public class SimpleResolver implements ColorResolver {

	private Picross p;
	private DistanceResolver distance;
	private int nbColor = 5;

	public SimpleResolver(Picross p, DistanceResolver distance) {
		this.p = p;
		this.distance = distance;
	}

	public Picross getP() {
		return p;
	}

	public void setP(Picross p, int nbColor) {
		this.p = p;
		this.nbColor = nbColor;
	}

	public void resolve(Picross p) {
		Map<Couleur, Integer> map = this.makeMap();
		int pos = 0;
		Random r = new Random();
		while (map.keySet().size() > nbColor) {
			Couleur cdt = (Couleur) map.keySet().toArray()[r.nextInt(map.keySet().size())];
			this.compact(map, cdt);
			map = this.makeMap();

		}
	}

	private Map<Couleur, Integer> makeMap() {
		Map<Couleur, Integer> map = new HashMap<>();
		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			Couleur c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			if (!map.containsKey(c)) {
				map.put(c, 1);
			}
			else {
				map.put(c, map.get(c) + 1);
			}
		}
		return map;
	}

	private void compact(Map<Couleur, Integer> map, Couleur ref) {
		double normal = 9999999;
		Couleur who = null;

		for (Couleur c : map.keySet()) {
			if (!c.equals(ref)) {
				double nor = distance.getdistance(c, ref);
				if (nor < normal) {
					who = c;
					normal = nor;
				}
			}
		}

		// who = new Color(ref.getRed() + who.getRed)
		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			Couleur c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			if (c.equals(who)) {
				p.setColor(ref, j % p.getLargeur(), j / p.getLargeur());
			}
		}
	}

	@Override
	public int getNbColor() {
		return nbColor;
	}

}
