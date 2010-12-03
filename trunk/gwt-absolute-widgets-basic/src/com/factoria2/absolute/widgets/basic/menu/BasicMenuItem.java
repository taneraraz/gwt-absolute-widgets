package com.factoria2.absolute.widgets.basic.menu;

import com.factoria2.absolute.widgets.aspect.Font;
import com.factoria2.absolute.widgets.aspect.value.Color;
import com.factoria2.absolute.widgets.basic.Image;
import com.factoria2.absolute.widgets.basic.Label;
import com.factoria2.absolute.widgets.geom.Insets;
import com.factoria2.absolute.widgets.geom.Rectangle;
import com.factoria2.absolute.widgets.geom.Size;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;

public abstract class BasicMenuItem extends MenuItem {

	protected static final Size ICON_SIZE = new Size(16, 16);
	protected static final Insets INSETS = new Insets(3, 0);

	private static final int HSEPARATION = 4;
	private static final Font defaultFont = Font.getDefault();

	protected static final Color normalColor = Color.BLACK;
	protected static final Color hoverColor = Color.WHITE;

	private Image check;
	private boolean checked;
	private Image icon;
	private Label text;
	private Label shortcut;
	private Anchor anchor;

	public BasicMenuItem(final String text, final String icon, final String shortcut, final boolean shortcutVisible) {
		this.check = new Image(ICON_SIZE, null);
		addChild(this.check);

		this.icon = new Image(ICON_SIZE, icon);
		addChild(this.icon);

		this.text = new Label(text);
		this.text.setFont(defaultFont);
		addChild(this.text);

		this.shortcut = new Label(shortcut == null ? "" : "^" + shortcut);
		// TODO: this.shortcut.setHorizontalAlignment right
		this.shortcut.setFont(defaultFont);
		addChild(this.shortcut);

		this.anchor = new Anchor();
		this.anchor.setVisible(false);
		if (shortcut != null) {
			this.anchor.setAccessKey(shortcut.charAt(0));
		}
		this.anchor.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				event.stopPropagation();
				onShortcutInvoked();
			}
		});
		addChild(this.anchor);

		setShortcutVisible(shortcutVisible);
	}

	public String getIcon() {
		return icon.getUrl();
	}

	@Override
	public Size getPreferredSize() {
		final Size textSize = text.getPreferredSize();
		final int iconWidth = getIconHeight();

		int h = textSize.getHeight();
		if (getBorder() != null) {
			h += getBorder().getWidth().getAggregatedSize().getHeight();
		}

		int w = 0;
		w += iconWidth;
		if (icon.getUrl() != null) {
			w += iconWidth;
			if (!text.getText().equals("")) {
				w += HSEPARATION;
			}
		}
		w += text.getPreferredSize().getWidth();
		if (shortcut.isVisible() && !shortcut.getText().equals("")) {
			w += 40;
			w += shortcut.getPreferredSize().getWidth();
		}
		w += iconWidth;

		Size size = new Size(w, h);
		size = size.growBy(INSETS.getAggregatedSize());
		if (getBorder() != null) {
			size = size.growBy(getBorder().getWidth().getAggregatedSize());
		}
		return size;
	}

	public String getShortcut() {
		return shortcut.getText();
	}

	public String getText() {
		return text.getText();
	}

	public boolean isChecked() {
		return checked;
	}

	public boolean isShortcutVisible() {
		return shortcut.isVisible();
	}

	public void setBold(final boolean bold) {
		text.setFont(text.getFont().deriveBoldFont(bold));
		shortcut.setFont(shortcut.getFont().deriveBoldFont(bold));
	}

	public final void setChecked(final boolean checked) {
		this.checked = checked;
		if (checked) {
			this.check.setUrl("images/BasicMenuItem/check.png");
		} else {
			this.check.setUrl("images/BasicMenuItem/blank.png");
		}
	}

	public final void setIcon(final String icon) {
		this.icon.setUrl(icon);
	}

	public final void setShortcut(final String shortcut) {
		this.shortcut.setText(shortcut);
	}

	public void setShortcutVisible(final boolean shortcutVisible) {
		shortcut.setVisible(shortcutVisible);
	}

	public final void setText(final String text) {
		this.text.setText(text);
		relayout();
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + text.getText() + "]";
	}

	@Override
	protected void layoutChildren(final Rectangle clientBounds) {
		final Size textSize = text.getPreferredSize();
		final int iconWidth = getIconHeight();
		final int h = clientBounds.getHeight();

		int x = INSETS.getLeft();

		check.setBounds(x, (h - iconWidth) / 2, iconWidth, iconWidth);
		x += iconWidth;

		if (icon.getUrl() != null) {
			icon.setBounds(x, (h - iconWidth) / 2, iconWidth, iconWidth);
			x += iconWidth + HSEPARATION;
		}

		text.setBounds(x, 0, textSize.getWidth(), h);

		if (shortcut.isVisible()) {
			int shortcutWidth = shortcut.getPreferredSize().getWidth();
			shortcut.setBounds(clientBounds.getWidth() - iconWidth - shortcutWidth, 0, shortcutWidth, h);
		}
	}

	protected abstract void onShortcutInvoked();

	@Override
	protected void setActive(final boolean active) {
		super.setActive(active);
		if (active) {
			text.setColor(hoverColor);
			shortcut.setColor(hoverColor);
		} else {
			text.setColor(normalColor);
			shortcut.setColor(normalColor);
		}
	}

	private int getIconHeight() {
		if (!text.getText().equals("")) {
			return text.getPreferredSize().getHeight();
		} else {
			text.setVisible(false);
			text.setText("W");
			int r = text.getPreferredSize().getHeight();
			text.setText("");
			text.setVisible(true);
			return r;
		}

	}
}
