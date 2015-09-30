package com.renaud.picross.game;



public interface IController extends Comparable<IController>{
	void mouseEntered(int x,int y);
	void mouseExited(int x,int y);
	void mouseMoved(int x,int y);
	void mouseLeftClick(int x,int y);
	Surface getSurface();
}
