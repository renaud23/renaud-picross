package com.renaud.picross.view.tools;

public interface Observable {
	void addObserver(Observer o);
	void notifyAll_(String message, Object o);
}
