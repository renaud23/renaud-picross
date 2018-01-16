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

		return Math.sqrt(2l * r + 4l * g + 3l * bl);
	}

	@Override
	public double getMaxValue() {
		return 195075l;
	}

}
