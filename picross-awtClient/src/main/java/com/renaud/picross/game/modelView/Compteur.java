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
	private Color backgroundColor = new Color(255, 0, 255);
	
	private Map<Couleur, Integer> choix = new HashMap<>();
	

	public Compteur(Surface surface, Collection<Groupe> groupes, boolean horizontal, int nbCouleurs) {
		this.groupes = groupes;
		this.surface = surface;
		this.horizontal = horizontal;
		this.nbCouleurs = nbCouleurs;
	
		for(Groupe g : groupes){
			this.choix.put(g.getCouleur(), 0);
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
		Integer how = choix.get(c);
		if(how != null){
			choix.put(c, how + 1);
		}else{
			choix.put(c, 1);
		}
	}
	
	public void decremente(Couleur c){
		Integer how = choix.get(c);
		if(how != null){
			choix.put(c, how - 1);
		}
	}

	@Override
	public void draw() {
		op.fillRect(backgroundColor, surface.getX(), surface.getY(), surface.getLargeur(), surface.getHauteur(), 1.0f);
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
			

			Integer how = choix.get(g.getCouleur()) == 0 ? g.getTaille() : Math.max(g.getTaille() - choix.get(g.getCouleur()), 0);
			
			if(how > 0){
				Couleur c = g.getCouleur();
				op.fillRect(new Color(c.getRgba()), x, y, l - 1, h - 1, 1.0f);
	
				if (g.isContinu()) {
					op.drawRect(Color.yellow, x, y, l - 2, h - 2);
				}
				
	
				if (this.horizontal) {
					op.drawChar(String.valueOf(how), x, y + (int) (l * 0.8), (int) (l * 0.7));
				}
				else {
					op.drawChar(String.valueOf(how), x, y + (int) (h * 0.8), (int) (h * 0.7));
				}
			
			}
			
			i++;
		}
	}
}
