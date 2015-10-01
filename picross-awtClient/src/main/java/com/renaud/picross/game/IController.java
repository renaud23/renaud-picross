package com.renaud.picross.game;

import com.renaud.picross.view.controller.Surface;



public interface IController extends Comparable<IController>{
	void mouseEntered(int x,int y);
	void mouseExited(int x,int y);
	void mouseMoved(int x,int y);
	void mouseLeftClick(int x,int y);
	Surface getSurface();
}
