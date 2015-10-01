package com.renaud.picross.game.element;

import java.awt.Color;

import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.controller.IController;
import com.renaud.picross.view.controller.Surface;

public class Cellule implements IController, IDrawable, DrawOperationAware {

	private IDrawOperation drawer;
	private Surface surface;
	private Color color;
	private boolean focused;

	public Cellule(Color color, int x, int y, int largeur, int hauteur) {
		this.surface = new Surface(x, y, largeur, hauteur);
		this.color = color;
	}

	@Override
	public int compareTo(IController o) {
		// TODO Auto-generated method stub
		return 0;
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
	public void setChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {

		if (focused) {
			this.drawer.fillRect(color, surface.getX()+ 1.0, surface.getY() + 1.0, surface.getLargeur() - 1.0, surface.getHauteur()-1.0, 0.3f);
			this.drawer.drawRect(Color.white, surface.getX(), surface.getY(), surface.getLargeur()-1, surface.getHauteur()-1);
		}
		else {
			this.drawer.drawRect(Color.black, surface.getX(), surface.getY(), surface.getLargeur(), surface.getHauteur());
			this.drawer.fillRect(color, surface.getX()+ 1.0, surface.getY() + 1.0, surface.getLargeur() - 1.0, surface.getHauteur()-1.0, 1.0f);
//			
		}

	}

	@Override
	public void mouseEntered(int x, int y) {
		focused = true;
	}

	@Override
	public void mouseExited(int x, int y) {
		focused = false;
	}

	@Override
	public void mouseMoved(int x, int y) {
		// System.out.println(System.currentTimeMillis());

	}

	@Override
	public void mouseLeftClick(int x, int y) {

	}

	@Override
	public Surface getSurface() {
		return surface;
	}

}
