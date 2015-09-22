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

import com.renaud.picross.colorResolver.ColorResolver;
import com.renaud.picross.colorResolver.DistanceMixte;
import com.renaud.picross.colorResolver.DistancePonderee;
import com.renaud.picross.colorResolver.DistanceSimple;
import com.renaud.picross.colorResolver.InspectorResolver;
import com.renaud.picross.colorResolver.NullResolver;
import com.renaud.picross.colorResolver.ProxyResolver;
import com.renaud.picross.generator.PicrossGeneratorImpl;
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
		Fenetre f = new Fenetre(600, 800);
		SimpleImageLoader sld = new SimpleImageLoader();
//		Image image = sld.getImage("E:/images/Photos/2015/2015-09/DSC_0602.JPG");
		Image image = sld.getImage("C:/Users/Renaud/git/renaud-picross/picross/src/main/resources/QUIZ_Les-personnages-de-Tintin_5472.jpeg");
		Picross picross = new Picross();
		f.addDrawable(addPicross(image, 0, 0, 30, 5,picross, new NullResolver()));
		picross = new Picross();
		f.addDrawable(addPicross(image, 150, 0, 30, 5,picross, new InspectorResolver(picross, new DistanceSimple(), 5)));

		f.start();
	}
	
	
	private static PicrosseDrawer addPicross(Image image,int x, int y, int largeur, int pixelLargeur, Picross picross, ColorResolver resolver){
		PicrossGeneratorImpl generator = new PicrossGeneratorImpl(image, picross, largeur);
		generator.setResolver(resolver);
		generator.computeImage();
		generator.computeColor();
		return new PicrosseDrawer(picross, Color.black, x, y, largeur * pixelLargeur, picross.getHauteur() * pixelLargeur, pixelLargeur);
	}

}
