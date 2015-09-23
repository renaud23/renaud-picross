package com.renaud.picros.finalize;

import java.awt.Color;

import com.renaud.picross.model.Picross;

public abstract class AbstractFinalizer implements Finalizer{
	@Override
	public void finalize(Picross p) {
		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			Color c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			Color n = change(c);
			p.setColor(n, j % p.getLargeur(), j / p.getLargeur());
		}
	}
	
	protected abstract Color change(Color c);
}
