package com.renaud.picros.finalize;

import java.awt.Color;

import com.renaud.picross.model.Couleur;

public class ContrasterFinalizer extends AbstractFinalizer{


	
	protected Couleur change(Couleur c){

		double r = c.getR();
		double g = c.getG();
		double b = c.getB();
		double tot = r + b + g;
		
		double alpha = tot > 375 ? 1.5 : 0.5;
		
		r = Math.min(255, r * alpha);
		g = Math.min(255, g * alpha);
		b = Math.min(255, b * alpha);
		
		Couleur n = new Couleur((int)r,(int)g,(int)b);
		
		return n;
	}

}
