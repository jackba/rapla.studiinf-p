package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.*;

public class PageFooter extends VerticalPanel {
	private final AbstractPage parent;
	
	public PageFooter(AbstractPage parent) {
		this.parent = parent;
		
		 DOM.setStyleAttribute(getElement(), "position", "absolute");
		    DOM.setStyleAttribute(getElement(), "overflow", "hidden");
		    DOM.setStyleAttribute(getElement(), "bottom", "0px");
		    DOM.setStyleAttribute(getElement(), "left", "0px");
		    DOM.setStyleAttribute(getElement(), "right", "0px");
		    DOM.setStyleAttribute(getElement(), "height", "6.25vh");
		    DOM.setStyleAttribute(getElement(), "width", "100vw");
		    DOM.setStyleAttribute(getElement(), "background", "#5c6971");
		    DOM.setStyleAttribute(getElement(), "color", "#ffffff");
		    this.add(new Button("<i class='fa fa-globe'></i> English"));
		    
	}
	
}
