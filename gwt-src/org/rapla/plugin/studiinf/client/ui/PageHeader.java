package org.rapla.plugin.studiinf.client.ui;

import java.util.Date;

import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author Team StudiInf
 *
 * Pageheader with title and date/time display
 */
public class PageHeader extends VerticalPanel {
	
	private HorizontalPanel subHeader = new HorizontalPanel();
	private AbstractPage parent;
	private static final DateTimeFormat dateFormatter = DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM);
	private HTML dateDisplay;
	private HTML title;
	
	public PageHeader(AbstractPage parent) {
			this.parent = parent;
		    this.addStyleName("header");
		    title = new HTML("<h1>"+parent.getTitle()+"</h1>");
		    this.add(title);
		    subHeader.addStyleName("subHeader");
		    
		 		    
			Date d = new Date();
			dateDisplay = new HTML(dateFormatter.format(d));
			dateDisplay.addStyleName("timeDisplay");
			subHeader.add(dateDisplay);
			if(DisplayMode.isStele()){
				this.add(subHeader);
			}
			
		    Timer timer = new Timer() {
				
				@Override
				public void run() {
					Date d = new Date();
					dateDisplay.setHTML(PageHeader.dateFormatter.format(d));
				}
			};
			timer.scheduleRepeating(1000);
	}


	public void refresh() {
		this.title.setHTML("<h1>"+parent.getTitle()+"</h1>");
		
	}
	
	
	
}
