package com.factoria2.absolute.widgets.aspect.background;

import java.util.HashMap;
import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.Color;
import com.factoria2.absolute.widgets.aspect.HAlignment;
import com.factoria2.absolute.widgets.aspect.VAlignment;

public class ImageBackground extends Background {

	public enum Adjustment {
		REPEAT_X("repeat-x"), 
		REPEAT_Y("repeat-y"), 
		NO_REPEAT("no-repeat"), 
		REPEAT("repeat"),
		FIT_HORIZONTAL("100% auto"),
		FIT_VERTICAL("auto 100%"),
		FIT_RESIZE("100% 100%"),
		FIT_STRETCH("contain"),
		FIT_EXPAND("cover");

		private String cssValue;

		private Adjustment( String cssValue ){
			this.cssValue = cssValue;
		}

		public String getCssValue() {
			return cssValue;
		}
	}
	
	private Color color;
	private String url;
	private HAlignment horizontalAlignment = HAlignment.CENTER;
	private VAlignment verticalAlignment = VAlignment.MIDDLE;
	private Adjustment adjustment = Adjustment.FIT_RESIZE;
	
	
	public ImageBackground( Color color, String url ) {
		this.color = color;
		this.url = url;
	}

	public ImageBackground( Color color, String url, Adjustment adjustment ) {
		this.color = color;
		this.url = url;
		this.adjustment = adjustment;
	}

	public ImageBackground( Color color, String url, HAlignment horizontalAlignment, VAlignment verticalAlignment, Adjustment adjustment ) {
		this.color = color;
		this.url = url;
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		this.adjustment = adjustment;
	}

	public Color getColor() {
		return color;
	}

	public String getUrl() {
		return url;
	}

	public HAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public VAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

	public Adjustment getAdjustment() {
		return adjustment;
	}

	@Override
	public Map<String,String> getCssValues() {
		Map<String,String> values = new HashMap<String, String>();
		String background = "";
		String backgroundSize = "";
		
		// Compose background
		background += " "+color.getCssValue();
		background += " url("+url+")";
		background += " "+horizontalAlignment.getCssValue();
		if( verticalAlignment==VAlignment.MIDDLE ){
			background += " center";
		} else {
			background += " "+verticalAlignment.getCssValue();
		}

		// Compose backgroundSize
		switch( adjustment ){
			case FIT_EXPAND:
			case FIT_HORIZONTAL:
			case FIT_RESIZE:
			case FIT_STRETCH:
			case FIT_VERTICAL: {
				background += " "+Adjustment.NO_REPEAT.getCssValue();
				backgroundSize += " "+adjustment.getCssValue();
				break;
			}
			
			default: {
				background += " "+adjustment.getCssValue();
				break;
			}
		}
		
		// Store values
		values.put("background", background);
		if( !backgroundSize.equals("") ){
			values.put("backgroundSize", backgroundSize);
		}

		return values;
	}

}
