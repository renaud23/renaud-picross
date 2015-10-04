package com.renaud.picross.view.tools;

import java.util.ArrayList;
import java.util.List;

public class ObservableComportement implements Observable{
	
	private List<Observer> observers = new ArrayList<>();
	
	public void addObserver(Observer o){
		this.observers.add(o);
	}
	
	public void notifyAll_(String message, Object o){
		for(Observer op : observers){
			op.notify(message, o);
		}
	}
}
