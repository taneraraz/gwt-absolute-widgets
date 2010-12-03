package com.factoria2.absolute.widgets.basic.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasItemSelectionHandlers<T> extends HasHandlers {
	HandlerRegistration addItemSelectionHandler(ItemSelectionHandler<T> handler);
}
