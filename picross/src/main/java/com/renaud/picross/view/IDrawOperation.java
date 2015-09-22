package com.renaud.picross.view;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Image;
import java.awt.image.BufferedImage;

public interface IDrawOperation {

	public Image getImage();

	public void clean();

	public void drawImage(Image image, double x, double y, double xRotation, double yRotation, double theta, double scale, float alpha);

	public void drawImage(Image image, double x, double y, double xRotation, double yRotation, double theta, double scaleX, double scaleY, float alpha);

	public void drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2);

	public void drawRect(Color color, int x, int y, int width, int height);

	public void fillRect(Color color, double x, double y, double width, double height, float alpha);

	public void fillCircle(Color color, double x, double y, double rayon, float alpha);

	public void drawImage(Image image, double x, double y, double xRotation, double yRotation, double theta, double scale, float alpha, Composite composite);

	public void drawChar(String ch, int x, int y);
	
	public void drawLine(Color color, int x1, int y1, int x2,int y2,float alpha);
	
	public void fillPolygone(Color color, int[] x, int[] y, float alpha);
	
	public void drawPolygone(Color color, int[] x, int[] y, float alpha);
	
	public void drawPolygone(int[] x, int[] y, BufferedImage img);
}
