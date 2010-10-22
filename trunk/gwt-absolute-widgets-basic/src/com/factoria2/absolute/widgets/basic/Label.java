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

	public Label(String text) {
		this(text, null, null);
	}

	public Label(String text, Integer preferredWidth, Integer preferredHeight) {
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;

		label = new com.google.gwt.user.client.ui.Label(text);
		setChildCss(label, "cursor", "default");
		addChild(label);
	}

	public String getText() {
		return label.getText();
	}

	public void setText(String text) {
		label.setText(text);
	}

	public Integer getPreferredWidth() {
		return preferredWidth;
	}

	public void setPreferredWidth(Integer preferredWidth) {
		this.preferredWidth = preferredWidth;
	}

	public Integer getPreferredHeight() {
		return preferredHeight;
	}

	public void setPreferredHeight(Integer preferredHeight) {
		this.preferredHeight = preferredHeight;
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

	@Override
	protected void layoutChildren(Rectangle clientBounds) {
		setChildBounds(label, clientBounds);
	}

}
