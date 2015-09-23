package com.renaud.picross.colorResolver;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
		Map<Color, Integer> map = this.makeMap();
		int pos = 0;
		Random r = new Random();
		while (map.keySet().size() > nbColor) {
			Color cdt = (Color) map.keySet().toArray()[r.nextInt(map.keySet().size())];
			this.compact(map, cdt);
			map = this.makeMap();

		}
	}

	private Map<Color, Integer> makeMap() {
		Map<Color, Integer> map = new HashMap<Color, Integer>();
		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			Color c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			if (!map.containsKey(c)) {
				map.put(c, 1);
			}
			else {
				map.put(c, map.get(c) + 1);
			}
		}
		return map;
	}

	private void compact(Map<Color, Integer> map, Color ref) {
		double normal = 9999999;
		Color who = null;

		for (Color c : map.keySet()) {
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
			Color c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
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
