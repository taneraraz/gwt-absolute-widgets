package com.factoria2.absolute.widgets.core.tools;

import java.util.Map;

import com.factoria2.absolute.widgets.core.aspect.Font;
import com.factoria2.absolute.widgets.core.geom.Size;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class TextSizer {

	private static final TextSizer instance=new TextSizer();

	public static TextSizer getInstance() {
		return instance;
	}

	private HTML label=new HTML();

	public TextSizer() {
		//		label.addClickHandler( new ClickHandler() {
		//			@Override
		//			public void onClick(ClickEvent event) {
		//				int w = label.getOffsetWidth();
		//				int h = label.getOffsetHeight();
		//				Window.alert(w+"x"+h);
		//			}
		//		});

		label.setStyleName( TextSizer.class.getName() );

		Element element=label.getElement();
		DOM.setStyleAttribute( element, "position", "absolute" );
		DOM.setStyleAttribute( element, "left", "0px" );
		DOM.setStyleAttribute( element, "top", "0px" );
		DOM.setStyleAttribute( element, "visibility", "hidden" );
		//		DOM.setStyleAttribute(element, "zIndex", "1000" );
		//		DOM.setStyleAttribute(element, "color", "red" );
		//		DOM.setStyleAttribute(element, "backgroundColor", "white" );
		RootPanel.get().add( label );
	}

	public int getHeight(final Font font, final String text, final int forcedWidth) {
		initLabel( font, text, forcedWidth, null );
		int ret=label.getOffsetHeight();
		resetLabel();
		return ret;
	}

	public Size getSize(final Font font, final String text) {
		initLabel( font, text, null, null );
		Size ret=getLabelOffsetSize();
		resetLabel();
		return ret;
	}

	public int getWidth(final Font font, final String text, final int forcedHeight) {
		initLabel( font, text, null, forcedHeight );
		int ret=label.getOffsetWidth();
		resetLabel();
		return ret;
	}

	private Size getLabelOffsetSize() {
		int w=label.getOffsetWidth();
		int h=label.getOffsetHeight();
		resetLabel();
		return new Size( w+1, h );
	}

	private void initLabel(final Font font, final String text, final Integer width, final Integer height) {
		label.setHTML( text );

		Element element=label.getElement();
		for( Map.Entry<String, String> entry : font.getCssProperties().entrySet() ) {
			DOM.setStyleAttribute( element, entry.getKey(), entry.getValue() );
		}

		if( width==null ) {
			DOM.setStyleAttribute( element, "width", "" );
		} else {
			DOM.setStyleAttribute( element, "width", width+"px" );
		}

		if( width==null ) {
			DOM.setStyleAttribute( element, "height", "" );
		} else {
			DOM.setStyleAttribute( element, "height", height+"px" );
		}
	}

	private void resetLabel() {
		label.setHTML( "" );
	}

}
