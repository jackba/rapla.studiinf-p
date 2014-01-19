package org.rapla.plugin.studiinf.client.ui;

import java.util.Date;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PageHeader extends VerticalPanel {
	
	private HorizontalPanel subHeader = new HorizontalPanel();
	private AbstractPage parent;
	
	public PageHeader(AbstractPage parent) {
			this.parent = parent;
		    this.setStyleName("header");
		    this.add(new HTML("<h1>"+parent.getTitle()+"</h1>"));
		    subHeader.addStyleName("subHeader");
			Date d = new Date();
			subHeader.add(new HTML("<p>"+d.toGMTString()+"</p>"));
		    this.add(subHeader);
	}


	public void refresh() {
		this.clear();
		this.add(new HTML("<h1>"+parent.getTitle()+"</h1>"));
	    this.add(subHeader);
		
	}
	
	
	
}
