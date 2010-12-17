package com.factoria2.absolute.widgets.core.geom;

/**
 * Sizes are read-only
 * 
 * @author Iv�n
 */
public class Size {

	public static final Size EMPTYNESS = new Size(0, 0);

	private int width;
	private int height;

	public Size(final int width, final int height) {
		this.width = width;
		this.height = height;
	}

	public Size clone() {
		return new Size(width, height);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Size other = (Size) obj;
		if (height != other.height) {
			return false;
		}
		if (width != other.width) {
			return false;
		}
		return true;
	}

	public int getHeight() {
		return height;
	}

	public Size getOpposite() {
		return new Size(-width, -height);
	}

	public int getWidth() {
		return width;
	}

	public Size growBy(final Size s) {
		Size size = this.clone();
		size.width += s.getWidth();
		size.height += s.getHeight();
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	public Size shrinkBy(final Size s) {
		Size size = this.clone();
		size.width -= s.getWidth();
		size.height -= s.getHeight();
		return size;
	}

	@Override
	public String toString() {
		return "[" + width + "x" + height + "]";
	}

}
