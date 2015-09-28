package com.renaud.picros.finalize;

import java.awt.Color;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;

public abstract class AbstractFinalizer implements Finalizer{
	@Override
	public void finalize(Picross p) {
		for (int j = 0; j < (p.getLargeur() * p.getHauteur()); j++) {
			Couleur c = p.getPixel(j % p.getLargeur(), j / p.getLargeur());
			Couleur n = change(c);
			p.setColor(n, j % p.getLargeur(), j / p.getLargeur());
		}
	}
	
	protected abstract Couleur change(Couleur c);
}
