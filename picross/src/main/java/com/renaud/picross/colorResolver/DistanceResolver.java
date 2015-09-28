package com.renaud.picross.colorResolver;

import java.awt.Color;

import com.renaud.picross.model.Couleur;

public interface DistanceResolver {

	public double getdistance(Couleur a, Couleur b);
}
