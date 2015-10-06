package com.renaud.picross.view.controller;

public class NullController implements IController {

	@Override
	public int compareTo(IController o) {
		return 0;
	}

	@Override
	public void mouseEntered(int x, int y) {}

	@Override
	public void mouseExited(int x, int y) {}

	@Override
	public void mouseMoved(int x, int y) {}

	@Override
	public void mouseLeftClick(int x, int y) {}

	@Override
	public void mouseDragged(int x, int y) {}

	@Override
	public Surface getSurface() {
		return Surface.SURFACE_VIDE;
	}

}
