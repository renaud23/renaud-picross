package com.renaud.picross.game.modelView;

import java.awt.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Groupe;
import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.controller.Surface;

public class Compteur implements IDrawable, DrawOperationAware {

	private Surface surface;
	private Collection<Groupe> groupes;
	private boolean horizontal;
	private IDrawOperation op;
	private int nbCouleurs;
	private Map<Couleur, Integer> tailleInitiale = new HashMap<>();

	public Compteur(Surface surface, Collection<Groupe> groupes, boolean horizontal, int nbCouleurs) {
		this.groupes = groupes;
		this.surface = surface;
		this.horizontal = horizontal;
		this.nbCouleurs = nbCouleurs;
		
		for(Groupe g : groupes){
			tailleInitiale.put(g.getCouleur(), g.getTaille());
		}
	}

	public void refresh(Collection<Groupe> groupes) {
		this.groupes = groupes;
	}

	public Collection<Groupe> getGroupe() {
		return groupes;
	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
	}

	@Override
	public boolean isChange() {
		return false;
	}

	@Override
	public void setChange() {}
	
	public void incremente(Couleur c){
		for(Groupe g : groupes){
			if(g.getCouleur().equals(c) && g.getTaille() <(tailleInitiale.get(g.getCouleur()) - 1)){
				g.incremente();
			}
		}
	}
	
	public void decremente(Couleur c){
		for(Groupe g : groupes){
			if(g.getCouleur().equals(c) && g.getTaille() > 0){
				g.decremente();
			}
		}
	}

	@Override
	public void draw() {
		op.drawRect(Color.black, surface.getX(), surface.getY(), surface.getLargeur(), surface.getHauteur());
		int l = 0, h = 0, x = 0, y = 0, p = 0;
		int i = 0;
		for (Groupe g : groupes) {
			if (this.horizontal) {
				l = Math.min(surface.getHauteur(), surface.getLargeur() / nbCouleurs);
				h = surface.getHauteur();
				x = surface.getX() + 1 + i * l;
				y = surface.getY() + 1;
			}
			else {
				l = surface.getLargeur();
				h = Math.min(surface.getLargeur(), surface.getHauteur() / nbCouleurs);
				x = surface.getX() + 1;
				y = surface.getY() + 1 + i * h;
			}
			int reste = tailleInitiale.get(g.getCouleur()) - g.getTaille();
			if(reste >= 0){
				Couleur c = g.getCouleur();
				op.fillRect(new Color(c.getRgba()), x, y, l - 1, h - 1, 1.0f);
	
				if (g.isContinu()) {
					op.drawRect(Color.yellow, x, y, l - 2, h - 2);
				}
				
	
				if (this.horizontal) {
					op.drawChar(String.valueOf(reste == 0 ? g.getTaille() : reste), x, y + (int) (l * 0.8), (int) (l * 0.7));
				}
				else {
					op.drawChar(String.valueOf(reste == 0 ? g.getTaille() : reste), x, y + (int) (h * 0.8), (int) (h * 0.7));
				}
			}
			i++;
		}
	}
}
