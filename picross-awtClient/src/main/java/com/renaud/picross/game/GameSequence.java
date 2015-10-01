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
		for (int i = 0; i < picross.getHauteur(); i++) {
			for (int j = 0; j < picross.getLargeur(); j++) {
				Couleur c = picross.getPixel(j, i);
				Color co = new Color(c.getRgba());
				Cellule cel = new Cellule(co, j * 10, i * 10, 10, 10);
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
