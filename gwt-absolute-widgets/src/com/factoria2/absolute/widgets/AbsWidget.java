package com.factoria2.absolute.widgets;

import java.util.Map;
import java.util.Map.Entry;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.Border;
import com.factoria2.absolute.widgets.aspect.Color;
import com.factoria2.absolute.widgets.aspect.Font;
import com.factoria2.absolute.widgets.aspect.Reflection;
import com.factoria2.absolute.widgets.aspect.Shadow;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Point;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

// TODO: support WebFonts with some automation???
// TODO: support text wrapping elipsis in labels
// TODO: support transitions and transforms
// TODO: support animations
public class AbsWidget extends Composite {

	private AbsWidget parent;
	private AbsolutePanel panel = new AbsolutePanel();
	private Element element = panel.getElement();
	private Rectangle bounds;
	private Rectangle cachedClientBounds;
	private double opacity;
	private Font font;
	private Color color;
	private Background background;
	private Border border;
	private Shadow shadow;
	private Reflection reflection;

	public AbsWidget() {
		initWidget(panel);

		bounds = Rectangle.EMPTY;
		setCss("position", "absolute");
		setCss("left", "0px");
		setCss("top", "0px");
		setCss("width", "0px");
		setCss("height", "0px");
		setCss("overflow", "");

		opacity = 1;
		setCss("opacity", "");

		font = Font.getDefault();
		for (Map.Entry<String, String> entry : font.getCssValues().entrySet()) {
			setCss(entry.getKey(), entry.getValue());
		}

		color = Color.BLACK;
		setCss("color", color.getCssValue());

		background = null;
		setCss("background", "");

		border = null;
		setCss("border", "0");

		shadow = null;
		setCss("webkitBoxShadow", "");

		reflection = null;
		setCss("webkitBoxReflect", "");
	}

	// VISUAL ASPECT METHODS //////////////////////////////////////////////////

	public double getOpacity() {
		return opacity;
	}

	public void setOpacity(double opacity) {
		this.opacity = opacity;
		if (opacity >= 0 && opacity < 1) {
			setCss("opacity", Double.toString(opacity));
		} else {
			setCss("opacity", "");
		}
	}

	public Border getBorder() {
		return border;
	}

	public void setBorder(Border border) {
		this.border = border;

		boolean renderBorder = true;

		if (border == null) {
			renderBorder = false;
		} else if (border.getWidth().equals(Insets.NONE)) {
			renderBorder = false;
		}

		// TODO: llevarse esta logica al border de forma similar a como se hace con los backgrounds
		if (renderBorder) {
			setCss("borderStyle", border.getType().getCssValue());
			setCss("borderColor", border.getColor().getCssValue());

			Insets w = border.getWidth();
			setCss("borderLeftWidth", w.getLeft() + "px");
			setCss("borderTopWidth", w.getTop() + "px");
			setCss("borderRightWidth", w.getRight() + "px");
			setCss("borderBottomWidth", w.getBottom() + "px");

			Insets r = border.getRadius();
			if (!r.equals(Insets.NONE)) {
				setCss("borderTopLeftRadius", r.getTop() + "px " + r.getLeft() + "px");
				setCss("borderTopRightRadius", r.getTop() + "px " + r.getRight() + "px");
				setCss("borderBottomLeftRadius", r.getBottom() + "px " + r.getLeft() + "px");
				setCss("borderBottomRightRadius", r.getBottom() + "px " + r.getRight() + "px");
			}

		} else {
			setCss("borderStyle", "");
			setCss("borderColor", "");
			setCss("borderTopLeftRadius", "");
			setCss("borderTopRightRadius", "");
			setCss("borderBottomLeftRadius", "");
			setCss("borderBottomRightRadius", "");
			setCss("borderWidth", "");
		}

		setBounds(bounds);
	}

	public void setBackground(Background background) {
		if (this.background != null) {
			for (Entry<String, String> e : background.getCssValues().entrySet()) {
				setCss(e.getKey(), "");
			}
		}
		this.background = background;
		if (this.background != null) {
			for (Entry<String, String> e : background.getCssValues().entrySet()) {
				setCss(e.getKey(), e.getValue());
			}
		}
	}

	public Background getBackground() {
		return background;
	}

	public void setShadow(Shadow shadow) {
		this.shadow = shadow;
		if (shadow == null) {
			setCss("webkitBoxShadow", "");
		} else {
			setCss("webkitBoxShadow", shadow.getCssValue());
		}
	}

	public Shadow getShadow() {
		return shadow;
	}

	public void setReflection(Reflection reflection) {
		this.reflection = reflection;
		if (reflection == null) {
			setCss("webkitBoxReflect", "");
		} else {
			setCss("webkitBoxReflect", reflection.getCssValue());
		}
	}

