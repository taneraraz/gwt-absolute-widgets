package com.factoria2.absolute.widgets.aspect;

public enum VAlignment {
	TOP("top"), 
	MIDDLE("middle"), 
	BOTTOM("bottom");
	
	private String cssValue;

	private VAlignment( String cssValue ){
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}
	
	
}
