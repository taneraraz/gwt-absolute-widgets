package com.factoria2.absolute.widgets.geom;

/**
 * Rectangles are read-only
 * 
 * @author Iván
 * 
 */
public class Rectangle {

	public static final Rectangle EMPTY = new Rectangle(0, 0, 0, 0);

	private Point location = Point.ORIGIN;
	private Size size = Size.EMPTYNESS;

	public Rectangle(int x, int y, int width, int height) {
		location = new Point(x, y);
		size = new Size(width, height);
	}

	public Rectangle(Point location, Size size) {
		this.location = location;
		this.size = size;
	}

	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}

	public int getWidth() {
		return size.getWidth();
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

	public Rectangle clone() {
		return new Rectangle(location, size);
	}

	@Override
	public String toString() {
		return location.toString() + size.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		Rectangle other = (Rectangle) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

	public Rectangle shrinkBy(Insets insets) {
		Rectangle rc = this.clone();
		rc.location = location.moveBy(insets.getOffset());
		rc.size = size.shrinkBy(insets.getAggregatedSize());
		return rc;
	}

	public Rectangle growSizeBy(Size size) {
		Rectangle rc = this.clone();
		rc.size = size.growBy(size);
		return rc;
	}

	public Rectangle shrinkSizeBy(Size size) {
		Rectangle rc = this.clone();
		rc.size = rc.size.shrinkBy(size);
		return rc;
	}

	public Rectangle moveTo(Point location) {
		return new Rectangle(location, this.size);
	}

	public Rectangle resizeTo(Size size) {
		return new Rectangle(this.location, size);
	}

	public boolean contains(Point p) {
		int x = p.getX();
		int y = p.getY();
		return x >= location.getX() && x < location.getX() + size.getWidth() && y >= location.getY() && y < location.getY() + size.getHeight();
	}

}
