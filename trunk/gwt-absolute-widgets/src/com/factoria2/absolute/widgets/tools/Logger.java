package com.factoria2.absolute.widgets.tools;

public final class Logger {

	public native static void log(final String msg) /*-{
		if( typeof(console)!="undefined" ) {
		console.log(msg);
		}
	}-*/;

	private Logger() {
	}
}
