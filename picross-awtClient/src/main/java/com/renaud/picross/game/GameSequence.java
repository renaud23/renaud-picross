package com.renaud.picross.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.game.element.Cellule;
import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.controller.IController;
import com.renaud.picross.view.controller.RootController;

public class GameSequence implements ISequence {

	private RootController controller;
	private Picross picross;
	private List<Cellule> cellules = new ArrayList<>();

	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}

	public GameSequence(RootController controller) {
		this.controller = controller;

	}

	private void prepare() {
		double marge = 10.0;
		double panelHau = 30;
		double lar = controller.getSurface().getLargeur() - marge * 2.0;
		double hau = controller.getSurface().getHauteur() - marge * 2.0;
		
		double min = Math.min(lar, hau);
		double max = Math.max(lar, hau);
		double ref1, ref2;
		if(controller.getSurface().getLargeur() < controller.getSurface().getHauteur()){
			ref1 =  (min / picross.getLargeur());
			ref2 =  (max / picross.getHauteur());
		}else{
			ref1 =  (min / picross.getHauteur());
			ref2 =  (max / picross.getLargeur());
		}
		
		
		
		int celSize = (int) ref1;//(int) Math.min(ref1, ref2);
		
	
		for (int i = 0; i < picross.getHauteur(); i++) {
			for (int j = 0; j < picross.getLargeur(); j++) {
				Couleur c = picross.getPixel(j, i);
				Color co = new Color(c.getRgba());
				Cellule cel = new Cellule(co,(int)marge + j * celSize,(int)marge + i * celSize, celSize, celSize);
				cellules.add(cel);
				controller.addController(cel);
			}
		}
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	public void setController(RootController controller) {
		this.controller = controller;
	}

	@Override
	public boolean isChange() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	/* rendering */

	@Override
	public void draw() {
		for (Cellule c : cellules) {
			c.draw();
		}
	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		for (Cellule c : cellules) {
			c.setDrawOperation(op);
		}
	}

	@Override
	public IController getController() {
		return this.controller;
	}

	public void setPicross(Picross picross) {
		this.picross = picross;
		this.prepare();
	}
}
