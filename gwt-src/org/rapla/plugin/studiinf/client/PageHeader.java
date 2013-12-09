package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PageHeader extends VerticalPanel {
	private final AbstractPage parent;
	
	
	
	
	public PageHeader(AbstractPage parent) {
		this.parent = parent;
		
		/* DOM.setStyleAttribute(getElement(), "position", "absolute");
		    DOM.setStyleAttribute(getElement(), "overflow", "hidden");
		    DOM.setStyleAttribute(getElement(), "top", "0px");
		    DOM.setStyleAttribute(getElement(), "left", "0px");
		    DOM.setStyleAttribute(getElement(), "right", "0px");
		    DOM.setStyleAttribute(getElement(), "height", "6.25vh");
		    DOM.setStyleAttribute(getElement(), "width", "100vw");
		    DOM.setStyleAttribute(getElement(), "background", "#e2001a");
		    DOM.setStyleAttribute(getElement(), "color", "#ffffff");*/
		    this.setStyleName("header");
		    this.add(new HTML("<h1>"+parent.getTitle()+"</h1>"));
		    
	}
	
	
	
}
