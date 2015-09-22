package com.renaud.picross.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import com.renaud.picross.generator.PicrossGenerator;
import com.renaud.picross.model.Picross;
import com.renaud.picross.tools.SimpleImageLoader;

public class Fenetre implements Iterable<IDrawable> {

	private JFrame frame;
	private int largeur = 800;
	private int hauteur = 600;

	private CanvasHwdBuffer buffer;
	private Timer timer;
	private List<IDrawable> drawables = new ArrayList<IDrawable>();

	public Fenetre(int largeur, int hauteur) {
		this.frame = new JFrame("Labyrinthe");
		this.frame.setIgnoreRepaint(true);
		this.frame.setVisible(true);
		this.frame.setPreferredSize(new Dimension(largeur, hauteur));
		this.largeur = largeur;
		this.hauteur = hauteur;

		this.buffer = new CanvasHwdBuffer(largeur, hauteur);
		this.frame.add((Component) this.buffer);
		this.buffer.createStrategy();

		this.frame.pack();
		this.frame.validate();

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.frame.setResizable(false);
		this.timer = new Timer();
	}

	public void setIconImage(Image image) {
		this.frame.setIconImage(image);
	}

	public void resize(int largeur, int hauteur) {
		this.largeur = largeur;
		this.hauteur = hauteur;
	}

	public void repaint() {
		this.frame.repaint();
	}

	public int getLargeur() {
		return largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public JFrame getFrame() {
		return frame;
	}

	public IDrawOperation getDrawOperation() {
		return this.buffer;
	}

	public BufferStrategy getStrategy() {
		return this.buffer.getBufferStrategy();
	}

	public void start() {

		final Fenetre f = this;

		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				f.getDrawOperation().clean();
				for (IDrawable drw : f) {
					if (drw instanceof DrawOperationAware)
						((DrawOperationAware) drw).setDrawOperation(f.getDrawOperation());
					drw.draw();
				}
				f.getStrategy().show();
			}
		};

		this.timer.schedule(task, 0, 10);
	}

	public Iterator<IDrawable> iterator() {
		return this.drawables.iterator();
	}

	public void addDrawable(IDrawable d) {
		this.drawables.add(d);
	}

	public final static void main(String[] args) {
		Fenetre f = new Fenetre(800, 600);
		SimpleImageLoader sld = new SimpleImageLoader();
		Image image = sld.getImage("D:/projet_java/personnel/picross/src/main/resources/paris-8.jpg");
		// Image image = sld.getImage("D:/projet_java/personnel/picross/src/main/resources/la-grand-mere-a-moustache.jpg");
		// Image image = sld.getImage("D:/projet_java/personnel/picross/src/main/resources/madonna.jpg");

		PicrossGenerator generator = new PicrossGenerator(image, 50);
		Picross picross = generator.genere();
		PicrosseDrawer drawer = new PicrosseDrawer(picross, Color.black, 800, 600, 10);
		f.addDrawable(drawer);

		f.start();
	}

}
