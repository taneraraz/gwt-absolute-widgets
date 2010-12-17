package com.factoria2.absolute.widgets.core.aspect.background;

import com.factoria2.absolute.widgets.core.aspect.Background;
import com.factoria2.absolute.widgets.core.aspect.value.Color;

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
