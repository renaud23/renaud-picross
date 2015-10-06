package com.renaud.picross.view.controller;

public interface IController extends Comparable<IController> {

	void mouseEntered(int x, int y);

	void mouseExited(int x, int y);

	void mouseMoved(int x, int y);

	void mouseLeftClick(int x, int y);

	void mouseDragged(int x, int y);

	Surface getSurface();
}
