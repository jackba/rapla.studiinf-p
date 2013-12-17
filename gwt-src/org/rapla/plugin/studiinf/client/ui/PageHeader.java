package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PageHeader extends VerticalPanel {
	
	
	
	
	public PageHeader(AbstractPage parent) {
		
		    this.setStyleName("header");
		    this.add(new HTML("<h1>"+parent.getTitle()+"</h1>"));
		    
	}
	
	
	
}
