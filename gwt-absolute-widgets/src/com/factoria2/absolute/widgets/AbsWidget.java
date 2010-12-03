package com.factoria2.absolute.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Background;
import com.factoria2.absolute.widgets.aspect.Border;
import com.factoria2.absolute.widgets.aspect.Cursor;
import com.factoria2.absolute.widgets.aspect.Font;
import com.factoria2.absolute.widgets.aspect.HasCssProperties;
import com.factoria2.absolute.widgets.aspect.Reflection;
import com.factoria2.absolute.widgets.aspect.Shadow;
import com.factoria2.absolute.widgets.aspect.value.Color;
import com.factoria2.absolute.widgets.event.DragEvent;
import com.factoria2.absolute.widgets.event.DragHandler;
import com.factoria2.absolute.widgets.event.HasDragHandlers;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Point;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.factoria2.absolute.widgets.tools.Logger;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasLoseCaptureHandlers;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasMouseMoveHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.HasMouseUpHandlers;
import com.google.gwt.event.dom.client.LoseCaptureEvent;
import com.google.gwt.event.dom.client.LoseCaptureHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

// TODO: support WebFonts with some automation???
// TODO: support text wrapping elipsis in labels
// TODO: support transitions and transforms
// TODO: support animations
// TODO: reset all inherited CSS attributes when constructing
public class AbsWidget extends Composite implements HasClickHandlers, HasMouseDownHandlers, HasMouseMoveHandlers, HasMouseOutHandlers, HasMouseOverHandlers, HasMouseUpHandlers, HasLoseCaptureHandlers, HasDragHandlers {

	private class DragHandlers implements MouseDownHandler, MouseMoveHandler, MouseUpHandler, LoseCaptureHandler {

		private Point dragStart;
		private Point dragPrevious;

		@Override
		public void onLoseCapture(final LoseCaptureEvent event) {
			dragStart = null;

			DragEvent.fireDragFinished(AbsWidget.this, AbsWidget.this, Size.EMPTYNESS);
		}

		@Override
		public void onMouseDown(final MouseDownEvent event) {
			setCapture();
			dragStart = new Point(event.getClientX(), event.getClientY());
			dragPrevious = dragStart;
			DragEvent.fireDragStarted(AbsWidget.this, AbsWidget.this);
		}

		@Override
		public void onMouseMove(final MouseMoveEvent event) {
			if (dragStart != null) {
				Logger.log("dragging " + AbsWidget.this.getClass().getName());
				int adw = event.getClientX() - dragStart.getX();
				int adh = event.getClientY() - dragStart.getY();
				int rdw = event.getClientX() - dragPrevious.getX();
				int rdh = event.getClientY() - dragPrevious.getY();

				dragPrevious = new Point(event.getClientX(), event.getClientY());

				DragEvent.fireDragProgressed(AbsWidget.this, AbsWidget.this, new Size(adw, adh), new Size(rdw, rdh));
			}
		}

		@Override
		public void onMouseUp(final MouseUpEvent event) {
			int adw = event.getClientX() - dragStart.getX();
			int adh = event.getClientY() - dragStart.getY();

			dragStart = null;
			releaseCapture();

			DragEvent.fireDragFinished(AbsWidget.this, AbsWidget.this, new Size(adw, adh));
		}

	}

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
	private Cursor cursor = Cursor.DEFAULT;
	private int newChildZIndex = 1;

	private List<HandlerRegistration> dragRegistrations;

