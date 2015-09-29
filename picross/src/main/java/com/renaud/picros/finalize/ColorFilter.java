package com.renaud.picros.finalize;

import com.renaud.picross.model.Couleur;

public class ColorFilter extends AbstractFinalizer {

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
		int ri = r ? 0 : c.getR();
		int gi = g ? 0 : c.getG();
		int bi = b ? 0 : c.getB();

		return new Couleur(ri, gi, bi);
	}

}
