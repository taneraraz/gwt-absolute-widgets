package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.core.AbsWidget;
import com.factoria2.absolute.widgets.core.geom.Insets;
import com.factoria2.absolute.widgets.core.geom.Size;
import com.factoria2.absolute.widgets.core.tools.TextSizer;

// TODO: implementar la toolbar
public class AToolBar extends AbsWidget {

	private static final Insets layoutInsets = new Insets(4);

	public AToolBar() {
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
