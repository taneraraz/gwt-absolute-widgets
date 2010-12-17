package com.factoria2.absolute.widgets.core.aspect.value;

public enum VAlignment implements HasCssValue {
	TOP("top"), MIDDLE("middle"), BOTTOM("bottom");

	private String cssValue;

	private VAlignment(final String cssValue) {
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}

}
