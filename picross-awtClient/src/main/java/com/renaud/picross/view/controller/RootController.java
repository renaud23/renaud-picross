package com.renaud.picross.view.controller;

public class RootController extends AbstractController {

	public RootController(int largeur, int hauteur) {
		super(new Surface(0, 0, largeur, hauteur));
	}

}
