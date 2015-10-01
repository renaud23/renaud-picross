package com.renaud.picross.game;

import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.controller.IController;
import com.renaud.picross.view.controller.NullController;

public class NullSequence implements ISequence {

	private IController controller = new NullController();

	@Override
	public void activate() {}

	@Override
	public boolean isFinished() {
		return false;
	}

	@Override
	public boolean isChange() {
		return false;
	}

	@Override
	public void setChange() {}

	@Override
	public void draw() {}

	@Override
	public void setDrawOperation(IDrawOperation op) {}

	@Override
	public IController getController() {
		return controller;
	}

}
