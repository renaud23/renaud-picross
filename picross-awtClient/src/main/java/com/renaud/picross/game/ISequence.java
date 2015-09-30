package com.renaud.picross.game;

import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;

public interface ISequence {
	public void activate();
	public boolean isFinished();
}
