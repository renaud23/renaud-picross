package com.renaud.picros.finalize;

import com.renaud.picross.model.Couleur;


public class ColorFilter extends AbstractFinalizer{
	
	private boolean r;
	private boolean g;
	private boolean b;	
	
	public ColorFilter(boolean r, boolean g, boolean b) {
		this.r = r;
		this.b = b;
		this.g = g;
	}


	@Override
	protected Couleur change(Couleur c) {
		int rgb = c.getRgba();
		int ri = r ? 0: ((rgb >> 16) & 0xff);
		int gi = g ? 0: ((rgb >> 8) & 0xff);
		int bi = b ? 0: ((rgb >> 0) & 0xff);
		
		return new Couleur((rgb & 0xff000000) | (ri << 16) | (gi << 8) | (bi << 0));	   
	}



	
	
}
