package com.factoria2.absolute.widgets.aspect;

public class Shadow {

	private int vertical;
	private int horizontal;
	private int smoothness;
	private Color color;

	public Shadow(Color color, int width) {
		this(color,width,width,2*width);
	}

	public Shadow(Color color, int vertical, int horizontal, int smoothness) {
		this.color = color;
		this.vertical = vertical;
		this.horizontal = horizontal;
		this.smoothness = smoothness;
	}

	public Color getColor() {
		return color;
	}

	public int getVertical() {
		return vertical;
	}

	public int getHorizontal() {
		return horizontal;
	}

	public int getSmoothness() {
		return smoothness;
	}
	
	public String getCssValue() {
		return color.getCssValue()+" "+horizontal+"px "+vertical+"px "+smoothness+"px";
	}
	
}
