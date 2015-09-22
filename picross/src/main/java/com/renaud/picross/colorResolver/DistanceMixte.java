package com.renaud.picross.colorResolver;

import java.awt.Color;

public class DistanceMixte implements DistanceResolver{
	
	private DistanceResolver simple = new DistanceSimple();
	private DistanceResolver ponderee = new DistancePonderee();

	@Override
	public double getdistance(Color a, Color b) {
		return simple.getdistance(a, b) + ponderee.getdistance(a, b);
	}

}
