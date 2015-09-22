package com.renaud.picross.generator;

import com.renaud.picross.colorResolver.ColorResolver;

public interface PicrossGenerator {
	void computeImage();
	void computeColor();
	void setResolver(ColorResolver resolver);
}
