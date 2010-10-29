package com.factoria2.absolute.widgets;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.AbsolutePanel;

class AbsWidgetPanel extends AbsolutePanel implements HasClickHandlers {

	@Override
	public HandlerRegistration addClickHandler(final ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

}
