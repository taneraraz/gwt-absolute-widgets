package com.factoria2.absolute.widgets.basic.menu;

import com.factoria2.absolute.widgets.core.AbsWidget;
import com.factoria2.absolute.widgets.core.aspect.Background;
import com.factoria2.absolute.widgets.core.aspect.background.LinearGradientBackground;
import com.factoria2.absolute.widgets.core.aspect.value.Color;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;

public abstract class MenuItem extends AbsWidget {

	private static final Background normalBackground = null;
	private static final Background hoverBackground = new LinearGradientBackground(new Color(0x65, 0x8A, 0xF0), new Color(0x29, 0x63, 0xed), true);

	private Menu menu;
	private boolean activable;

	public MenuItem() {
		this(true);
	}

	public MenuItem(final boolean activable) {
		this.activable = activable;
		if (activable) {
			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(final MouseOverEvent event) {
					getMenu().setActiveItem(MenuItem.this);
				}
			});
			addMouseOutHandler(new MouseOutHandler() {
				@Override
				public void onMouseOut(final MouseOutEvent event) {
					getMenu().setActiveItem(null);
				}
			});
		}
	}

	public Menu getMenu() {
		return menu;
	}

	public boolean isActivable() {
		return activable;
	}

	protected void detachFromMenu() {
		if (menu != null) {
			//			Window.alert("detachFromMenu - " + this);
			removeFromParent();
			menu = null;
		}
	}

	protected void setActive(final boolean active) {
		if (active && activable) {
			setBackground(hoverBackground);
		} else {
			setBackground(normalBackground);
		}
	}

	void setMenu(final Menu menu) {
		if (this.menu != null && menu != null) {
			throw new IllegalStateException("MenuItem is already attached to a menu");
		}
		this.menu = menu;
	}

}