package com.factoria2.absolute.widgets.core.aspect.background;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.factoria2.absolute.widgets.core.aspect.Background;
import com.factoria2.absolute.widgets.core.aspect.value.Color;
import com.factoria2.absolute.widgets.core.aspect.value.HAlignment;
import com.factoria2.absolute.widgets.core.aspect.value.ImageAdjustment;
import com.factoria2.absolute.widgets.core.aspect.value.VAlignment;

public class ImageBackground extends Background {

	private Color color;
	private String url;
	private HAlignment horizontalAlignment = HAlignment.CENTER;
	private VAlignment verticalAlignment = VAlignment.MIDDLE;
	private ImageAdjustment adjustment = ImageAdjustment.FIT_RESIZE;
	private Map<String, String> cssProps = new HashMap<String, String>();

	public ImageBackground(final Color color, final String url) {
		this(color, url, HAlignment.CENTER, VAlignment.MIDDLE, ImageAdjustment.FIT_RESIZE);
	}

	public ImageBackground(final Color color, final String url, final HAlignment horizontalAlignment, final VAlignment verticalAlignment, final ImageAdjustment adjustment) {
		this.color = color;
		this.url = url;
		this.horizontalAlignment = horizontalAlignment;
		this.verticalAlignment = verticalAlignment;
		this.adjustment = adjustment;

		String background = "";
		String backgroundSize = "";

		// Compose background
		background += " " + color.getCssValue();
		background += " url(" + url + ")";
		background += " " + horizontalAlignment.getCssValue();
		if (verticalAlignment == VAlignment.MIDDLE) {
			background += " center";
		} else {
			background += " " + verticalAlignment.getCssValue();
		}

		// Compose backgroundSize
		switch (adjustment) {
			case FIT_EXPAND:
			case FIT_HORIZONTAL:
			case FIT_RESIZE:
			case FIT_STRETCH:
			case FIT_VERTICAL: {
				background += " " + ImageAdjustment.NO_REPEAT.getCssValue();
				backgroundSize += " " + adjustment.getCssValue();
				break;
			}

			default: {
				background += " " + adjustment.getCssValue();
				break;
			}
		}

		// Store values
		cssProps.put("background", background);
		if (!backgroundSize.equals("")) {
			cssProps.put("backgroundSize", backgroundSize);
		}
		cssProps = Collections.unmodifiableMap(cssProps);
	}

	public ImageBackground(final Color color, final String url, final ImageAdjustment adjustment) {
		this(color, url, HAlignment.CENTER, VAlignment.MIDDLE, adjustment);
	}

	public ImageAdjustment getAdjustment() {
		return adjustment;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public Map<String, String> getCssProperties() {
		return cssProps;
	}

	public HAlignment getHorizontalAlignment() {
		return horizontalAlignment;
	}

	public String getUrl() {
		return url;
	}

	public VAlignment getVerticalAlignment() {
		return verticalAlignment;
	}

}
