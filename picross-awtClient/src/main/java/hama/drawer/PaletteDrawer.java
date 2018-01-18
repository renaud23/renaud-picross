package hama.drawer;

import java.awt.Color;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.JImageBuffer;

import hama.Nuancier;
import hama.view.Action;
import hama.view.HamaGame.NuancierRenew;
import hama.view.StoreObserver;

public class PaletteDrawer implements IDrawable, DrawOperationAware, StoreObserver {

	private Couleur couleurMouseOver = null;
	private boolean filled = false;
	private IDrawOperation drawer;
	private IDrawOperation buffer;
	private int posX;
	private int posY;
	private int size = 10;
	private int marge = 1;
	private Nuancier nuancier;
	private int MAX_COULEURS = 30;

	public void setDrawOperation(IDrawOperation op) {
		this.drawer = op;
	}

	public PaletteDrawer(Nuancier nuancier, int posX, int posY, int size) {
		this.nuancier = nuancier;
		this.posX = posX;
		this.posY = posY;
		this.size = size;

		buffer = new JImageBuffer(Color.WHITE, (size + 1) * MAX_COULEURS, size * 2);
	}

	@Override
	public boolean isChange() {
		return false;
	}

	@Override
	public void setChange() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		if (!filled) {
			buffer.transparentClean();
			filled = true;
			int i = 0;
			for (Couleur c : nuancier.getCouleursRef()) {
				buffer.fillRect(new Color(c.getR(), c.getG(), c.getB()), i * (size + marge), 0, size, size, 1.0f);

				if (nuancier.estUtilisee(c) && !c.equals(couleurMouseOver)) {
					buffer.drawRect(Color.yellow, i * (size + marge), 0, size - 1, size - 1);
				}

				i++;
			}

		}
		this.drawer.drawImage(buffer.getImage(), posX, posY, 0, 0, 0, 1.0, 1.0f);
	}

	public Couleur getCouleurActive() {
		return couleurMouseOver;
	}

	public void setCouleurActive(Couleur couleurActive) {
		filled = false;
		this.couleurMouseOver = couleurActive;
	}

	@Override
	public void notify(Action action) {
		if (action instanceof NuancierRenew) {
			filled = false;
		}

	}

}
