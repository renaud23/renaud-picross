package com.renaud.picross.view;

import java.awt.Color;
import java.awt.Image;

import com.renaud.picross.model.Picross;

public class PicrosseDrawer implements IDrawable, DrawOperationAware {

	private IDrawOperation buffer;
	private Picross picross;
	private IDrawOperation drawer;
	private boolean start = false;
	private int pixelSize = 4;

	public PicrosseDrawer(Picross picross, Color color, int largeur, int hauteur, int pixelSize) {
		buffer = new JImageBuffer(color, largeur, hauteur);
		this.picross = picross;
		this.pixelSize = pixelSize;
	}

	public void render() {
		buffer.clean();
		for (int i = 0; i < picross.getHauteur(); i++) {
			for (int j = 0; j < picross.getLargeur(); j++) {
				buffer.fillRect(picross.getPixel(j, i), j * pixelSize, i * pixelSize, pixelSize - 1.0, pixelSize - 1.0, 1.0f);
			}
		}

	}

	public Image getImage() {
		return buffer.getImage();
	}

	public boolean isChange() {
		return true;
	}

	public void setChange() {
		// TODO Auto-generated method stub

	}

	public void draw() {
		this.drawer.clean();
		this.render();
		this.drawer.drawImage(buffer.getImage(), 0, 0, 0, 0, 0, 1.0, 1.0f);
	}

	public void setDrawOperation(IDrawOperation op) {
		this.drawer = op;
	}
}
