package com.factoria2.absolute.widgets.basic.event;

import com.google.gwt.event.shared.GwtEvent;

public class CommandEvent extends GwtEvent<CommandHandler> {

	public static CommandEvent fire(final HasCommandHandlers source, final String command) {
		CommandEvent event = new CommandEvent(command);
		source.fireEvent(event);
		return event;
	}

	private String command;

	private CommandEvent(final String command) {
		this.command = command;
	}

	@Override
	public Type<CommandHandler> getAssociatedType() {
		return CommandHandler.TYPE;
	}

	public String getCommand() {
		return command;
	}

	@Override
	protected void dispatch(final CommandHandler handler) {
		handler.onCommand(this);
	}

}