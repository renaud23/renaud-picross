package hama.drawer;

import java.awt.Color;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.JImageBuffer;

import hama.Hama;
import hama.Perle;
import hama.view.Action;
import hama.view.HamaGame.HamaRenew;
import hama.view.PaletteView.PerleOver;
import hama.view.StoreObserver;

public class HamaDrawer implements IDrawable, DrawOperationAware, StoreObserver {

	private IDrawOperation drawer;
	private Perle PerleOver = null;
	private Hama hama;
	private IDrawOperation buffer;
	private int margeX = 5;
	private int margeY = 5;
	private int pixelSize = 5;
	private int marge = 1;
	private boolean filled = false;

	public HamaDrawer(Hama hama) {
		this.hama = hama;
		buffer = new JImageBuffer(Color.white, hama.getLargeur() * pixelSize, hama.getHauteur() * pixelSize);
	}

	public HamaDrawer(Hama hama, int margeX, int margeY, int pixelSize, int marge) {
		this.hama = hama;
		this.margeX = margeX;
		this.margeY = margeY;
		this.pixelSize = pixelSize;
		buffer = new JImageBuffer(Color.white, hama.getLargeur() * pixelSize, hama.getHauteur() * pixelSize);
		this.marge = marge;
	}

	public void setDrawOperation(IDrawOperation op) {
		this.drawer = op;
	}

	@Override
	public boolean isChange() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		if (!filled) {
			buffer.transparentClean();
			for (int i = 0; i < hama.getHauteur(); i++) {
				for (int j = 0; j < hama.getLargeur(); j++) {
					int xi = j * pixelSize;
					int yi = i * pixelSize;
					// System.out.println(j + " " + i + " " + hama.getPerle(j, i));
					Couleur couleur = hama.getPerle(j, i).getCouleur();

					Color color = new Color(couleur.getR(), couleur.getG(), couleur.getB(), couleur.getAlpha());

					if (PerleOver != null && couleur.equals(PerleOver.getCouleur())) {
						buffer.drawRect(Color.white, xi, yi, pixelSize - marge - 1, pixelSize - marge - 1);
						buffer.fillRect(color, xi, yi, pixelSize - marge, pixelSize - marge, 0.3f);
					}
					else {
						buffer.fillRect(color, xi, yi, pixelSize - marge, pixelSize - marge, 1.0f);
					}
				}
			}
			filled = true;
		}
		this.drawer.drawImage(buffer.getImage(), margeX, margeY, 0, 0, 0, 1.0, 1.0f);

	}

	@Override
	public void notify(Action action) {
		if (action instanceof HamaRenew) {
			this.hama = ((HamaRenew) action).getHama();
			buffer = new JImageBuffer(Color.white, hama.getLargeur() * pixelSize, hama.getHauteur() * pixelSize);
			filled = false;
		}
		else
			if (action instanceof PerleOver) {
				PerleOver = ((PerleOver) action).getPerle();
				filled = false;
			}
	}

}
