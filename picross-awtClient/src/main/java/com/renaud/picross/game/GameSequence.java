package com.renaud.picross.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.game.modelView.Cellule;
import com.renaud.picross.game.modelView.ColorChooser;
import com.renaud.picross.model.Couleur;
import com.renaud.picross.model.Picross;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.controller.IController;
import com.renaud.picross.view.controller.RectangularController;
import com.renaud.picross.view.controller.RootController;
import com.renaud.picross.view.controller.Surface;
import com.renaud.picross.view.tools.Observer;

public class GameSequence implements ISequence, Observer {
	
	private IDrawOperation op;
	private RootController controller;
	private Picross picross;
	private List<Cellule> cellules = new ArrayList<>();
	private Cellule focused;
	private ColorChooser chooser;

	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}

	public GameSequence(RootController controller) {
		this.controller = controller;
	}

	private void prepare() {
		double marge = 10.0;
		double panelHau = 30;
		double colorChooserHau = 100;
		double lar = controller.getSurface().getLargeur() - marge * 2.0;
		double hau = controller.getSurface().getHauteur() - marge * 2.0 - colorChooserHau;
		
		double min = Math.min(lar, hau);
		double max = Math.max(lar, hau);
		double ref1, ref2;
		if(controller.getSurface().getLargeur() < controller.getSurface().getHauteur()){
			ref1 =  (min / picross.getLargeur());
			ref2 =  (max / picross.getHauteur());
		}else{
			ref1 =  (min / picross.getHauteur());
			ref2 =  (max / picross.getLargeur());
		}
		
		int celSize = (int) ref1;//(int) Math.min(ref1, ref2);
		
	
		for (int i = 0; i < picross.getHauteur(); i++) {
			for (int j = 0; j < picross.getLargeur(); j++) {
				Couleur c = picross.getPixel(j, i);
				Color co = new Color(0,0,0,0);
				Cellule cel = new Cellule(co,
						(int)marge + j * celSize,
						(int)(marge + i * celSize + colorChooserHau), celSize, celSize);
				cel.setPicrossX(j);
				cel.setPicrossY(i);
				cellules.add(cel);
				cel.getController().addObserver(this);
				controller.addController(cel.getController());
			}
		}
		this.chooser = new ColorChooser(new Surface(0, 0, controller.getSurface().getLargeur(), (int) colorChooserHau), picross.getCouleurs());
		controller.addAllController(chooser.getControllers());
	}

	@Override
	public boolean isFinished() {
		return false;
	}

	public void setController(RootController controller) {
		this.controller = controller;
	}

	@Override
	public boolean isChange() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	/* rendering */

	@Override
	public void draw() {
		this.op.clean();
		this.chooser.draw();
		for (Cellule c : cellules) {
			c.draw();
		}
		if(focused != null){
			Surface s = focused.getController().getSurface();
			op.drawRect(Color.red, s.getX(), s.getY(),  s.getLargeur(),  s.getHauteur());
		}
	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
		this.chooser.setDrawOperation(op);
		for (Cellule c : cellules) {
			c.setDrawOperation(op);
		}
	}

	@Override
	public IController getController() {
		return this.controller;
	}

	public void setPicross(Picross picross) {
		this.picross = picross;
		this.prepare();
	}

	@Override
	public void notify(String message, Object arg) {
		if(message.equals(RectangularController.RV_CLICK)){
			if(arg instanceof Cellule){
				Cellule cel = (Cellule) arg;
				Couleur coul = chooser.getCouleurChoice();
				if(!coul.equals(Couleur.NULL)){
					cel.setColor(new Color(coul.getRgba()));
				}else{
					cel.setColor(new Color(0,0,0,0));
				}
			}
//			picross.getPixel(cel.getPicrossX(), cel.getPicrossY())
		}else if(message.equals(RectangularController.RV_FOCUSED)){
			if(arg instanceof Cellule){
				focused = (Cellule) arg;
			}
		}else if(message.equals(RectangularController.RV_UNFOCUSED)){
			if(arg instanceof Cellule){
				focused = null;
			}
		}
		
	}
}
