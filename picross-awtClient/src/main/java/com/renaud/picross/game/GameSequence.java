package com.renaud.picross.game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.renaud.picross.game.modelView.Cellule;
import com.renaud.picross.game.modelView.ColorChooser;
import com.renaud.picross.game.modelView.Compteur;
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

	private List<Compteur> compteurLigne = new ArrayList<>();
	private List<Compteur> compteurColonne = new ArrayList<>();

	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}

	public GameSequence(RootController controller) {
		this.controller = controller;
	}

	private void prepare() {
		double marge = 10.0;
		double colorChooserHau = 100;
		double compteurTail = 100;
		double lar = controller.getSurface().getLargeur() - marge * 2.0 - compteurTail;
		double hau = controller.getSurface().getHauteur() - marge * 2.0 - colorChooserHau - compteurTail;

		double min = Math.min(lar, hau);
		double ref1 = 0;
		if (controller.getSurface().getLargeur() < controller.getSurface().getHauteur()) {
			ref1 = (min / picross.getLargeur());
		}
		else {
			ref1 = (min / picross.getHauteur());
		}

		int celSize = (int) ref1;// (int) Math.min(ref1, ref2);
		double xi, yi;
		for (int i = 0; i < picross.getHauteur(); i++) {
			int g = ((i % 5) == 4 ? 2 : 0) + 2 * (i / 5);
			yi = marge + i * celSize + colorChooserHau + compteurTail + g ;
			compteurLigne.add(i,
				new Compteur(new Surface((int) marge, (int) yi, (int) compteurTail, celSize),
					picross.getLigne(i), true, picross.getNbCouleur()));

			for (int j = 0; j < picross.getLargeur(); j++) {
				int v = ((j % 5) == 4 ? 2 : 0) + 2 * (j / 5);
				xi = marge + j * celSize + compteurTail + v;
				if(i == 0){
					compteurColonne.add(j,
					new Compteur(new Surface((int) xi, (int) (marge + colorChooserHau), celSize, (int) compteurTail),
						picross.getColonne(j), false, picross.getNbCouleur()));
				}

				Color co = new Color(0, 0, 0, 0);
				Cellule cel = new Cellule(co,
					(int) xi,
					(int) yi
					, celSize, celSize);
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
//		this.op.clean();
		this.chooser.draw();
		for (Cellule c : cellules) {
			c.draw();
		}
		for (Compteur cmp : compteurLigne) {
			cmp.draw();
		}
		for (Compteur cmp : compteurColonne) {
			cmp.draw();
		}

		if (focused != null) {
			Surface s = focused.getController().getSurface();
			op.drawRect(Color.red, s.getX(), s.getY(), s.getLargeur(), s.getHauteur());
		}

	}

	@Override
	public void setDrawOperation(IDrawOperation op) {
		this.op = op;
		this.chooser.setDrawOperation(op);
		for (Cellule c : cellules) {
			c.setDrawOperation(op);
		}
		for (Compteur cmp : compteurLigne) {
			cmp.setDrawOperation(op);
		}
		for (Compteur cmp : compteurColonne) {
			cmp.setDrawOperation(op);
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
		if (message.equals(RectangularController.RV_CLICK)) {
			if (arg instanceof Cellule) {
				Cellule cel = (Cellule) arg;
				this.checkColor(cel);
			}
		}
		else
			if (message.equals(RectangularController.RV_FOCUSED)) {
				if (arg instanceof Cellule) {
					focused = (Cellule) arg;
				}
			}
			else
				if (message.equals(RectangularController.RV_UNFOCUSED)) {
					if (arg instanceof Cellule) {
						focused = null;
					}
				}
				else
					if (message.equals(RectangularController.RV_DRAGGUED)) {
						if (arg instanceof Cellule) {
							this.checkColor((Cellule) arg);
						}
					}

	}

	private void checkColor(Cellule cel) {
		Couleur coul = chooser.getCouleurChoice();
		if (!coul.equals(Couleur.NULL)) {
			if (cel.getColor().getAlpha() == 0) {
				cel.setColor(new Color(coul.getRgba()));
				this.compteurColonne.get(cel.getPicrossX()).incremente(coul);
				this.compteurLigne.get(cel.getPicrossY()).incremente(coul);
			}
		}
		else {
			if(cel.getColor().getAlpha() != 0) {
				Couleur r = new Couleur(cel.getColor().getRed(), cel.getColor().getGreen(), cel.getColor().getBlue());
				this.compteurColonne.get(cel.getPicrossX()).decremente(r);
				this.compteurLigne.get(cel.getPicrossY()).decremente(r);
			}
			cel.setColor(new Color(0, 0, 0, 0));
		}
	}
}
