package com.renaud.picross.colorResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;

public class InspectorResolver implements ColorResolver {

	private int nbColor = 5;
	private Picross p;
	private ProxyResolver proxyResolver;
	private DistanceResolver distance;

	public InspectorResolver(Picross p, DistanceResolver distance, int nbColor) {
		this.p = p;
		this.proxyResolver = new ProxyResolver(distance);
		this.nbColor = nbColor;
		this.distance = distance;
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

	public void resolve(Picross p) {
		List<Couleur> colors = new ArrayList<>();
		Map<Couleur, Integer> map = makeMap();
		while (colors.size() < nbColor && map.size() > 0) {
			int best = -1;
			Couleur who = null;
			for (Couleur c : map.keySet()) {
				int count = contraste(c);
				if (count > best) {
					best = count;
					who = c;
				}
			}
			colors.add(who);
			map.remove(who);
		}
		this.reduce(colors);
		proxyResolver.setModel(colors);
		proxyResolver.resolve(p);
	}

	private int contraste(Couleur c) {
		int ct = 0;
		double pow = 1;
		ct += Math.pow(Math.round(c.getR() - c.getG()), pow);
		ct += Math.pow(Math.round(c.getR() - c.getB()), pow);

		ct += Math.pow(Math.round(c.getG() - c.getR()), pow);
		ct += Math.pow(Math.round(c.getG() - c.getB()), pow);

		ct += Math.pow(Math.round(c.getB() - c.getR()), pow);
		ct += Math.pow(Math.round(c.getB() - c.getG()), pow);

		return ct;
	}

	@Override
	public int getNbColor() {
		return proxyResolver.getNbColor();
	}

	private void reduce(List<Couleur> couleurs) {
		List<Couleur> clone = new ArrayList<>(couleurs);
		for (Couleur c : clone) {
			List<Couleur> other = new ArrayList<>(couleurs);
			other.remove(c);
			for (Couleur rival : other) {
				double dist = this.distance.getdistance(c, rival) / 195075;
				if (dist < 0.00001) {
					System.out.println(rival + " vs " + c);
					couleurs.remove(rival);
				}
			}
		}
	}
}
