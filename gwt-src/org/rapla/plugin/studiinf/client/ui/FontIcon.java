package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Image;

public class FontIcon extends Image {
	
	
	
	public FontIcon() {
		setElement(DOM.createSpan());
	}
	public FontIcon(String url) {
		this();
		setUrl(url);
	}
	@Override
	public void setPixelSize(int width, int height) {
		this.getElement().getStyle().setFontSize(Math.max(width, height), Unit.PX);
		super.setPixelSize(width, height);
	}
	@Override
	public void setUrl(SafeUri url) {
		setUrl(url.asString());
	}
	
	@Override
	public void setUrl(String url) {
		getElement().setClassName(url);
	}
	
}
