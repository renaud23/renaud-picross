package com.renaud.picross.game.modelView;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.controller.IController;
import com.renaud.picross.view.controller.RectangularController;
import com.renaud.picross.view.controller.Surface;
import com.renaud.picross.view.tools.Observable;
import com.renaud.picross.view.tools.ObservableComportement;
import com.renaud.picross.view.tools.Observer;

public class ColorChooser implements IDrawable, DrawOperationAware, Observer {
	
	private Surface surface;
	private List<Couleur> couleurs;
	private HashMap<Couleur, IController> controllers = new HashMap<>();
	private Observable comportement = new ObservableComportement();
	private IDrawOperation op;
	private Couleur couleurChoice;
	private RectangularController gomme;

	public ColorChooser(Surface surface, List<Couleur> couleurs) {
		this.couleurs = couleurs;
		this.surface = surface;
		
		double h = surface.getHauteur() - 1;
		double l = (surface.getLargeur() - 2 ) / (couleurs.size() + 1);
		int i = 0;
		for(Couleur c : couleurs){
			RectangularController rc = new RectangularController(
					new Surface((int) (surface.getX()+1 + l * i), surface.getY()+1, (int)l ,(int)h), c);
			controllers.put(c, rc);
			rc.addObserver(this);
			i++;
		}
		gomme = new RectangularController(
				new Surface((int) (surface.getX()+1 + l * couleurs.size()), surface.getY()+1, (int)l ,(int)h), Color.green);
		gomme.addObserver(this);
	}


	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
	}

	@Override
	public boolean isChange() {
		return false;
	}

	@Override
	public void setChange() {}

	@Override
	public void draw() {
		this.op.drawRect(Color.black, surface.getX(), surface.getY(), surface.getLargeur() - 1,  surface.getHauteur());
		for(Couleur c : controllers.keySet()){
			Surface s = controllers.get(c).getSurface();
			this.op.fillRect(new Color(c.getRgba()), s.getX(), s.getY(), s.getLargeur(), s.getHauteur(), 1.0f);
		}
	}


	@Override
	public void notify(String message, Object arg) {
		if(message.equals(RectangularController.RV_CLICK)){
			System.out.println(arg);
			
		}
		
	}

	
	public Couleur getCouleurChoice() {
		return couleurChoice;
	}


	public void setCouleurChoice(Couleur couleurChoice) {
		this.couleurChoice = couleurChoice;
	}


	public Collection<IController> getControllers(){
		Collection<IController> c = new ArrayList<>();
		c.addAll(this.controllers.values());
		c.add(gomme);
		return c;
	}
}
