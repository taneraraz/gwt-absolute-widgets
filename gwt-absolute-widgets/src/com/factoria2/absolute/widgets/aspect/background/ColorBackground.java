package com.factoria2.absolute.widgets.aspect.background;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.value.Color;

public class ColorBackground extends Background {

	public static final ColorBackground WHITE = new ColorBackground(Color.WHITE);
	public static final ColorBackground BLACK = new ColorBackground(Color.BLACK);

	public static final ColorBackground GRAY_100 = WHITE;
	public static final ColorBackground GRAY_87 = new ColorBackground(Color.GRAY_87);
	public static final ColorBackground GRAY_75 = new ColorBackground(Color.GRAY_75);
	public static final ColorBackground GRAY_62 = new ColorBackground(Color.GRAY_62);
	public static final ColorBackground GRAY_50 = new ColorBackground(Color.GRAY_50);
	public static final ColorBackground GRAY_37 = new ColorBackground(Color.GRAY_37);
	public static final ColorBackground GRAY_25 = new ColorBackground(Color.GRAY_25);
	public static final ColorBackground GRAY_12 = new ColorBackground(Color.GRAY_12);
	public static final ColorBackground GRAY_0 = BLACK;

	public static final ColorBackground RED = new ColorBackground(Color.RED);
	public static final ColorBackground GREEN = new ColorBackground(Color.GREEN);
	public static final ColorBackground BLUE = new ColorBackground(Color.BLUE);
	public static final ColorBackground CYAN = new ColorBackground(Color.CYAN);
	public static final ColorBackground MAGENTA = new ColorBackground(Color.MAGENTA);
	public static final ColorBackground YELLOW = new ColorBackground(Color.YELLOW);

	public static final ColorBackground RED_75 = new ColorBackground(Color.RED_75);
	public static final ColorBackground GREEN_75 = new ColorBackground(Color.GREEN_75);
	public static final ColorBackground BLUE_75 = new ColorBackground(Color.BLUE_75);
	public static final ColorBackground CYAN_75 = new ColorBackground(Color.CYAN_75);
	public static final ColorBackground MAGENTA_75 = new ColorBackground(Color.MAGENTA_75);
	public static final ColorBackground YELLOW_75 = new ColorBackground(Color.YELLOW_75);

	public static final ColorBackground RED_50 = new ColorBackground(Color.RED_50);
	public static final ColorBackground GREEN_50 = new ColorBackground(Color.GREEN_50);
	public static final ColorBackground BLUE_50 = new ColorBackground(Color.BLUE_50);
	public static final ColorBackground CYAN_50 = new ColorBackground(Color.CYAN_50);
	public static final ColorBackground MAGENTA_50 = new ColorBackground(Color.MAGENTA_50);
	public static final ColorBackground YELLOW_50 = new ColorBackground(Color.YELLOW_50);

	public static final ColorBackground RED_25 = new ColorBackground(Color.RED_25);
	public static final ColorBackground GREEN_25 = new ColorBackground(Color.GREEN_25);
	public static final ColorBackground BLUE_25 = new ColorBackground(Color.BLUE_25);
	public static final ColorBackground CYAN_25 = new ColorBackground(Color.CYAN_25);
	public static final ColorBackground MAGENTA_25 = new ColorBackground(Color.MAGENTA_25);
	public static final ColorBackground YELLOW_25 = new ColorBackground(Color.YELLOW_25);

	private Color color;
	private Map<String, String> cssProps = new HashMap<String, String>();

	public ColorBackground(final Color color) {
		this.color = color;
		cssProps.put("background", color.getCssValue());
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

}
