package com.factoria2.absolute.widgets.basic.menu;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.factoria2.absolute.widgets.basic.event.CommandEvent;
import com.factoria2.absolute.widgets.basic.event.CommandHandler;
import com.factoria2.absolute.widgets.basic.event.HasCommandHandlers;
import com.factoria2.absolute.widgets.basic.event.HasItemSelectionHandlers;
import com.factoria2.absolute.widgets.basic.event.ItemSelectionEvent;
import com.factoria2.absolute.widgets.basic.event.ItemSelectionHandler;
import com.factoria2.absolute.widgets.core.AbsWidget;
import com.factoria2.absolute.widgets.core.aspect.Background;
import com.factoria2.absolute.widgets.core.aspect.Border;
import com.factoria2.absolute.widgets.core.aspect.Shadow;
import com.factoria2.absolute.widgets.core.aspect.background.ColorBackground;
import com.factoria2.absolute.widgets.core.aspect.background.LinearGradientBackground;
import com.factoria2.absolute.widgets.core.aspect.value.BorderType;
import com.factoria2.absolute.widgets.core.aspect.value.Color;
import com.factoria2.absolute.widgets.core.aspect.value.HAlignment;
import com.factoria2.absolute.widgets.core.geom.Insets;
import com.factoria2.absolute.widgets.core.geom.Rectangle;
import com.factoria2.absolute.widgets.core.geom.Size;
import com.google.gwt.event.shared.HandlerRegistration;

public class Menu extends AbsWidget implements HasCommandHandlers, HasItemSelectionHandlers<MenuItem> {

	private static final Insets horizontalInsets = new Insets(0, 0);
	private static final Insets verticalInsets = new Insets(0, 3, 0, 0);

	private static final Background horizDefaultBackground = new LinearGradientBackground(new Color(0xFA, 0xFA, 0xFA), new Color(0xCE, 0xCE, 0xCE), true);
	private static final Background vertDefaultBackground = ColorBackground.WHITE;

	private static final Border horizDefaultBorder = new Border(BorderType.SOLID, new Insets(0, 0, 0, 1), new Color(0x2B, 0x2C, 0x28));
	private static final Border vertDefaultBorder = new Border(BorderType.SOLID, new Insets(0, 0, 0, 3), new Insets(3, 0, 3, 3), Color.WHITE);

	private static final Shadow horizDefaultShadow = null;
	private static final Shadow vertDefaultShadow = Shadow.STANDARD_BLACK;

	private List<MenuItem> leftItems = new ArrayList<MenuItem>();
	private List<MenuItem> roLeftItems = Collections.unmodifiableList(leftItems);

	private List<MenuItem> rightItems = new ArrayList<MenuItem>();
	private List<MenuItem> roRightItems = Collections.unmodifiableList(leftItems);

	private List<MenuItem> allItems = new AbstractList<MenuItem>() {
		@Override
		public MenuItem get(final int index) {
			if (index >= leftItems.size()) {
				return rightItems.get(index - leftItems.size());
			} else {
				return leftItems.get(index);
			}
		}

		@Override
		public int size() {
			return leftItems.size() + rightItems.size();
		}
	};

	private boolean vertical;
	private MenuItem activeItem;

	public Menu() {
		this(false);
	}

	public Menu(final boolean vertical) {
		setBackground(vertical ? vertDefaultBackground : horizDefaultBackground);
		setBorder(vertical ? vertDefaultBorder : horizDefaultBorder);
		setShadow(vertical ? vertDefaultShadow : horizDefaultShadow);
		this.vertical = vertical;
	}

	@Override
	public HandlerRegistration addCommandHandler(final CommandHandler handler) {
		return addHandler(handler, CommandHandler.TYPE);
	}

	public void addItem(final MenuItem item) {
		addItem(item, HAlignment.LEFT);
	}

