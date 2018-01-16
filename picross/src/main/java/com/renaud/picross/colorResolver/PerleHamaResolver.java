package com.renaud.picross.colorResolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;

public class PerleHamaResolver implements ColorResolver {

	private int nbColor = 5;
	private Picross picross;
	private ProxyResolver proxyResolver;
	private DistanceResolver distance;
	private double sensibilite = 0.01;

	public PerleHamaResolver(Picross p, DistanceResolver distance, int nbColor) {
		this.picross = p;
		this.proxyResolver = new ProxyResolver(distance);
		this.nbColor = nbColor;
		this.distance = distance;
	}
	
	public PerleHamaResolver(Picross p, DistanceResolver distance, int nbColor, double sensibilite) {
		this.picross = p;
		this.proxyResolver = new ProxyResolver(distance);
		this.nbColor = nbColor;
		this.distance = distance;
		this.sensibilite = sensibilite;
	}

	private Map<Couleur, Integer> makeMap() {
		Map<Couleur, Integer> map = new HashMap<>();
		for (int j = 0; j < (picross.getLargeur() * picross.getHauteur()); j++) {
			Couleur c = picross.getPixel(j % picross.getLargeur(), j / picross.getLargeur());
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
			Couleur who = Couleur.NOIR;
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
		this.reduce(colors, this.sensibilite);
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

	private void reduce(List<Couleur> couleurs, double sensibilite) {
		List<Couleur> clone = new ArrayList<>(couleurs);
		for (Couleur c : clone) {
			List<Couleur> other = new ArrayList<>(couleurs);
			other.remove(c);
			for (Couleur rival : other) {
				if(couleurs.contains(c)){
					double dist = this.distance.getdistance(c, rival) / 195075;
					if (dist < sensibilite) {
						couleurs.remove(rival);
					}
				}
			}
		}
	}
}
