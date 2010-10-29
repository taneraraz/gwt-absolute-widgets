package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.AbsWidget;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.factoria2.absolute.widgets.tools.TextSizer;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;

public class MenuBar extends AbsWidget {

	private static final Insets layoutInsets = new Insets(4);

	private com.google.gwt.user.client.ui.MenuBar menu = new com.google.gwt.user.client.ui.MenuBar();

	public MenuBar() {
		addChild(menu);
		menu.addItem("Archivo", new Command() {
			@Override
			public void execute() {
				Window.alert("Archivo");
			}
		});
		menu.addItem("Ayuda", new Command() {
			@Override
			public void execute() {
				Window.alert("Ayuda");
			}
		});
	}

	@Override
	public Size getPreferredSize() {
		// TODO: compose itemsText
		String itemsText = "File Edit View Window Help PADDING PADDING PADDING";

		Size size = TextSizer.getInstance().getSize(getFont(), itemsText);
		size = size.growBy(layoutInsets.getAggregatedSize());
		if (getBorder() != null) {
			size = size.growBy(getBorder().getWidth().getAggregatedSize());
		}
		return size;
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		setChildBounds(menu, clientBounds.shrinkBy(layoutInsets));
	}
}
