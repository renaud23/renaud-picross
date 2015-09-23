package com.renaud.picross.colorResolver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.renaud.picross.model.Picross;

public class ProxyResolver implements ColorResolver {

	private List<Color> model = new ArrayList<Color>();
	private DistanceResolver distance;
	private Set<Color> resolved = new HashSet<>();

	public ProxyResolver(DistanceResolver distance) {
		this.distance = distance;
		model.add(new Color(200, 250, 200));
		model.add(new Color(250, 200, 200));
		model.add(new Color(200, 200, 250));
		model.add(new Color(250, 250, 200));
		model.add(new Color(200, 250, 250));
		model.add(new Color(250, 200, 250));

		model.add(new Color(150, 200, 150));
		model.add(new Color(200, 150, 150));
		model.add(new Color(150, 150, 200));
		model.add(new Color(200, 200, 150));
		model.add(new Color(150, 200, 200));
		model.add(new Color(200, 150, 200));

		model.add(new Color(100, 150, 100));
		model.add(new Color(150, 100, 100));
		model.add(new Color(100, 100, 150));
		model.add(new Color(150, 150, 100));
		model.add(new Color(100, 150, 150));
		model.add(new Color(150, 100, 150));

		// model.add(new Color(0, 50, 0));
		// model.add(new Color(50, 0, 0));
		// model.add(new Color(0, 0, 50));
		// model.add(new Color(50, 50, 0));
		// model.add(new Color(0, 50, 50));
		// model.add(new Color(50, 0, 50));

		model.add(Color.black);
		model.add(Color.white);

		model.add(Color.green);
		model.add(Color.blue);
		model.add(Color.red);
		model.add(Color.yellow);
		model.add(Color.orange);
		model.add(Color.pink);
		model.add(Color.magenta);
		model.add(Color.cyan);

		model.add(Color.gray);
		model.add(Color.lightGray);
		model.add(Color.darkGray);

	}

	public List<Color> getModel() {
		return model;
	}

	public void setModel(List<Color> model) {
		this.model = model;
	}

	public void resolve(Picross p) {

		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			double normal = 9999999;
			Color who = null;
			Color c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			for (Color m : model) {
				if (!c.equals(m)) {
					double nor = distance.getdistance(c, m);
					if (nor < normal) {
						who = m;
						normal = nor;
						if (!resolved.contains(who)) {
							resolved.add(who);
						}
					}
				}
			}
			p.setColor(who, j % p.getLargeur(), j / p.getLargeur());

		}
	}

	public int getNbColor() {
		return resolved.size();
	}
}
