package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.AbsWidget;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.factoria2.absolute.widgets.tools.TextSizer;

public class Label extends AbsWidget {

	private com.google.gwt.user.client.ui.Label label;
	private Integer preferredWidth;
	private Integer preferredHeight;

	public Label() {
		this("");
	}

	public Label(final String text) {
		this(text, null, null);
	}

	public Label(final String text, final Integer preferredWidth, final Integer preferredHeight) {
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;

		label = new com.google.gwt.user.client.ui.Label(text);
		setChildCss(label, "cursor", "default");
		addChild(label);
	}

	public Integer getPreferredHeight() {
		return preferredHeight;
	}

	@Override
	public Size getPreferredSize() {
		Size prefSize;
		if (preferredWidth != null && preferredHeight != null) {
			prefSize = new Size(preferredWidth, preferredHeight);
		} else if (preferredWidth != null) {
			int height = TextSizer.getInstance().getHeight(getFont(), getText(), preferredWidth);
			prefSize = new Size(preferredWidth, height);
		} else if (preferredHeight != null) {
			int width = TextSizer.getInstance().getWidth(getFont(), getText(), preferredHeight);
			prefSize = new Size(width, preferredHeight);
		} else {
			prefSize = TextSizer.getInstance().getSize(getFont(), getText());
		}

		if (getBorder() != null) {
			Insets border = getBorder().getWidth();
			prefSize = prefSize.growBy(border.getAggregatedSize());
		}
		return prefSize;
	}

	public Integer getPreferredWidth() {
		return preferredWidth;
	}

	public String getText() {
		return label.getText();
	}

	public void setPreferredHeight(final Integer preferredHeight) {
		this.preferredHeight = preferredHeight;
	}

	public void setPreferredWidth(final Integer preferredWidth) {
		this.preferredWidth = preferredWidth;
	}

	public void setText(final String text) {
		label.setText(text);
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		setChildCss(label, "lineHeight", clientBounds.getHeight() + "px");
		setChildBounds(label, clientBounds);
	}

}
