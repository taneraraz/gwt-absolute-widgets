package com.factoria2.absolute.widgets.aspect.value;

public enum ReflectionPosition implements HasCssValue {
	ABOVE("above"), BELOW("below"), LEFT("left"), RIGHT("right");

	private String cssValue;

	private ReflectionPosition(final String cssValue) {
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}
}