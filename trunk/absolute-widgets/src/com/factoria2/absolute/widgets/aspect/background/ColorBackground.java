package com.factoria2.absolute.widgets.aspect.background;

import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.Color;

public class ColorBackground extends Background {

	private Color color;

	public ColorBackground( Color color ) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}

	@Override
	public Map<String,String> getCssValues() {
		return background(color.getCssValue());
	}

}
