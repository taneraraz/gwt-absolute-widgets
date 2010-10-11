package com.factoria2.absolute.widgets.aspect.background;

import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Color;

public class LinearGradientBackground extends GradientBackground {

	private boolean vertical;

	public LinearGradientBackground( Color from, Color to, boolean vertical ) {
		super(from, to);
		this.vertical = vertical;
	}

	public boolean isVertical() {
		return vertical;
	}

	@Override
	public Map<String,String> getCssValues() {
		if( isVertical() ) {
			return background(
				"-webkit-gradient(linear, left top, left bottom, "+
                "from("+getFrom().getCssValue()+"), to("+getTo().getCssValue()+") )"
            );
		} else {
			return background(
				"-webkit-gradient(linear, left top, right top, "+
				"from("+getFrom().getCssValue()+"), to("+getTo().getCssValue()+") )"
			);
		}
	}

}
