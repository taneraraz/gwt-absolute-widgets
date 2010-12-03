package com.factoria2.absolute.widgets.basic.menu;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class CommandMenuItem extends BasicMenuItem {

	private String command;

	public CommandMenuItem(final String command, final String text) {
		this(command, text, null, null);
	}

	public CommandMenuItem(final String command, final String text, final String icon) {
		this(command, text, icon, null);
	}

	public CommandMenuItem(final String command, final String text, final String icon, final String shortcut) {
		this(command, text, icon, shortcut, true);
	}

	public CommandMenuItem(final String command, final String text, final String icon, final String shortcut, final boolean shortcutVisible) {
		super(text, icon, shortcut, shortcutVisible);
		this.command = command;
		addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				getMenu().fireCommand(command);
			}
		});
	}

	public String getCommand() {
		return command;
	}

	@Override
	protected void onShortcutInvoked() {
		getMenu().fireCommand(command);
	}

}
