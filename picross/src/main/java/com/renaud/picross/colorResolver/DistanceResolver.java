package com.renaud.picross.colorResolver;

import com.renaud.picross.model.Couleur;

public interface DistanceResolver {

	public double getMaxValue();

	public double getdistance(Couleur a, Couleur b);
}