	public void addItem(final MenuItem item, final HAlignment alignment) {
		item.setMenu(this);
		switch (alignment) {
			case LEFT: {
				leftItems.add(item);
				break;
			}
			case RIGHT: {
				rightItems.add(item);
				break;
			}
			default: {
				throw new IllegalArgumentException("Unsupported alignment: " + alignment);
			}
		}
		addChild(item);
		relayout();
	}

	@Override
	public HandlerRegistration addItemSelectionHandler(final ItemSelectionHandler<MenuItem> handler) {
		return addHandler(handler, ItemSelectionHandler.TYPE);
	}

	public void clearItems() {
		for (MenuItem item : allItems) {
			item.detachFromMenu();
		}
		leftItems.clear();
		rightItems.clear();
	}

	public void fireCommand(final String command) {
		setActiveItem(null);
		CommandEvent.fire(Menu.this, command);
	}

	public MenuItem getActiveItem() {
		return activeItem;
	}

	public List<MenuItem> getAllItems() {
		return allItems;
	}

	public List<MenuItem> getItems(final HAlignment alignment) {
		switch (alignment) {
			case LEFT: {
				return roLeftItems;
			}
			case RIGHT: {
				return roRightItems;
			}
			default: {
				throw new IllegalArgumentException("Unsupported alignment: " + alignment);
			}
		}
	}

	@Override
	public Size getPreferredSize() {
		Size size;
		if (vertical) {
			// Get items' width and height
			int h = 0;
			int w = 0;
			for (MenuItem item : allItems) {
				Size isize = item.getPreferredSize();
				h += isize.getHeight();
				if (w < isize.getWidth()) {
					w = isize.getWidth();
				}
			}
			size = new Size(w, h);

			// Enlarge size by margins 
			size = size.growBy(verticalInsets.getAggregatedSize());
		} else {
			// Get items' width and height
			int h = 0;
			int w = 0;
			for (MenuItem item : allItems) {
				Size isize = item.getPreferredSize();
				w += isize.getWidth();
				if (h < isize.getHeight()) {
					h = isize.getHeight();
				}
			}
			size = new Size(w, h);

			// Enlarge size by margins 
			size = size.growBy(horizontalInsets.getAggregatedSize());
		}

		// Enlarge size by border 
		if (getBorder() != null) {
			size = size.growBy(getBorder().getWidth().getAggregatedSize());
		}
		return size;
	}

	public boolean isVertical() {
		return vertical;
	}

	public void setActiveItem(final MenuItem activeItem) {
		if (activeItem != null && !activeItem.isActivable()) {
			throw new IllegalArgumentException("Item is not activable");
		}
		if (this.activeItem != null) {
			this.activeItem.setActive(false);
			ItemSelectionEvent.fireItemDeselected(this, this.activeItem);
		}
		this.activeItem = activeItem;
		if (this.activeItem != null) {
			this.activeItem.setActive(true);
			ItemSelectionEvent.fireItemSelected(this, this.activeItem);
		}
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		if (vertical) {
			int w = clientBounds.getWidth() - verticalInsets.getAggregatedSize().getWidth();
			int y = verticalInsets.getTop();
			for (MenuItem item : allItems) {
				Size size = item.getPreferredSize();
				item.setBounds(horizontalInsets.getLeft(), y, w, size.getHeight());
				y += size.getHeight();
			}
		} else {
			int h = clientBounds.getHeight() - horizontalInsets.getAggregatedSize().getHeight();
			int x = horizontalInsets.getLeft();
			for (MenuItem item : leftItems) {
				Size size = item.getPreferredSize();
				item.setBounds(x, horizontalInsets.getTop(), size.getWidth(), h);
				x += size.getWidth();
			}
			x = clientBounds.getWidth() - horizontalInsets.getRight();
			for (int i = rightItems.size() - 1; i >= 0; i--) {
				MenuItem item = rightItems.get(i);
				Size size = item.getPreferredSize();
				item.setBounds(x - size.getWidth(), horizontalInsets.getTop(), size.getWidth(), h);
				x += size.getWidth();
			}
		}
	}

}
