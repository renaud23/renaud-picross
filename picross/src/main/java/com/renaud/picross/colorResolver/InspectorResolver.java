package com.renaud.picross.colorResolver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.renaud.picross.model.Picross;

public class InspectorResolver implements ColorResolver {

	private int nbColor = 5;
	private Picross p;
	private DistanceResolver distance;
	private ProxyResolver proxyResolver;

	public InspectorResolver(Picross p, DistanceResolver distance, int nbColor) {
		this.p = p;
		this.distance = distance;
		this.proxyResolver = new ProxyResolver(distance);
		this.nbColor = nbColor;
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

	public void resolve(Picross p) {
		List<Color> colors = new ArrayList<>();
		Map<Color, Integer> map = makeMap();
		while (colors.size() < nbColor && map.size() > 0) {
			int best = -1;
			Color who = null;
			for (Color c : map.keySet()) {
				int count = contraste(c);
				if (count > best) {
					best = count;
					who = c;
				}
			}
			colors.add(who);
			map.remove(who);
		}
		proxyResolver.setModel(colors);
		proxyResolver.resolve(p);
	}

	private int contraste(Color c) {
		int ct = 0;
		double pow = 1;
		ct += Math.pow(Math.round(c.getRed() - c.getGreen()), pow);
		ct += Math.pow(Math.round(c.getRed() - c.getBlue()), pow);

		ct += Math.pow(Math.round(c.getGreen() - c.getRed()), pow);
		ct += Math.pow(Math.round(c.getGreen() - c.getBlue()), pow);

		ct += Math.pow(Math.round(c.getBlue() - c.getRed()), pow);
		ct += Math.pow(Math.round(c.getBlue() - c.getGreen()), pow);

		return ct;
	}

	@Override
	public int getNbColor() {
		return proxyResolver.getNbColor();
	}

}
