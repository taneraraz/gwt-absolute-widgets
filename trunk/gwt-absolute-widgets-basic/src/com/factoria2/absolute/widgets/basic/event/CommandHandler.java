package com.factoria2.absolute.widgets.basic.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent.Type;

public interface CommandHandler extends EventHandler {

	public static final Type<CommandHandler> TYPE = new Type<CommandHandler>();

	public void onCommand(CommandEvent event);

}