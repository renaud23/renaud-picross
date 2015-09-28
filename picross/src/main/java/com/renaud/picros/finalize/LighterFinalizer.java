package com.renaud.picros.finalize;

import java.awt.Color;

import com.renaud.picross.model.Couleur;

public class LighterFinalizer extends AbstractFinalizer{

	private double factor;
	
	public LighterFinalizer(double factor) {
		this.factor = factor;
	}


	protected Couleur change(Couleur c){

		double r = c.getR()* (1 + factor);
		double g = c.getG()* (1 + factor);
		double b = c.getB()* (1 + factor);
		r = Math.min(255, r);
		g = Math.min(255, g);
		b = Math.min(255, b);
		
		Couleur n = new Couleur((int)r,(int)g,(int)b);
		return n;
	}
	
	
	
}
