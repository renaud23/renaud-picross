package com.renaud.picross.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.view.controller.IController;

public abstract class AbstractController implements IController, MouseMotionListener, MouseListener {

	private List<IController> controllers = new ArrayList<>();
	protected int x;
	protected int y;
	protected int precx;
	protected int precy;

	public void addController(IController controller) {
		controllers.add(controller);
	}

	public void removeController(IController controller) {
		controllers.add(controller);
	}

	private IController getCandidat() {
		IController winner = null;
		for (IController c : controllers) {
			if (c.getSurface().isIn(x, y)) {
				if (winner == null || c.compareTo(winner) > 0) {
					winner = c;
				}
			}
		}

		return winner;
	}

	private void checkPosition(MouseEvent e) {
		precx = x;
		precy = y;
		x = e.getX();
		y = e.getY();
	}

	@Override
	public int compareTo(IController o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		checkPosition(e);
		IController candidat = this.getCandidat();
		if (e.getButton() == MouseEvent.BUTTON1) {
			if (candidat != null) {
				candidat.mouseLeftClick(x, y);
			}
			else {
				this.mouseLeftClick(x, y);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		checkPosition(e);
		this.mouseEntered(x, y);

	}

	@Override
	public void mouseExited(MouseEvent e) {
		checkPosition(e);
		this.mouseExited(x, y);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		checkPosition(e);
		this.mouseMoved(x, y);
	}

	/*
	 * 
	 */

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent e) {
		checkPosition(e);
		this.mouseDragged(x, y);
	}
}
