package com.factoria2.absolute.widgets.core.aspect.value;


public enum ImageAdjustment implements HasCssValue {
	REPEAT_X("repeat-x"), REPEAT_Y("repeat-y"), NO_REPEAT("no-repeat"), REPEAT("repeat"), FIT_HORIZONTAL("100% auto"), FIT_VERTICAL("auto 100%"), FIT_RESIZE("100% 100%"), FIT_STRETCH("contain"), FIT_EXPAND("cover");

	private String cssValue;

	private ImageAdjustment(final String cssValue) {
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}
}