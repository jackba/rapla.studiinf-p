package org.rapla.plugin.studiinf.client.ui;

import java.util.Date;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PageHeader extends VerticalPanel {
	
	private HorizontalPanel subHeader = new HorizontalPanel();
	private AbstractPage parent;
	private static DateTimeFormat dateFormatter = DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM);
	
	
	public PageHeader(AbstractPage parent) {
			this.parent = parent;
		    this.addStyleName("header");
		    this.add(new HTML("<h1>"+parent.getTitle()+"</h1>"));
		    subHeader.addStyleName("subHeader");
		    
		 		    
			Date d = new Date();
			subHeader.add(new HTML("<p>"+dateFormatter.format(d)+"</p>"));
		    this.add(subHeader);
		    Scheduler scheduler = Scheduler.get();
		    scheduler.scheduleFixedPeriod(new RepeatingCommand() {
				
				@Override
				public boolean execute() {
					Date d = new Date();
					
					subHeader.clear();
					subHeader.add(new HTML("<p>"+dateFormatter.format(d)+"</p>"));
					return true;
				}
			}, 1000);
	}


	public void refresh() {
		this.clear();
		this.add(new HTML("<h1>"+parent.getTitle()+"</h1>"));
	    this.add(subHeader);
		
	}
	
	
	
}
