package com.renaud.picross.colorResolver;

import com.renaud.picross.model.Couleur;

public class DistancePonderee implements DistanceResolver {

	@Override
	public double getdistance(Couleur a, Couleur b) {
		double ta = a.getR() + a.getG() + a.getB();
		double tb = b.getR() + b.getG() + b.getB();
		double r = a.getR() / ta;
		r -= b.getR() / tb;
		r *= r;
		double g = a.getG() / ta;
		g -= b.getG() / tb;
		g *= g;
		double bl = a.getB() / ta;
		bl -= b.getB() / tb;
		bl *= bl;

		return r + g + bl;
	}

}
