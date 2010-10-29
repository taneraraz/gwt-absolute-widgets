package com.factoria2.absolute.widgets.tools;

import java.util.Map;

import com.factoria2.absolute.widgets.aspect.Font;
import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class TextSizer {

	private static final TextSizer instance = new TextSizer();

	public static TextSizer getInstance() {
		return instance;
	}

	private Label label = new Label();

	public TextSizer() {
		//		label.addClickHandler( new ClickHandler() {
		//			@Override
		//			public void onClick(ClickEvent event) {
		//				int w = label.getOffsetWidth();
		//				int h = label.getOffsetHeight();
		//				Window.alert(w+"x"+h);
		//			}
		//		});

		Element element = label.getElement();
		DOM.setStyleAttribute(element, "position", "absolute");
		DOM.setStyleAttribute(element, "left", "0px");
		DOM.setStyleAttribute(element, "top", "0px");
		DOM.setStyleAttribute(element, "visibility", "hidden");
		//		DOM.setStyleAttribute(element, "zIndex", "1000" );
		//		DOM.setStyleAttribute(element, "color", "red" );
		//		DOM.setStyleAttribute(element, "backgroundColor", "white" );
		RootPanel.get().add(label);
	}

	public int getHeight(final Font font, final String text, final int forcedWidth) {
		initLabel(font, text, forcedWidth, null);
		return label.getOffsetHeight();
	}

	public Size getSize(final Font font, final String text) {
		initLabel(font, text, null, null);
		return getLabelOffsetSize();
	}

	public int getWidth(final Font font, final String text, final int forcedHeight) {
		initLabel(font, text, null, forcedHeight);
		return label.getOffsetWidth();
	}

	private Size getLabelOffsetSize() {
		int w = label.getOffsetWidth();
		int h = label.getOffsetHeight();
		return new Size(w + 1, h);
	}

	private void initLabel(final Font font, final String text, final Integer width, final Integer height) {
		label.setText(text);

		Element element = label.getElement();
		for (Map.Entry<String, String> entry : font.getCssProperties().entrySet()) {
			DOM.setStyleAttribute(element, entry.getKey(), entry.getValue());
		}

		if (width == null) {
			DOM.setStyleAttribute(element, "width", "");
		} else {
			DOM.setStyleAttribute(element, "width", width + "px");
		}

		if (width == null) {
			DOM.setStyleAttribute(element, "height", "");
		} else {
			DOM.setStyleAttribute(element, "height", height + "px");
		}
	}

}
