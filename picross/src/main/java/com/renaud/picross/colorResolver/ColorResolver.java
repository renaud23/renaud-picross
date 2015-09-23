package com.renaud.picross.colorResolver;

import com.renaud.picross.model.Picross;

public interface ColorResolver {

	public void resolve(Picross p);

	public int getNbColor();
}
