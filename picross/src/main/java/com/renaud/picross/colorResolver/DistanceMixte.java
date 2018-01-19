package com.renaud.picross.colorResolver;

import com.renaud.picross.model.Couleur;

public class DistanceMixte implements DistanceResolver {

	private double pond = 0.6;

	DistanceResolver ds = new DistanceSimple();

	@Override
	public double getdistance(Couleur a, Couleur b) {
		double s = ds.getdistance(a, b);
		return Math.sqrt(Math.pow(profil(a, b), 2) * pond + (1l - pond) * Math.pow(s, 2));
	}

	@Override
	public double getMaxValue() {
		return 195075l;
	}

	public double profil(Couleur a, Couleur b) {
		// R
		double rToB = a.getR() - a.getB() - b.getR() + b.getB();
		rToB *= rToB;
		double rToG = a.getR() - a.getG() - b.getR() + b.getG();
		rToG *= rToG;
		double bToG = a.getB() - a.getG() - a.getB() + b.getG();
		bToG *= bToG;

		return Math.sqrt(rToB + rToG + bToG);
	}

	public final static void main(String[] args) {
		Couleur a = new Couleur(255, 255, 0);
		Couleur b = new Couleur(0, 0, 0);
		DistanceMixte d = new DistanceMixte();
		System.out.println(d.profil(a, b));
	}

}
