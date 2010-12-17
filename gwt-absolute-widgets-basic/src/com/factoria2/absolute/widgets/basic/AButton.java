package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.core.AbsWidget;
import com.factoria2.absolute.widgets.core.geom.Rectangle;
import com.factoria2.absolute.widgets.core.geom.Size;
import com.factoria2.absolute.widgets.core.tools.TextSizer;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;

public class AButton extends AbsWidget {

	// TODO: pintar el botton no con button de GWT sino con nuestras movidas
	private com.google.gwt.user.client.ui.Button button;

	public AButton() {
		this("");
	}

	public AButton(final String text) {
		button = new com.google.gwt.user.client.ui.Button(text);
		addChild(button);
	}

	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return button.addClickHandler(handler);
	}

	@Override
	public Size getPreferredSize() {
		Size size = TextSizer.getInstance().getSize(getFont(), getText());
		size = size.growBy(new Size(12, 8));
		if (getBorder() != null) {
			size = size.growBy(getBorder().getWidth().getAggregatedSize());
		}
		return size;
	}

	public String getText() {
		return button.getHTML();
	}

	public void setText(final String text) {
		button.setHTML(text);
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		setChildBounds(button, clientBounds);
	}

}
