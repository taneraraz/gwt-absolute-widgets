package com.factoria2.absolute.widgets.aspect.background;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.factoria2.absolute.widgets.aspect.value.Color;
import com.google.gwt.core.client.GWT;

public class LinearGradientBackground extends GradientBackground {

	static class Implementation {
		protected LinearGradientBackground owner;

		public Map<String, String> createCssProperties() {
			return Collections.emptyMap();
		}
	}

	static class ImplementationFirefox extends Implementation {
		@Override
		public Map<String, String> createCssProperties() {
			Map<String, String> ret = new HashMap<String, String>();
			if (owner.isVertical()) {
				ret.put("background", "-moz-linear-gradient(270deg, " + owner.getFrom().getCssValue() + ", " + owner.getTo().getCssValue() + ")");
			} else {
				ret.put("background", "-moz-linear-gradient(0deg, " + owner.getFrom().getCssValue() + ", " + owner.getTo().getCssValue() + ")");
			}
			return ret;
		}
	}

	static class ImplementationWebkit extends Implementation {
		@Override
		public Map<String, String> createCssProperties() {
			Map<String, String> ret = new HashMap<String, String>();
			if (owner.isVertical()) {
				ret.put("background", "-webkit-gradient(linear, left top, left bottom, " + "from(" + owner.getFrom().getCssValue() + "), to(" + owner.getTo().getCssValue() + ") )");
			} else {
				ret.put("background", "-webkit-gradient(linear, left top, right top, " + "from(" + owner.getFrom().getCssValue() + "), to(" + owner.getTo().getCssValue() + ") )");
			}
			return ret;
		}
	}

	private Implementation impl = GWT.create(Implementation.class);
	private boolean vertical;
	private Map<String, String> cssProps;

	public LinearGradientBackground(final Color from, final Color to, final boolean vertical) {
		super(from, to);
		this.impl.owner = this;
		this.vertical = vertical;

		cssProps = impl.createCssProperties();
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

	public boolean isVertical() {
		return vertical;
	}
}
