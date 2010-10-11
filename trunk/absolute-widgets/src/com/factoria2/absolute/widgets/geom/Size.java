package com.factoria2.absolute.widgets.geom;

/**
 * Sizes are read-only
 * @author Iván
 *
 */
public class Size {

	public static final Size EMPTYNESS = new Size(0,0);
	
	private int width;
	private int height;
	
	public Size(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Size clone() {
		return new Size(width,height);
	}

	@Override
	public String toString() {
		return "["+width+"x"+height+"]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
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
		Size other = (Size) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}

	public Size shrinkBy(int deltaWidth, int deltaHeight) {
		Size size = this.clone();
		size.width -= deltaWidth;
		size.height -= deltaHeight;
		return size;
	}

	public Size resizeTo(int width, int height) {
		Size size = this.clone();
		size.width = width;
		size.height = height;
		return size;
	}
	
}
