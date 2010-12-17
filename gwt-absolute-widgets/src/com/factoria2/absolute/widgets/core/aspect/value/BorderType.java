package com.factoria2.absolute.widgets.core.aspect.value;

public enum BorderType implements HasCssValue {
	DOTTED("dotted"), DASHED("dashed"), SOLID("solid"), DOUBLE("double"), GROOVE("groove"), RIDGE("ridge"), INSET("inset"), OUTSET("outset");

	private String cssValue;

	private BorderType(final String cssValue) {
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}

}