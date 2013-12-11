package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.ui.RootPanel;

public class Navigation {
	
	public static final AbstractPage homePage = new HomePage();
	public static final AbstractPage person = new PersonSearch();
	public static final AbstractPage course = new CourseSearch();
	public static final AbstractPage poi = new PoiSearch();
	public static final AbstractPage room = new RoomSearch();
	
	
	public static void goToPage(AbstractPage page){
		RootPanel.get().clear();
		RootPanel.get().add(page);
	}

}
