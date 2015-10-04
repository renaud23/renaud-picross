package com.renaud.picross.view.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractController implements IController {

	private List<IController> controllers = new ArrayList<>();
	protected int mouseX;
	protected int mouseY;
	protected int precx;
	protected int precy;
	protected Surface surface;
	protected IController current;

	public AbstractController(Surface surface) {
		this.surface = surface;
	}

	public void addController(IController controller) {
		controllers.add(controller);
	}
	
	public void addAllController(Collection<IController> controllers) {
		for(IController c : controllers){
			this.addController(c);
		}
	}

	public void removeController(IController controller) {
		controllers.add(controller);
	}

	private IController getCandidat() {
		IController winner = null;
		
			for (IController c : controllers) {
				if (c.getSurface().isIn(mouseX, mouseY)) {
					if (winner == null || c.compareTo(winner) > 0) {
						winner = c;
					}
				}
			}
	
		return winner;
	}

	private void checkPosition(int x, int y) {
		precx = mouseX;
		precy = mouseY;
		mouseX = x;
		mouseY = y;
	}

	@Override
	public int compareTo(IController o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mouseEntered(int x, int y) {}

	@Override
	public void mouseExited(int x, int y) {}

	@Override
	public void mouseMoved(int x, int y) {
		checkPosition(x, y);
		IController candidat = this.getCandidat();
		if (candidat != null) {
			if (current == null) {
				current = candidat;
				candidat.mouseEntered(mouseX, mouseY);
			}
			else
				if (current != candidat) {
					current.mouseExited(mouseX, mouseY);
					candidat.mouseEntered(mouseX, mouseY);
					current = candidat;
				}
				else {
					candidat.mouseMoved(mouseX, mouseY);
				}
		}
		else {
			if (current != null) {
				current.mouseExited(mouseX, mouseY);
				current = null;
			}
		}
	}

	@Override
	public void mouseLeftClick(int x, int y) {
		checkPosition(x, y);
		IController candidat = this.getCandidat();
		if (candidat != null) {
			candidat.mouseLeftClick(x, y);
		}
	}

	@Override
	public Surface getSurface() {
		return surface;
	}
}
