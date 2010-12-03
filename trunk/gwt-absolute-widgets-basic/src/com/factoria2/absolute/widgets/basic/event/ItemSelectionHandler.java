package com.factoria2.absolute.widgets.basic.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

public interface ItemSelectionHandler<T> extends EventHandler {

	public static final Type<ItemSelectionHandler<?>> TYPE = new Type<ItemSelectionHandler<?>>();

	public void onItemDeselected(ItemSelectionEvent<T> event);

	public void onItemSelected(ItemSelectionEvent<T> event);
}