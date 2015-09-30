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

import com.renaud.picros.finalize.Finalizer;
import com.renaud.picros.finalize.LighterFinalizer;
import com.renaud.picross.colorResolver.ColorResolver;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.colorResolver.InspectorResolver;
import com.renaud.picross.generator.PicrossGeneratorImpl;
import com.renaud.picross.model.Picross;
import com.renaud.picross.view.tools.SimpleImageLoader;

public class Fenetre implements Iterable<IDrawable> {

	private JFrame frame;
	private int largeur = 800;
	private int hauteur = 600;

	private CanvasHwdBuffer buffer;
	private Timer timer;
	private List<IDrawable> drawables = new ArrayList<IDrawable>();

	public Fenetre(int largeur, int hauteur) {
		this.frame = new JFrame("Picross");
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
		Image image = sld.getImage( System.getProperty("user.dir") + "/src/main/resources/ferrari.jpg");
		Picross picross = new Picross();
		ColorResolver resolver = new InspectorResolver(picross, new DistanceSimple(), 5, 0.01);
		Finalizer finalizer = new LighterFinalizer(0.4);
		f.addDrawable(addPicross(image, 10, 10, 50, 5, picross, resolver, finalizer));
		f.start();

		System.out.println(resolver.getNbColor());
	}

	private static PicrosseDrawer addPicross(Image image, int x, int y, int largeur, int pixelLargeur, Picross picross, ColorResolver colorResolver, Finalizer finalizer) {
		PicrossGeneratorImpl generator = new PicrossGeneratorImpl(new AWTPixelReader(image).getTable(), picross, largeur);
		generator.computeImage();
		colorResolver.resolve(picross);
		finalizer.finalize(picross);
		generator.computeNumber();
		return new PicrosseDrawer(picross, Color.black, x, y, largeur * pixelLargeur, picross.getHauteur() * pixelLargeur, pixelLargeur);
	}

}
