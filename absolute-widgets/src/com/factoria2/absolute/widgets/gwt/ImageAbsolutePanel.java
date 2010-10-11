package com.factoria2.absolute.widgets.gwt;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

public class ImageAbsolutePanel extends AbsolutePanel {
	
	private String imageUrl;
	private Image image;
	
	public ImageAbsolutePanel() {
		image = new Image();
		image.setSize("100%", "100%");
		add(image,0,0);
		remove(image);
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		image.setUrl(imageUrl);
		if( imageUrl!=null ){
			insert(image, 0);
		} else {
			remove(image);
		}
	}
	
//	public void setWidgetPosition( Widget w, String left, String top ) {
//		checkWidgetParent(w);
//		Element h = w.getElement();
//		DOM.setStyleAttribute(h, "position", "absolute");
//		DOM.setStyleAttribute(h, "left", left);
//		DOM.setStyleAttribute(h, "top", top);
//	}
//
//	public void setWidgetRight( Widget w, int right ) {
//		checkWidgetParent(w);
//		Element h = w.getElement();
//		DOM.setStyleAttribute(h, "position", "absolute");
//		DOM.setStyleAttribute(h, "left", "");
//		DOM.setStyleAttribute(h, "right", right + "px");
//	}
//
//	public void setWidgetBottom( Widget w, int bottom ) {
//		checkWidgetParent(w);
//		Element h = w.getElement();
//		DOM.setStyleAttribute(h, "position", "absolute");
//		DOM.setStyleAttribute(h, "top", "");
//		DOM.setStyleAttribute(h, "bottom", bottom + "px");
//	}
//	
//	private void checkWidgetParent(Widget w) {
//		if (w.getParent() != this) {
//			throw new IllegalArgumentException(
//					"Widget must be a child of this panel.");
//		}
//	}

}
