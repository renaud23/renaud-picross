package com.renaud.picross.colorResolver;

import com.renaud.picross.model.Couleur;

public class DistanceSimple implements DistanceResolver {

	@Override
	public double getdistance(Couleur a, Couleur b) {
		double r = a.getR();
		r -= b.getR();
		r *= r;
		double g = a.getG();
		g -= b.getG();
		g *= g;
		double bl = a.getB();
		bl -= b.getB();
		bl *= bl;

		return r + g + bl;
	}

}
