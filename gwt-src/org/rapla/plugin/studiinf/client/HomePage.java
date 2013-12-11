package org.rapla.plugin.studiinf.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class HomePage extends AbstractPage {
	
	public HomePage() {
		super();
		
		Button courseBtn = new Tile(i18n.courses());
		Button personBtn = new Tile(i18n.people());
		Button roomBtn = new Tile(i18n.rooms());
		Button poiBtn = new Tile(i18n.pointsOfInterest());
		
		personBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Navigation.goToPage(Navigation.person);
				
			}
		});
		
		courseBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Navigation.goToPage(Navigation.course);
				
			}
		});
		roomBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Navigation.goToPage(Navigation.room);
				
			}
		});
		poiBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Navigation.goToPage(Navigation.poi);
				
			}
		});
		
		this.add(courseBtn);
		this.add(personBtn);
		this.add(roomBtn);
		this.add(poiBtn);
		

	}
	
	@Override
	public String getTitle() {
		return i18n.homeScreenTitle();
	}

}
