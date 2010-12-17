package com.factoria2.absolute.widgets.core.event;

import com.google.gwt.event.shared.EventHandler;

public interface DragHandler extends EventHandler {
	public void onDragFinished(DragEvent event);

	public void onDragProgressed(DragEvent event);

	public void onDragStarted(DragEvent event);
}