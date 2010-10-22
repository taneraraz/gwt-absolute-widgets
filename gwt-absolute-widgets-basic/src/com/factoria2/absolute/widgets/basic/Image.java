package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.AbsWidget;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class Image extends AbsWidget {

	private static final MouseDownHandler preventDraggingHandler = new MouseDownHandler() {
		@Override
		public void onMouseDown(MouseDownEvent event) {
			event.preventDefault();
		}
	};

	private com.google.gwt.user.client.ui.Image image;
	private Integer preferredWidth;
	private Integer preferredHeight;
	private HandlerRegistration preventDraggingRegistration;

	public Image() {
		this("");
	}

	public Image(String url) {
		this(url, null, null);
	}

	public Image(String url, Integer preferredWidth, Integer preferredHeight) {
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;

		image = new com.google.gwt.user.client.ui.Image(url);
		addChild(image);
	}

	public boolean isDraggable() {
		return preventDraggingRegistration == null;
	}

	public void setDraggable(boolean draggable) {
		if (draggable && preventDraggingRegistration != null) {
			preventDraggingRegistration.removeHandler();
			preventDraggingRegistration = null;
		} else if (!draggable && preventDraggingRegistration == null) {
			preventDraggingRegistration = image.addMouseDownHandler(preventDraggingHandler);
		}
	}

	public String getUrl() {
		return image.getUrl();
	}

	public void setUrl(String url) {
		image.setUrl(url);
	}

	public Integer getPreferredWidth() {
		return preferredWidth;
	}

	public void setPreferredWidth(Integer preferredWidth) {
		this.preferredWidth = preferredWidth;
	}

	public Integer getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredHeight(Integer preferredHeight) {
		this.preferredHeight = preferredHeight;
	}

	@Override
	public Size getPreferredSize() {
		Size prefSize;
		if (preferredWidth != null && preferredHeight != null) {
			prefSize = new Size(preferredWidth, preferredHeight);
		} else if (preferredWidth != null) {
			prefSize = new Size(preferredWidth, image.getHeight());
		} else if (preferredHeight != null) {
			prefSize = new Size(image.getWidth(), preferredHeight);
		} else {
			prefSize = new Size(image.getWidth(), image.getHeight());
		}

		if (getBorder() != null) {
			Insets border = getBorder().getWidth();
			prefSize = prefSize.growBy(border.getAggregatedSize());
		}
		return prefSize;
	}

	@Override
	protected void layoutChildren(Rectangle clientBounds) {
		setChildBounds(image, clientBounds);
	}

}
