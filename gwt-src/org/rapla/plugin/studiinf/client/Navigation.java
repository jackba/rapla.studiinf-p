package org.rapla.plugin.studiinf.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.CourseSearch;
import org.rapla.plugin.studiinf.client.pages.HomePage;
import org.rapla.plugin.studiinf.client.pages.PersonSearch;
import org.rapla.plugin.studiinf.client.pages.PoiSearch;

import org.rapla.plugin.studiinf.client.pages.RoomSearch;

//import com.google.gwt.user.client.Window; Window.alert("text");
import com.google.gwt.user.client.ui.RootPanel;

public class Navigation {
	
	public static final AbstractPage homePage = new HomePage();
	public static final AbstractPage person = new PersonSearch();
	public static final AbstractPage course = new CourseSearch();
	public static final AbstractPage poi = new PoiSearch();
	public static final AbstractPage room = new RoomSearch();
	
	private static List<AbstractPage> pages;
	
	
	
	public static void goToPage(AbstractPage page){
		RootPanel.get().clear();
		RootPanel.get().add(page);
	}
	
	public static void add(AbstractPage page){
		if(pages == null){
			pages = new LinkedList<AbstractPage>();
		}
		pages.add(page);
	}
	
	public static void init(){
		
		Iterator<AbstractPage> iterator = pages.iterator();
		while( iterator.hasNext()){
			AbstractPage page = iterator.next();
			page.init();
		}
	}

}
