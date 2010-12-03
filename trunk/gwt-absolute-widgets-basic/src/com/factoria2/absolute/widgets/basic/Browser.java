package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.AbsWidget;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.user.client.ui.Frame;

public class Browser extends AbsWidget {

	private static final Size defaultSize = new Size(640, 480);
	private Frame frame;

	public Browser() {
		this("");
	}

	public Browser(final String url) {
		frame = new Frame(url);
		setChildCss(frame, "border", "0");
		addChild(frame);
	}

	@Override
	public Size getPreferredSize() {
		Size size = defaultSize;
		if (getBorder() != null) {
			size = size.growBy(getBorder().getWidth().getAggregatedSize());
		}
		return size;
	}

	public String getUrl() {
		return frame.getUrl();
	}

	public void setUrl(final String url) {
		frame.setUrl(url);
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		setChildBounds(frame, clientBounds);
	}

}
