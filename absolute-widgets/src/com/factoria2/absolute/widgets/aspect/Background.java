package com.factoria2.absolute.widgets.aspect;

import java.util.HashMap;
import java.util.Map;

public abstract class Background {

	public abstract Map<String,String> getCssValues();
	
	protected final Map<String,String> background( String value ) {
		Map<String,String> ret = new HashMap<String, String>();
		ret.put("background", value);
		return ret;
	}
}
