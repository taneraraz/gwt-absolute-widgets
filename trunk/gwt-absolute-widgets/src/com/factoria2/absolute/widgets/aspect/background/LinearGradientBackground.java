package com.factoria2.absolute.widgets.aspect.background;

import java.util.Collections;
import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Color;
import com.google.gwt.core.client.GWT;

public class LinearGradientBackground extends GradientBackground {

	private boolean vertical;
	private Implementation impl = GWT.create(Implementation.class);
	{
		impl.owner = this;
	}

	public LinearGradientBackground(Color from, Color to, boolean vertical) {
		super(from, to);
		this.vertical = vertical;
	}

	public boolean isVertical() {
		return vertical;
	}

	@Override
	public Map<String, String> getCssValues() {
		return impl.getCssValues();
	}

	static class Implementation {
		protected LinearGradientBackground owner;

		public Map<String, String> getCssValues() {
			return Collections.emptyMap();
		}
	}

	static class ImplementationFirefox extends Implementation {
		@Override
		public Map<String, String> getCssValues() {
			if (owner.isVertical()) {
				return owner.background("-moz-linear-gradient(270deg, " + owner.getFrom().getCssValue() + ", " + owner.getTo().getCssValue() + ")");
			} else {
				return owner.background("-moz-linear-gradient(0deg, " + owner.getFrom().getCssValue() + ", " + owner.getTo().getCssValue() + ")");
			}
		}
	}

	static class ImplementationWebkit extends Implementation {
		@Override
		public Map<String, String> getCssValues() {
			if (owner.isVertical()) {
				return owner.background("-webkit-gradient(linear, left top, left bottom, " + "from(" + owner.getFrom().getCssValue() + "), to(" + owner.getTo().getCssValue() + ") )");
			} else {
				return owner.background("-webkit-gradient(linear, left top, right top, " + "from(" + owner.getFrom().getCssValue() + "), to(" + owner.getTo().getCssValue() + ") )");
			}
		}
	}
}
