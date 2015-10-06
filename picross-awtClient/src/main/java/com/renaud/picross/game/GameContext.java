package com.renaud.picross.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.controller.IController;
import com.renaud.picross.view.controller.NullController;

public class GameContext implements ISequence, MouseMotionListener, MouseListener {

	private ISequence sequence = new NullSequence();

	@Override
	public void activate() {
		sequence.activate();
	}

	@Override
	public boolean isFinished() {
		return this.sequence.isFinished();
	}

	public ISequence getSequence() {
		return sequence;
	}

	public void setSequence(ISequence sequence) {
		this.sequence = sequence;
	}

	/* */

	@Override
	public void setDrawOperation(IDrawOperation op) {
		sequence.setDrawOperation(op);
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

	@Override
	public void draw() {
		sequence.draw();

	}

	/* controller */

	@Override
	public void mouseDragged(MouseEvent e) {
		this.sequence.getController().mouseDragged(e.getX(), e.getY());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		this.sequence.getController().mouseMoved(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			this.sequence.getController().mouseLeftClick(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.sequence.getController().mouseEntered(e.getX(), e.getY());

	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.sequence.getController().mouseExited(e.getX(), e.getY());

	}

	@Override
	public IController getController() {
		return new NullController();
	}

}