	public AbsWidget() {
		initWidget(panel);

		panel.setStyleName(getClass().getName());

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

	@Override
	public HandlerRegistration addDragHandler(final DragHandler handler) {
		final HandlerRegistration reg = addHandler(handler, DragEvent.TYPE);
		if (getHandlerCount(DragEvent.TYPE) == 1) {
			installDragHandlers();
		}

		return new HandlerRegistration() {
			@Override
			public void removeHandler() {
				reg.removeHandler();
				if (getHandlerCount(DragEvent.TYPE) == 0) {
					uninstallDragHandlers();
				}
			}
		};
	}

	@Override
	public HandlerRegistration addLoseCaptureHandler(final LoseCaptureHandler handler) {
		return panel.addLoseCaptureHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(final MouseDownHandler handler) {
		return panel.addMouseDownHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(final MouseMoveHandler handler) {
		return panel.addMouseMoveHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOutHandler(final MouseOutHandler handler) {
		return panel.addMouseOutHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseOverHandler(final MouseOverHandler handler) {
		return panel.addMouseOverHandler(handler);
	}

	@Override
	public HandlerRegistration addMouseUpHandler(final MouseUpHandler handler) {
		return panel.addMouseUpHandler(handler);
	}

	@Deprecated
	@Override
	public final void addStyleDependentName(final String styleSuffix) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Deprecated
	@Override
	public final void addStyleName(final String style) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Override
	public void fireEvent(final GwtEvent<?> event) {
		if (event instanceof DragEvent) {
			Logger.log("fireEvent " + event);
		}
		super.fireEvent(event);
	}

	public AbsWidget getAbsParent() {
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
	public Rectangle getBounds() {
		return bounds;
	}

	public Rectangle getClientBounds() {
		if (cachedClientBounds == null) {
			cachedClientBounds = bounds;
			if (border != null) {
				cachedClientBounds = cachedClientBounds.shrinkBy(border.getWidth());
			}
			cachedClientBounds = cachedClientBounds.moveTo(Point.ORIGIN);

			boolean resize = false;
			int w = cachedClientBounds.getWidth();
			int h = cachedClientBounds.getHeight();
			if (w < 0) {
				resize = true;
				w = 0;
			}
			if (h < 0) {
				resize = true;
				h = 0;
			}
			if (resize) {
				cachedClientBounds = cachedClientBounds.resizeTo(new Size(w, h));
			}
		}
		return cachedClientBounds;
	}

	public Color getColor() {
		return color;
	}

	public Cursor getCursor() {
		return cursor;
	}

	public Font getFont() {
		return font;
	}

	@Deprecated
	@Override
	public final Object getLayoutData() {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
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

	@Deprecated
	@Override
	public final String getStyleName() {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Deprecated
	@Override
	public final String getStylePrimaryName() {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	public int getZIndex() {
		return Integer.parseInt(getCss("zIndex"));
	}

	public void growBy(final Size size) {
		setSize(getBounds().getSize().growBy(size));
	}

	public void moveBy(final Size offset) {
		setLocation(getBounds().getLocation().moveBy(offset));
	}

	public void relayout() {
		layoutChildren(getClientBounds());
	}

	public final void releaseCapture() {
		DOM.releaseCapture(getElement());
	}

	@Deprecated
	@Override
	public final void removeStyleDependentName(final String styleSuffix) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Deprecated
	@Override
	public final void removeStyleName(final String style) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	public void setBackground(final Background background) {
		unapplyCssProperties(this.background);
		this.background = background;
		applyCssProperties(this.background);
	}

	public void setBorder(final Border border) {
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

	public void setBounds(final Rectangle bounds) {
		Rectangle oldBounds = this.bounds;

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

		if (!bounds.getSize().equals(oldBounds.getSize())) {
			relayout();
		}
	}

	public final void setCapture() {
		DOM.setCapture(getElement());
	}

	public void setColor(final Color color) {
		this.color = color;
		setCss("color", color.getCssValue());
	}

	public void setCursor(final Cursor cursor) {
		unapplyCssProperties(this.cursor);
		this.cursor = cursor;
		applyCssProperties(this.cursor);
	}

	public void setFont(final Font font) {
		unapplyCssProperties(this.font);
		this.font = font;
		applyCssProperties(this.font);
	}

	@Override
	@Deprecated
	public final void setHeight(final String height) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Deprecated
	@Override
	public final void setLayoutData(final Object layoutData) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	public void setLocation(final int x, final int y) {
		setBounds(bounds.moveTo(new Point(x, y)));
	}

	public void setLocation(final Point location) {
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
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
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

	public void setSize(final int width, final int height) {
		setBounds(bounds.resizeTo(new Size(width, height)));
	}

	public void setSize(final Size size) {
		setBounds(bounds.resizeTo(size));
	}

	@Override
	@Deprecated
	public final void setSize(final String width, final String height) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Deprecated
	@Override
	public final void setStyleName(final String style) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Deprecated
	@Override
	public final void setStylePrimaryName(final String style) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	@Override
	@Deprecated
	public final void setWidth(final String width) {
		throw new UnsupportedOperationException("This method is not supported on AbsWidgets");
	}

	public void setZIndex(final int zIndex) {
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

	protected void applyChildCssProperties(final UIObject child, final HasCssProperties cssProps) {
		if (cssProps != null) {
			for (Map.Entry<String, String> prop : cssProps.getCssProperties().entrySet()) {
				setChildCss(child, prop.getKey(), prop.getValue());
			}
		}
	}

	protected String getCss(final String property) {
		return DOM.getStyleAttribute(element, property);
	}

	protected String getJsProperty(final String property) {
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
	protected void setChildBounds(final UIObject widget, final int x, final int y, final int width, final int height) {
		if (widget instanceof AbsWidget) {
			throw new IllegalArgumentException("This method is designed only for non-AbsWidget widgets");
		}
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

	protected void setChildCss(final UIObject child, final String property, final String value) {
		if (child instanceof AbsWidget) {
			throw new IllegalArgumentException("This method is designed only for non-AbsWidget widgets");
		}
		DOM.setStyleAttribute(child.getElement(), property, value);
	}

	protected void setCss(final String property, final String value) {
		DOM.setStyleAttribute(element, property, value);
	}

	protected void setJsProperty(final String property, final String value) {
		DOM.setElementProperty(element, property, value);
	}

	private void applyCssProperties(final HasCssProperties cssProps) {
		if (cssProps != null) {
			for (Map.Entry<String, String> prop : cssProps.getCssProperties().entrySet()) {
				setCss(prop.getKey(), prop.getValue());
			}
		}
	}

	private void installDragHandlers() {
		DragHandlers dragHandler = new DragHandlers();
		dragRegistrations = new ArrayList<HandlerRegistration>();
		dragRegistrations.add(addMouseDownHandler(dragHandler));
		dragRegistrations.add(addMouseMoveHandler(dragHandler));
		dragRegistrations.add(addMouseUpHandler(dragHandler));
		dragRegistrations.add(addLoseCaptureHandler(dragHandler));
	}

	private void unapplyCssProperties(final HasCssProperties cssProps) {
		if (cssProps != null) {
			for (Map.Entry<String, String> prop : cssProps.getCssProperties().entrySet()) {
				setCss(prop.getKey(), "");
			}
		}
	}

	private void uninstallDragHandlers() {
		for (HandlerRegistration dragRegistration : dragRegistrations) {
			dragRegistration.removeHandler();
		}
		dragRegistrations = null;
	}

}
