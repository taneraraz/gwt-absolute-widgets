package com.factoria2.absolute.widgets.aspect;

public enum HAlignment {
	LEFT("left"), 
	CENTER("center"), 
	RIGHT("right");
	
	private String cssValue;

	private HAlignment( String cssValue ){
		this.cssValue = cssValue;
	}

	public String getCssValue() {
		return cssValue;
	}
}
