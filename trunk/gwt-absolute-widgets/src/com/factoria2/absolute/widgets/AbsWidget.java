package com.factoria2.absolute.widgets;

import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.Border;
import com.factoria2.absolute.widgets.aspect.Font;
import com.factoria2.absolute.widgets.aspect.HasCssProperties;
import com.factoria2.absolute.widgets.aspect.Reflection;
import com.factoria2.absolute.widgets.aspect.Shadow;
import com.factoria2.absolute.widgets.aspect.value.Color;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Point;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

// TODO: support WebFonts with some automation???
// TODO: support text wrapping elipsis in labels
// TODO: support transitions and transforms
// TODO: support animations
// TODO: reset all inherited CSS attributes when constructing
public class AbsWidget extends Composite implements HasClickHandlers {

	private AbsWidget parent;
	private AbsWidgetPanel panel = new AbsWidgetPanel();
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
	private int newChildZIndex = 1;

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
		applyCssProperties(font);

		color = Color.BLACK;
		setCss("color", color.getCssValue());

		background = null;
		setCss("background", "");

		border = null;
		setCss("border", "0");
	}

	@Override
	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return panel.addClickHandler(handler);
	}

	public final AbsWidget getAbsParent() {
		return parent;
	}

	public Background getBackground() {
		return background;
	}

	public Border getBorder() {
		return border;
	}

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

	public Color getColor() {
		return color;
	}

	public Font getFont() {
		return font;
	}

	public double getOpacity() {
		return opacity;
	}

	public Size getPreferredSize() {
		return Size.EMPTYNESS;
	}

	public Reflection getReflection() {
		return reflection;
	}

	public Shadow getShadow() {
		return shadow;
	}

	public final int getZIndex() {
		return Integer.parseInt(getCss("zIndex"));
	}

	public final void growBy(final Size size) {
		setSize(getBounds().getSize().growBy(size));
	}

	// POSITION AND SIZE METHODS //////////////////////////////////////////////

	public final void moveBy(final Point offset) {
		setLocation(getBounds().getLocation().moveBy(offset));
	}

	public final void relayout() {
		layoutChildren(getClientBounds());
	}

	public final void setBackground(final Background background) {
		unapplyCssProperties(this.background);
		this.background = background;
		applyCssProperties(this.background);
	}

	public final void setBorder(final Border border) {
		unapplyCssProperties(this.border);
		this.border = border;
		applyCssProperties(this.border);
		setBounds(bounds);
	}

	public final void setBounds(final int x, final int y, final int width, final int height) {
		setBounds(new Rectangle(x, y, width, height));
	}

	public final void setBounds(final int x, final int y, final Size size) {
		setBounds(new Rectangle(x, y, size.getWidth(), size.getHeight()));
	}

	public final void setBounds(final Rectangle bounds) {
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

	public void setColor(final Color color) {
		this.color = color;
		setCss("color", color.getCssValue());
	}

	public void setFont(final Font font) {
		unapplyCssProperties(this.font);
		this.font = font;
		applyCssProperties(this.font);
	}

	@Override
	@Deprecated
	public final void setHeight(final String height) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	public final void setLocation(final int x, final int y) {
		setBounds(bounds.moveTo(new Point(x, y)));
	}

	public final void setLocation(final Point location) {
		setBounds(bounds.moveTo(location));
	}

	public void setOpacity(final double opacity) {
		this.opacity = opacity;
		if (opacity >= 0 && opacity < 1) {
			setCss("opacity", Double.toString(opacity));
		} else {
			setCss("opacity", "");
		}
	}

	@Override
	@Deprecated
	public final void setPixelSize(final int width, final int height) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	public void setReflection(final Reflection reflection) {
		unapplyCssProperties(this.reflection);
		this.reflection = reflection;
		applyCssProperties(this.reflection);
	}

	public void setShadow(final Shadow shadow) {
		unapplyCssProperties(this.shadow);
		this.shadow = shadow;
		applyCssProperties(this.shadow);
	}

	public final void setSize(final int width, final int height) {
		setBounds(bounds.resizeTo(new Size(width, height)));
	}

	public final void setSize(final Size size) {
		setBounds(bounds.resizeTo(size));
	}

	@Override
	@Deprecated
	public final void setSize(final String width, final String height) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	@Override
	@Deprecated
	public final void setWidth(final String width) {
		throw new UnsupportedOperationException("VioletWidgets must always be positioned absolute and measured in pixels");
	}

	public final void setZIndex(final int zIndex) {
		setCss("zIndex", Integer.toString(zIndex));
	}

	protected void addChild(final Widget widget) {
		panel.add(widget);
		if (widget instanceof AbsWidget) {
			AbsWidget absWidget = (AbsWidget) widget;
			absWidget.setZIndex(newChildZIndex++);
			absWidget.parent = this;
		}
	}

	protected final String getCss(final String property) {
		return DOM.getStyleAttribute(element, property);
	}

	protected final String getJsProperty(final String property) {
		return DOM.getElementProperty(element, property);
	}

	protected void layoutChildren(final Rectangle clientBounds) {
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
	protected final void setChildBounds(final Widget widget, final int x, final int y, final int width, final int height) {
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
	protected final void setChildBounds(final Widget widget, final Rectangle bounds) {
		setChildBounds(widget, bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
	}

	protected final void setChildCss(final Widget child, final String property, final String value) {
		DOM.setStyleAttribute(child.getElement(), property, value);
	}

	protected final void setCss(final String property, final String value) {
		DOM.setStyleAttribute(element, property, value);
	}

	protected final void setJsProperty(final String property, final String value) {
		DOM.setElementProperty(element, property, value);
	}

	private void applyCssProperties(final HasCssProperties cssProps) {
		if (cssProps != null) {
			for (Map.Entry<String, String> prop : cssProps.getCssProperties().entrySet()) {
				setCss(prop.getKey(), prop.getValue());
			}
		}
	}

	private void checkWidgetParent(final Widget widget) {
		if (widget.getParent() != panel) {
			throw new IllegalArgumentException("Widget " + widget + " is not a child of this");
		}
	}

	private void unapplyCssProperties(final HasCssProperties cssProps) {
		if (cssProps != null) {
			for (Map.Entry<String, String> prop : cssProps.getCssProperties().entrySet()) {
				setCss(prop.getKey(), "");
			}
		}
	}

}
