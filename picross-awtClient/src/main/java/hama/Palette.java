package hama;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

import com.renaud.picross.model.Couleur;
import com.renaud.picross.view.DrawOperationAware;
import com.renaud.picross.view.IDrawOperation;
import com.renaud.picross.view.IDrawable;
import com.renaud.picross.view.JImageBuffer;

public class Palette implements IDrawable, DrawOperationAware {

	private boolean filled = false;
	private IDrawOperation drawer;
	private IDrawOperation buffer;
	private int posX;
	private int posY;
	private int size = 10;
	private int marge = 1;
	private Set<Couleur> couleurs = new HashSet<>();

	public void setDrawOperation(IDrawOperation op) {
		this.drawer = op;
	}

	public Palette(Set<Couleur> couleurs, int posX, int posY, int size) {
		this.couleurs = couleurs;
		this.posX = posX;
		this.posY = posY;
		this.size = size;

		buffer = new JImageBuffer(Color.WHITE, (size + 1) * couleurs.size(), size * 2);
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
			for (Couleur c : couleurs) {
				buffer.fillRect(new Color(c.getR(), c.getG(), c.getB()), i * (size + marge), 0, size, size, 1.0f);
				buffer.drawRect(Color.WHITE, i * (size + marge), 0, size - 1, size - 1);
				i++;
			}

		}
		this.drawer.drawImage(buffer.getImage(), posX, posY, 0, 0, 0, 1.0, 1.0f);
	}

}
