package com.renaud.picross.colorResolver;

import java.awt.Color;

public class DistancePonderee implements DistanceResolver {

	@Override
	public double getdistance(Color a, Color b) {
		double ta = a.getRed() + a.getGreen() + a.getBlue();
		double tb = b.getRed() + b.getGreen() + b.getBlue();
		double r = a.getRed() / ta;
		r -= b.getRed() / tb;
		r *= r;
		double g = a.getGreen() / ta;
		g -= b.getGreen() / tb;
		g *= g;
		double bl = a.getBlue() / ta;
		bl -= b.getBlue() / tb;
		bl *= bl;

		return r + g + bl;
	}

}
