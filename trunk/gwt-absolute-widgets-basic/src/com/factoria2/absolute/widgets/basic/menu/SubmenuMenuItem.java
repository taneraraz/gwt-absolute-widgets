package com.factoria2.absolute.widgets.basic.menu;

import com.factoria2.absolute.widgets.aspect.value.HAlignment;
import com.factoria2.absolute.widgets.basic.Image;
import com.factoria2.absolute.widgets.basic.event.CommandEvent;
import com.factoria2.absolute.widgets.basic.event.CommandHandler;
import com.factoria2.absolute.widgets.basic.event.ItemSelectionAdapter;
import com.factoria2.absolute.widgets.basic.event.ItemSelectionEvent;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;

public class SubmenuMenuItem extends BasicMenuItem {

	private static final Size ICON_EXTRA_SIZE = new Size(ICON_SIZE.getWidth(), 0);

	private Menu submenu;
	private Image expandIcon;

	private HAlignment alignment = HAlignment.LEFT;

	public SubmenuMenuItem(final Menu submenu, final String text) {
		this(submenu, text, null, null);
	}

	public SubmenuMenuItem(final Menu submenu, final String text, final String icon) {
		this(submenu, text, icon, null);
	}

	public SubmenuMenuItem(final Menu submenu, final String text, final String icon, final String shortcut) {
		this(submenu, text, icon, shortcut, true);
	}

	public SubmenuMenuItem(final Menu submenu, final String text, final String icon, final String shortcut, final boolean shortcutVisible) {
		super(text, icon, shortcut, shortcutVisible);

		this.submenu = submenu;

		this.expandIcon = new Image(ICON_SIZE, "images/SubmenuMenuItem/expandIcon.png");
		addChild(expandIcon);

		addChild(submenu);
		submenu.setVisible(false);
		submenu.addCommandHandler(new CommandHandler() {
			@Override
			public void onCommand(final CommandEvent event) {
				getMenu().fireCommand(event.getCommand());
			}
		});
		submenu.addItemSelectionHandler(new ItemSelectionAdapter<MenuItem>() {
			@Override
			public void onItemSelected(final ItemSelectionEvent<MenuItem> event) {
				getMenu().setActiveItem(SubmenuMenuItem.this);
				relayout();
			}
		});
	}

	public HAlignment getAlignment() {
		return alignment;
	}

	@Override
	public Size getPreferredSize() {
		Size size = super.getPreferredSize();
		if (getMenu() == null) {
			int a = 1;
			a = a + 2;
		}
		if (getMenu().isVertical()) {
			size = size.growBy(ICON_EXTRA_SIZE);
		}
		return size;
	}

	public void setAlignment(final HAlignment alignment) {
		this.alignment = alignment;
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		super.layoutChildren(clientBounds.shrinkSizeBy(ICON_EXTRA_SIZE));

		if (getMenu().isVertical()) {
			int iconW = clientBounds.getHeight() - INSETS.getAggregatedSize().getHeight();
			expandIcon.setBounds(clientBounds.getWidth() - iconW, (clientBounds.getHeight() - iconW) / 2, iconW, iconW);
		} else {
			expandIcon.setVisible(false);
		}

		if (getMenu().isVertical()) {
			submenu.setBounds(getClientBounds().getWidth(), 0, submenu.getPreferredSize());
		} else {
			switch (alignment) {
				case LEFT: {
					submenu.setBounds(0, getClientBounds().getHeight(), submenu.getPreferredSize());
					break;
				}
				case RIGHT: {
					Size size = submenu.getPreferredSize();
					submenu.setBounds(getClientBounds().getWidth() - size.getWidth(), getClientBounds().getHeight(), size);
					break;
				}
			}
		}
	}

	@Override
	protected void onShortcutInvoked() {
		getMenu().setActiveItem(this);
	}

	@Override
	protected void setActive(final boolean active) {
		super.setActive(active);
		if (active) {
			relayout();
		}
		submenu.setVisible(active);
	}
}
