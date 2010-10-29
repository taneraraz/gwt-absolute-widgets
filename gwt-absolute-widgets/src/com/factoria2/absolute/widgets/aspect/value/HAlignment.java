package com.factoria2.absolute.widgets.aspect.value;


public enum HAlignment implements HasCssValue {
	LEFT("left"), CENTER("center"), RIGHT("right");

	private String cssValue;

	private HAlignment(final String cssValue) {
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}
}
