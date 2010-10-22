package com.factoria2.absolute.widgets.aspect;



/**
 * Colors are read-only
 * @author Iván
 *
 */
// TODO: support HSL color model
public class Color {
	
	public static final Color WHITE = new Color(255,255,255);
	public static final Color BLACK = new Color(0,0,0);

	public static final Color GRAY_100 = WHITE;
	public static final Color GRAY_87 = new Color(224,224,224);
	public static final Color GRAY_75 = new Color(192,192,192);
	public static final Color GRAY_62 = new Color(160,160,160);
	public static final Color GRAY_50 = new Color(128,128,128);
	public static final Color GRAY_37 = new Color(96,96,96);
	public static final Color GRAY_25 = new Color(64,64,64);
	public static final Color GRAY_12 = new Color(32,32,32);
	public static final Color GRAY_0 = BLACK;

	public static final Color RED = new Color(255,0,0);
	public static final Color GREEN = new Color(0,255,0);
	public static final Color BLUE = new Color(0,0,255);
	public static final Color CYAN = new Color(0,255,255);
	public static final Color MAGENTA = new Color(255,0,255);
	public static final Color YELLOW = new Color(255,255,0);
	
	public static final Color RED_75 = new Color(192,0,0);
	public static final Color GREEN_75 = new Color(0,192,0);
	public static final Color BLUE_75 = new Color(0,0,192);
	public static final Color CYAN_75 = new Color(0,192,192);
	public static final Color MAGENTA_75 = new Color(192,0,192);
	public static final Color YELLOW_75 = new Color(192,192,0);

	public static final Color RED_50 = new Color(128,0,0);
	public static final Color GREEN_50 = new Color(0,128,0);
	public static final Color BLUE_50 = new Color(0,0,128);
	public static final Color CYAN_50 = new Color(0,128,128);
	public static final Color MAGENTA_50 = new Color(128,0,128);
	public static final Color YELLOW_50 = new Color(128,128,0);

	public static final Color RED_25 = new Color(64,0,0);
	public static final Color GREEN_25 = new Color(0,64,0);
	public static final Color BLUE_25 = new Color(0,0,64);
	public static final Color CYAN_25 = new Color(0,64,64);
	public static final Color MAGENTA_25 = new Color(64,0,64);
	public static final Color YELLOW_25 = new Color(64,64,0);
	
	private int red;
	private int green;
	private int blue;
	private Double opacity;
	
	public Color(int red, int green, int blue, double opacity) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.opacity = opacity;
	}

	public Color(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public double getOpacity() {
		return opacity;
	}
	
	public Color clone() {
		return new Color(red,green,blue,opacity);
	}

	public String getCssValue() {
		if( opacity!=null ) {
			return "rgba("+red+","+green+","+blue+","+opacity+")";
		} else {
			return "rgb("+red+","+green+","+blue+")";
		}
	}
	
	@Override
	public String toString() {
		return getCssValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blue;
		result = prime * result + green;
		result = prime * result + ((opacity == null) ? 0 : opacity.hashCode());
		result = prime * result + red;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Color other = (Color) obj;
		if (blue != other.blue)
			return false;
		if (green != other.green)
			return false;
		if (opacity == null) {
			if (other.opacity != null)
				return false;
		} else if (!opacity.equals(other.opacity))
			return false;
		if (red != other.red)
			return false;
		return true;
	}

}
