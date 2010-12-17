package com.factoria2.absolute.widgets.core.aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Cursor implements HasCssProperties {
	DEFAULT("default"), CROSSHAIR("crosshair"), RESIZE_E("e-resize"), HELP("help"), MOVE("move"), RESIZE_N("n-resize"), RESIZE_NE("ne-resize"), RESIZE_NW("nw-resize"), POINTER("pointer"), BUSY("wait"), RESIZE_S("s-resize"), RESIZE_SE("se-resize"), RESIZE_SW("sw-resize"), TEXT("text"), RESIZE_W("w-resize");

	private Map<String, String> cssProps = new HashMap<String, String>();

	private Cursor(final String cssValue) {
		cssProps.put("cursor", cssValue);
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

}
