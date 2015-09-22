package com.renaud.picross.colorResolver;

import java.awt.Color;

public class DistanceSimple implements DistanceResolver {

	@Override
	public double getdistance(Color a, Color b) {
		double r = a.getRed();
		r -= b.getRed();
		r *= r;
		double g = a.getGreen();
		g -= b.getGreen();
		g *= g;
		double bl = a.getBlue();
		bl -= b.getBlue();
		bl *= bl;

		return r + g + bl;
	}

}
