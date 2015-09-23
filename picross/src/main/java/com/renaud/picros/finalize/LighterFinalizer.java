package com.renaud.picros.finalize;

import java.awt.Color;

public class LighterFinalizer extends AbstractFinalizer{

	private double factor;
	
	public LighterFinalizer(double factor) {
		this.factor = factor;
	}


	protected Color change(Color c){

		double r = c.getRed()* (1 + factor);
		double g = c.getGreen()* (1 + factor);
		double b = c.getBlue()* (1 + factor);
		r = Math.min(255, r);
		g = Math.min(255, g);
		b = Math.min(255, b);
		
		Color n = new Color((int)r,(int)g,(int)b);
		return n;
	}
	
	
	
}
