package com.factoria2.absolute.widgets.core.event;

import com.factoria2.absolute.widgets.core.AbsWidget;
import com.factoria2.absolute.widgets.core.geom.Size;
import com.google.gwt.event.shared.GwtEvent;

public class DragEvent extends GwtEvent<DragHandler> {

	public static enum Id {
		DRAG_STARTED, DRAG_FINISHED, DRAG_PROGRESSED
	}

	public static final Type<DragHandler> TYPE = new Type<DragHandler>();

	public static DragEvent fireDragFinished(final HasDragHandlers source, final AbsWidget widget, final Size absoluteOffset) {
		DragEvent event = new DragEvent(Id.DRAG_FINISHED, widget, absoluteOffset, Size.EMPTYNESS);
		source.fireEvent(event);
		return event;
	}

	public static DragEvent fireDragProgressed(final HasDragHandlers source, final AbsWidget widget, final Size absoluteOffset, final Size relativeOffset) {
		DragEvent event = new DragEvent(Id.DRAG_FINISHED, widget, absoluteOffset, relativeOffset);
		source.fireEvent(event);
		return event;
	}

	public static DragEvent fireDragStarted(final HasDragHandlers source, final AbsWidget widget) {
		DragEvent event = new DragEvent(Id.DRAG_STARTED, widget, Size.EMPTYNESS, Size.EMPTYNESS);
		source.fireEvent(event);
		return event;
	}

	private DragEvent.Id id;
	private AbsWidget widget;
	private Size absoluteOffset;
	private Size relativeOffset;

	private DragEvent(final DragEvent.Id id, final AbsWidget widget, final Size absoluteOffset, final Size relativeOffset) {
		this.id = id;
		this.widget = widget;
		this.absoluteOffset = absoluteOffset;
		this.relativeOffset = relativeOffset;
	}

	public Size getAbsoluteOffset() {
		return absoluteOffset;
	}

	@Override
	public Type<DragHandler> getAssociatedType() {
		return TYPE;
	}

	public DragEvent.Id getId() {
		return id;
	}

	public Size getRelativeOffset() {
		return relativeOffset;
	}

	public AbsWidget getWidget() {
		return widget;
	}

	@Override
	protected void dispatch(final DragHandler handler) {
		switch (id) {
			case DRAG_STARTED: {
				handler.onDragStarted(this);
				break;
			}
			case DRAG_PROGRESSED: {
				handler.onDragProgressed(this);
				break;
			}
			case DRAG_FINISHED: {
				handler.onDragFinished(this);
				break;
			}
		}
	}

}