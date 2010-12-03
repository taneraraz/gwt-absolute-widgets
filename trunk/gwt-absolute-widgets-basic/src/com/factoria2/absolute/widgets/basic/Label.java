package com.factoria2.absolute.widgets.basic;

import com.factoria2.absolute.widgets.AbsWidget;
import com.factoria2.absolute.widgets.aspect.value.HAlignment;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.factoria2.absolute.widgets.tools.TextSizer;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;

// TODO: horizontal and vertical alignment
public class Label extends AbsWidget {

	private com.google.gwt.user.client.ui.HTML label;
	private Integer preferredWidth;
	private Integer preferredHeight;
	private HAlignment horizontalAlignment = HAlignment.LEFT;
	private String text;

	public Label() {
		this("");
	}

	public Label(final String text) {
		this(text, null, null);
	}

	public Label(final String text, final Integer preferredWidth, final Integer preferredHeight) {
		this.preferredWidth = preferredWidth;
		this.preferredHeight = preferredHeight;

		label = new com.google.gwt.user.client.ui.HTML();
		setChildCss(label, "cursor", "default");
		setChildCss(label, "overflow", "hidden");
		addChild(label);
		label.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(final MouseDownEvent event) {
				event.preventDefault();
			}
		});

		setText(text);
	}

	public HAlignment getHorizontalAlignment() {
		return horizontalAlignment;
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
			int height = TextSizer.getInstance().getHeight(getFont(), label.getHTML(), preferredWidth);
			prefSize = new Size(preferredWidth, height);
		} else if (preferredHeight != null) {
			int width = TextSizer.getInstance().getWidth(getFont(), label.getHTML(), preferredHeight);
			prefSize = new Size(width, preferredHeight);
		} else {
			prefSize = TextSizer.getInstance().getSize(getFont(), label.getHTML());
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
		return text;
	}

	public void setHorizontalAlignment(final HAlignment horizontalAlignment) {
		this.horizontalAlignment = horizontalAlignment;
		setChildCss(label, "textAlign", horizontalAlignment.getCssValue());
	}

	public void setPreferredHeight(final Integer preferredHeight) {
		this.preferredHeight = preferredHeight;
	}

	public void setPreferredWidth(final Integer preferredWidth) {
		this.preferredWidth = preferredWidth;
	}

	public void setText(final String text) {
		this.text = text == null ? "" : text;
		label.setHTML(escape(text));
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		setChildBounds(label, clientBounds);
	}

	private String escape(final String text) {
		if (text == null) {
			return "";
		} else {
			// TODO: escapar %
			return text.replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll("\\n", "<br>");
		}
	}

}
