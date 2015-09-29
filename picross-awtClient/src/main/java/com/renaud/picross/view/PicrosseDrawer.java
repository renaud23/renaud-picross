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

	private int x;
	private int y;
	private boolean filled = false;

	public PicrosseDrawer(Picross picross, Color color, int largeur, int hauteur, int pixelSize) {
		buffer = new JImageBuffer(color, largeur, hauteur);
		this.picross = picross;
		this.pixelSize = pixelSize;
	}

	public PicrosseDrawer(Picross picross, Color color, int x, int y, int largeur, int hauteur, int pixelSize) {
		this.x = x;
		this.y = y;
		buffer = new JImageBuffer(color, largeur, hauteur);
		this.picross = picross;
		this.pixelSize = pixelSize;
	}

	public void render() {
		if (!filled) {
			buffer.transparentClean();
			for (int i = 0; i < picross.getHauteur(); i++) {
				for (int j = 0; j < picross.getLargeur(); j++) {

					int xi = j * pixelSize;
					int yi = i * pixelSize;
					buffer.fillRect(
						new Color(
							picross.getPixel(j, i).getR(),
							picross.getPixel(j, i).getG(),
							picross.getPixel(j, i).getB()), xi, yi, pixelSize - 1, pixelSize - 1, 1.0f);
				}
			}
			filled = true;
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
		this.render();
		this.drawer.drawImage(buffer.getImage(), x, y, 0, 0, 0, 1.0, 1.0f);
	}

	public void setDrawOperation(IDrawOperation op) {
		this.drawer = op;
	}
}
