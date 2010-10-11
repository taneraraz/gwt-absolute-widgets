package com.factoria2.absolute.widgets.aspect.background;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.Color;

//TODO: support radial gradients
public abstract class GradientBackground extends Background {

	private Color from;
	private Color to;
	
	protected GradientBackground(Color from, Color to) {
		this.from = from;
		this.to = to;
	}

	public Color getFrom() {
		return from;
	}

	public Color getTo() {
		return to;
	}
	
}
