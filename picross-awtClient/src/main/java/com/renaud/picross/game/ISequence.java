package com.renaud.picross.game;

import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.controller.IController;

public interface ISequence extends IDrawable, DrawOperationAware {

	public void activate();

	public boolean isFinished();

	public IController getController();
}
