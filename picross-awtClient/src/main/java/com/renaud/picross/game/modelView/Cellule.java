package com.renaud.picross.game.modelView;

import java.awt.Color;

import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.controller.RectangularController;
import com.renaud.picross.view.controller.Surface;

public class Cellule implements IDrawable, DrawOperationAware {
	
	private IDrawOperation drawer;
	private Surface surface;
	private Color color;
	private int picrossX;
	private int picrossY;
	private RectangularController controller;


	public Cellule(Color color, int x, int y, int largeur, int hauteur) {
		this.surface = new Surface(x, y, largeur, hauteur);
		this.controller = new RectangularController(surface, this);
		this.color = color;
	}

	@Override
	public void setDrawOperation(IDrawOperation drawer) {
		this.drawer = drawer;
	}

	@Override
	public boolean isChange() {
		return false;
	}

	@Override
	public void setChange() {}

	@Override
	public void draw() {
		this.drawer.drawRect(Color.black, surface.getX(), surface.getY(), surface.getLargeur(), surface.getHauteur());
		this.drawer.fillRect(color, surface.getX()+ 1.0, surface.getY() + 1.0, surface.getLargeur() - 1.0, surface.getHauteur()-1.0, 1.0f);
	}

	public int getPicrossX() {
		return picrossX;
	}

	public void setPicrossX(int picrossX) {
		this.picrossX = picrossX;
	}

	public int getPicrossY() {
		return picrossY;
	}

	public void setPicrossY(int picrossY) {
		this.picrossY = picrossY;
	}

	public RectangularController getController() {
		return controller;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
