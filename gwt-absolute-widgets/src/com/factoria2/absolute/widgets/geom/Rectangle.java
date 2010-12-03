package com.factoria2.absolute.widgets.geom;

/**
 * Rectangles are read-only
 * 
 * @author Iván
 */
public class Rectangle {

	public static final Rectangle EMPTY = new Rectangle(0, 0, 0, 0);

	private Point location = Point.ORIGIN;
	private Size size = Size.EMPTYNESS;

	public Rectangle(final int x, final int y, final int width, final int height) {
		location = new Point(x, y);
		size = new Size(width, height);
	}

	public Rectangle(final Point location, final Size size) {
		this.location = location;
		this.size = size;
	}

	public Rectangle clone() {
		return new Rectangle(location, size);
	}

	public boolean contains(final Point p) {
		int x = p.getX();
		int y = p.getY();
		return x >= location.getX() && x < location.getX() + size.getWidth() && y >= location.getY() && y < location.getY() + size.getHeight();
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
		Rectangle other = (Rectangle) obj;
		if (location == null) {
			if (other.location != null) {
				return false;
			}
		} else if (!location.equals(other.location)) {
			return false;
		}
		if (size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!size.equals(other.size)) {
			return false;
		}
		return true;
	}

	public int getHeight() {
		return size.getHeight();
	}

	public Point getLocation() {
		return location;
	}

	public Size getSize() {
		return size;
	}

	public int getWidth() {
		return size.getWidth();
	}

	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}

	public Rectangle growSizeBy(final Size size) {
		Rectangle rc = this.clone();
		rc.size = rc.size.growBy(size);
		return rc;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	public Rectangle moveBy(final Size offset) {
		return new Rectangle(location.moveBy(offset), this.size);
	}

	public Rectangle moveTo(final Point location) {
		return new Rectangle(location, this.size);
	}

	public Rectangle resizeTo(final Size size) {
		return new Rectangle(this.location, size);
	}

	public Rectangle shrinkBy(final Insets insets) {
		Rectangle rc = this.clone();
		rc.location = location.moveBy(insets.getOffset());
		rc.size = size.shrinkBy(insets.getAggregatedSize());
		return rc;
	}

	public Rectangle shrinkSizeBy(final Size size) {
		Rectangle rc = this.clone();
		rc.size = rc.size.shrinkBy(size);
		return rc;
	}

	@Override
	public String toString() {
		return location.toString() + size.toString();
	}

}
