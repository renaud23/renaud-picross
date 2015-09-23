package com.renaud.picros.finalize;

import java.awt.Color;

public class ContrasterFinalizer extends AbstractFinalizer{


	
	protected Color change(Color c){

		double r = c.getRed();
		double g = c.getGreen();
		double b = c.getBlue();
		double tot = r + b + g;
		
		double alpha = tot > 375 ? 1.5 : 0.5;
		
		r = Math.min(255, r * alpha);
		g = Math.min(255, g * alpha);
		b = Math.min(255, b * alpha);
		
		Color n = new Color((int)r,(int)g,(int)b);
		System.out.println(n);
		return n;
	}

}
