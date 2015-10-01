package com.renaud.picross.view;

import java.awt.Canvas;
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

import com.renaud.picross.game.ISequence;

public class Fenetre implements Iterable<IDrawable> {

	private JFrame frame;
	private int largeur = 800;
	private int hauteur = 600;

	private CanvasHwdBuffer buffer;
	private Timer timer;
	private List<IDrawable> drawables = new ArrayList<IDrawable>();
	private ISequence sequence;

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
				if (sequence != null) {
					sequence.activate();
				}
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

	public ISequence getSequence() {
		return sequence;
	}

	public void setSequence(ISequence sequence) {
		this.sequence = sequence;
	}

	public Canvas getBuffer() {
		return this.buffer;
	}

}
