package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.core.AbsWidget;
import com.factoria2.absolute.widgets.core.geom.Insets;
import com.factoria2.absolute.widgets.core.geom.Rectangle;
import com.factoria2.absolute.widgets.core.geom.Size;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class AImage extends AbsWidget {

	private static final MouseDownHandler preventDraggingHandler = new MouseDownHandler() {
		@Override
		public void onMouseDown(final MouseDownEvent event) {
			event.preventDefault();
		}
	};

	private static final String NULL_IMAGE = "images/Image/blank.png";

	private com.google.gwt.user.client.ui.Image image;
	private Size imageSize;
	private HandlerRegistration preventDraggingRegistration;

	private String url;
	private String hoverUrl;
	private String clickUrl;

	public AImage() {
		this(Size.EMPTYNESS, "");
	}

	public AImage(final Size imageSize, final String url) {
		this(imageSize, url, null, null);
	}

	public AImage(final Size imageSize, final String url, final String hoverUrl, final String clickUrl) {
		this.imageSize = imageSize;
		this.url = url;
		this.hoverUrl = hoverUrl;
		this.clickUrl = clickUrl;

		image = new com.google.gwt.user.client.ui.Image();
		setChildCss(image, "overflow", "hidden");
		addChild(image);
		addMouseOverHandler(new MouseOverHandler() {
			@Override
			public void onMouseOver(final MouseOverEvent event) {
				if (hoverUrl != null) {
					setImageUrlInternal(hoverUrl);
				}
			}
		});
		addMouseOutHandler(new MouseOutHandler() {
			@Override
			public void onMouseOut(final MouseOutEvent event) {
				setImageUrlInternal(url);
			}
		});

		setImageUrlInternal(url);
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public String getHoverUrl() {
		return hoverUrl;
	}

	public Size getImageSize() {
		return imageSize;
	}

	@Override
	public Size getPreferredSize() {
		Size prefSize = imageSize;
		if (getBorder() != null) {
			Insets border = getBorder().getWidth();
			prefSize = prefSize.growBy(border.getAggregatedSize());
		}
		return prefSize;
	}

	public String getUrl() {
		return url;
	}

	public boolean isDraggable() {
		return preventDraggingRegistration == null;
	}

	public void setClickUrl(final String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public void setDraggable(final boolean draggable) {
		if (draggable && preventDraggingRegistration != null) {
			preventDraggingRegistration.removeHandler();
			preventDraggingRegistration = null;
		} else if (!draggable && preventDraggingRegistration == null) {
			preventDraggingRegistration = image.addMouseDownHandler(preventDraggingHandler);
		}
	}

	public void setHoverUrl(final String hoverUrl) {
		this.hoverUrl = hoverUrl;
	}

	public void setImageSize(final Size imageSize) {
		this.imageSize = imageSize;
	}

	public void setUrl(final String url) {
		this.url = url;
		setImageUrlInternal(url);
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		setChildBounds(image, clientBounds);
	}

	private void setImageUrlInternal(final String url) {
		image.setUrl(url == null || url.equals("") ? NULL_IMAGE : url);
	}

}
