package com.factoria2.absolute.widgets.basic.event;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasCommandHandlers extends HasHandlers {
	HandlerRegistration addCommandHandler(CommandHandler handler);
}
