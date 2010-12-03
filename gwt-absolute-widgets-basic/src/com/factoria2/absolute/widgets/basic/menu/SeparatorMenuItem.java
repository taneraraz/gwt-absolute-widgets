package com.factoria2.absolute.widgets.basic.menu;

import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.user.client.ui.HTML;

public class SeparatorMenuItem extends MenuItem {

	private static final Size preferredSize = new Size(100, 12);

	public SeparatorMenuItem() {
		super(false);
		addChild(new HTML("<div style='border-top: 1px solid #d0d0d0;height:1px;margin-top:" + preferredSize.getHeight() / 2 + "px;'></div>"));
	}

	@Override
	public Size getPreferredSize() {
		return preferredSize;
	}
}
