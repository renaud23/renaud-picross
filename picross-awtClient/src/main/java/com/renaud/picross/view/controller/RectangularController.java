package com.renaud.picross.view.controller;

import com.renaud.picross.view.tools.Observable;
import com.renaud.picross.view.tools.ObservableComportement;
import com.renaud.picross.view.tools.Observer;

public class RectangularController implements IController, Observable {

	public static final String RV_CLICK = "__RV_CLICK__";
	public static final String RV_FOCUSED = "__RV_FOCUSED__";
	public static final String RV_UNFOCUSED = "__RV_UNFOCUSED__";
	public static final String RV_DRAGGUED = "__RV_DRAGGUED__";

	private Surface surface;
	private Observable comportement = new ObservableComportement();
	private Object owner;

	public RectangularController(Surface surface, Object owner) {
		this.surface = surface;
		this.owner = owner;
	}

	@Override
	public void addObserver(Observer o) {
		comportement.addObserver(o);
	}

	@Override
	public void notifyAll_(String message, Object o) {
		comportement.notifyAll_(message, o);
	}

	@Override
	public Surface getSurface() {
		return this.surface;
	}

	@Override
	public int compareTo(IController o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void mouseEntered(int x, int y) {
		this.notifyAll_(RV_FOCUSED, owner);

	}

	@Override
	public void mouseExited(int x, int y) {
		this.notifyAll_(RV_UNFOCUSED, owner);

	}

	@Override
	public void mouseMoved(int x, int y) {}

	@Override
	public void mouseLeftClick(int x, int y) {
		this.notifyAll_(RV_CLICK, owner);
	}

	@Override
	public void mouseDragged(int x, int y) {
		this.notifyAll_(RV_DRAGGUED, owner);
	}
}
