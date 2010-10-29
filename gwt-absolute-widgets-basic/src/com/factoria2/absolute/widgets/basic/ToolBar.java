package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.AbsWidget;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Size;
import com.factoria2.absolute.widgets.tools.TextSizer;

// TODO: implementar la toolbar
public class ToolBar extends AbsWidget {

	private static final Insets layoutInsets = new Insets(4);

	public ToolBar() {
	}

	@Override
	public Size getPreferredSize() {
		Size size = TextSizer.getInstance().getSize(getFont(), "W");
		size = size.growBy(new Size(0, size.getHeight()));
		size = size.growBy(layoutInsets.getAggregatedSize());
		if (getBorder() != null) {
			size = size.growBy(getBorder().getWidth().getAggregatedSize());
		}
		return size;

	}

}
