package com.renaud.picross.colorResolver;

import com.renaud.picross.model.Couleur;

public class DistanceMixte implements DistanceResolver {

	private DistanceResolver simple = new DistanceSimple();
	private DistanceResolver ponderee = new DistancePonderee();

	@Override
	public double getdistance(Couleur a, Couleur b) {
		return simple.getdistance(a, b) + ponderee.getdistance(a, b);
	}

}
