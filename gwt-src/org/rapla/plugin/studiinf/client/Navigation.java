package org.rapla.plugin.studiinf.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.CourseSearch;
import org.rapla.plugin.studiinf.client.pages.DetailPageRoom;
import org.rapla.plugin.studiinf.client.pages.HomePage;
import org.rapla.plugin.studiinf.client.pages.PersonSearch;
import org.rapla.plugin.studiinf.client.pages.PoiSearch;
import org.rapla.plugin.studiinf.client.pages.RoomSearch;

import com.google.gwt.user.client.History;
//import com.google.gwt.user.client.Window; Window.alert("text");
import com.google.gwt.user.client.ui.RootPanel;

public final class Navigation {
	
	public static final AbstractPage homePage = new HomePage();
	public static final AbstractPage person = new PersonSearch();
	public static final AbstractPage course = new CourseSearch();
	public static final AbstractPage poi = new PoiSearch();
	public static final AbstractPage room = new RoomSearch();
	public static final AbstractPage roomDetail = new DetailPageRoom();
	
	
	private static List<AbstractPage> pages;
	
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
	
	
	
	public static void goToPage(AbstractPage page){
		RootPanel.get().clear();
		RootPanel.get().add(page);
		History.newItem(page.getHistoryKey(), false);
	}
	
	public static void goToPage(String key){
		AbstractPage page = getPageByKey(key);
		if(page != null){
			goToPage(page);
		}
	}
	
	private static AbstractPage getPageByKey(String key){
		Iterator<AbstractPage> iterator = pages.iterator();
		while( iterator.hasNext()){
			AbstractPage page = iterator.next();
			if(page.getHistoryKey().equals(key)){
				return page;
			}
		}
		return null;
	}

}