	public Reflection getReflection() {
		return reflection;
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		if (font == null) {
			if (this.font != null) {
				for (String prop : this.font.getCssValues().keySet()) {
					setCss(prop, "");
				}
			}
		} else {
			for (Map.Entry<String, String> entry : font.getCssValues().entrySet()) {
				setCss(entry.getKey(), entry.getValue());
			}
		}
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		setCss("color", color.getCssValue());
	}

	// POSITION AND SIZE METHODS //////////////////////////////////////////////

	/**
	 * Returns a copy of the bounds
	 * 
	 * @return a copy of the bounds
	 */
	public final Rectangle getBounds() {
		return bounds;
	}

	public final Rectangle getClientBounds() {
		if (cachedClientBounds == null) {
			cachedClientBounds = bounds;
			if (border != null) {
				cachedClientBounds = cachedClientBounds.shrinkBy(border.getWidth());
			}
			cachedClientBounds = cachedClientBounds.moveTo(Point.ORIGIN);
		}
		return cachedClientBounds;
	}

	public final void setBounds(int x, int y, Size size) {
		setBounds(new Rectangle(x, y, size.getWidth(), size.getHeight()));
	}

	public final void setBounds(int x, int y, int width, int height) {
		setBounds(new Rectangle(x, y, width, height));
	}

	public final void setLocation(int x, int y) {
		setBounds(bounds.moveTo(new Point(x, y)));
	}

	public final void setLocation(Point location) {
		setBounds(bounds.moveTo(location));
	}

	public final void setSize(int width, int height) {
		setBounds(bounds.resizeTo(new Size(width, height)));
	}

	public final void setSize(Size size) {
		setBounds(bounds.resizeTo(size));
	}

	public final void setBounds(Rectangle bounds) {
		this.bounds = bounds;
		this.cachedClientBounds = null;

		Rectangle modBounds = bounds;
		if (border != null) {
			Insets ins = border.getWidth();
			modBounds = modBounds.shrinkSizeBy(ins.getAggregatedSize());
		}

		setCss("position", "absolute");
		setCss("left", modBounds.getX() + "px");
		setCss("top", modBounds.getY() + "px");
		setCss("width", modBounds.getWidth() + "px");
		setCss("height", modBounds.getHeight() + "px");

		relayout();
	}

	public final void moveBy(Point offset) {
		setLocation(getBounds().getLocation().moveBy(offset));
	}

	public final void growBy(Size size) {
		setSize(getBounds().getSize().growBy(size));
	}

	public Size getPreferredSize() {
		return Size.EMPTYNESS;
	}

	public final void relayout() {
		layoutChildren(getClientBounds());
	}

	// HIERARCHY METHODS //////////////////////////////////////////////////////

	public final AbsWidget getAbsParent() {
		return parent;
	}

	// HELPER METHODS TO BE OVERRIDEN OR CALLED FROM SUBCLASSES ///////////////

	protected void addChild(Widget widget) {
		panel.add(widget);
		if (widget instanceof AbsWidget) {
			((AbsWidget) widget).parent = this;
		}
	}

	/**
	 * This method is a helper to be used with non {@link AbsWidget}s
	 * 
	 * @param widget
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	protected final void setChildBounds(Widget widget, int x, int y, int width, int height) {
		checkWidgetParent(widget);
		Element elem = widget.getElement();
		DOM.setStyleAttribute(elem, "position", "absolute");
		DOM.setStyleAttribute(elem, "left", x + "px");
		DOM.setStyleAttribute(elem, "top", y + "px");
		DOM.setStyleAttribute(elem, "width", width + "px");
		DOM.setStyleAttribute(elem, "height", height + "px");
	}

	/**
	 * This method is to be used with non {@link AbsWidget}s
	 * 
	 * @param widget
	 * @param bounds
	 */
	protected final void setChildBounds(Widget widget, Rectangle bounds) {
		setChildBounds(widget, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
	}

	protected final void setChildCss(Widget child, String property, String value) {
		DOM.setStyleAttribute(child.getElement(), property, value);
	}

	protected final void setCss(String property, String value) {
		DOM.setStyleAttribute(element, property, value);
	}

	protected void layoutChildren(Rectangle clientBounds) {
	}

	// PRIVATE HELPER METHODS /////////////////////////////////////////////////

	private void checkWidgetParent(Widget widget) {
		if (widget.getParent() != panel) {
			throw new IllegalArgumentException("Widget " + widget + " is not a child of this");
		}
	}

	// METHODS FROM GWT THAT SHOULDN'T BE USED ////////////////////////////////

	@Override
	@Deprecated
	public final void setHeight(String height) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	@Override
	@Deprecated
	public final void setPixelSize(int width, int height) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	@Override
	@Deprecated
	public final void setSize(String width, String height) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	@Override
	@Deprecated
	public final void setWidth(String width) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

}
