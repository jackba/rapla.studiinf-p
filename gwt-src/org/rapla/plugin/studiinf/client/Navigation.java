package org.rapla.plugin.studiinf.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractDetailPage;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.RaplaPageCourse;
import org.rapla.plugin.studiinf.client.pages.CourseSearchPage;
import org.rapla.plugin.studiinf.client.pages.DetailPageCourse;
import org.rapla.plugin.studiinf.client.pages.DetailPagePerson;
import org.rapla.plugin.studiinf.client.pages.DetailPagePoi;
import org.rapla.plugin.studiinf.client.pages.DetailPageRoom;
import org.rapla.plugin.studiinf.client.pages.ExtraInfoPage;
import org.rapla.plugin.studiinf.client.pages.HomePage;
import org.rapla.plugin.studiinf.client.pages.OrganisationChartCourse;
import org.rapla.plugin.studiinf.client.pages.OrganisationChartPerson;
import org.rapla.plugin.studiinf.client.pages.OrganisationChartRoom;
import org.rapla.plugin.studiinf.client.pages.RaplaPagePerson;
import org.rapla.plugin.studiinf.client.pages.PersonSearchPage;
import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;
import org.rapla.plugin.studiinf.client.pages.RaplaPageRoom;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;

import com.google.gwt.user.client.History;
//import com.google.gwt.user.client.Window; Window.alert("text");
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Navigation API for changing the active page
 *
 */
public final class Navigation {
	
	public static final AbstractPage personDetail = new DetailPagePerson();
	public static final AbstractPage courseDetail = new DetailPageCourse();
	public static final AbstractPage roomDetail = new DetailPageRoom();
	public static final AbstractPage poiDetail = new DetailPagePoi();
	public static final AbstractPage homePage = new HomePage();
	public static final AbstractPage person = new PersonSearchPage();
	public static final AbstractPage course = new CourseSearchPage();
	public static final AbstractPage poi = new PoiSearchPage();
	public static final AbstractPage room = new RoomSearchPage();
	public static final AbstractPage organisationChartCourse = new OrganisationChartCourse();
	public static final AbstractPage organisationChartPerson = new OrganisationChartPerson();
	public static final AbstractPage organisationChartRoom = new OrganisationChartRoom();
	public static final AbstractPage extraInfo = new ExtraInfoPage(new DetailPagePerson());
	public static final AbstractPage raplaPersonLink = new RaplaPagePerson();
	public static final AbstractPage raplaRoomLink = new RaplaPageRoom();
	public static final AbstractPage raplaCourseLink = new RaplaPageCourse();
	public static AbstractPage activePage = homePage;
	public static final String ID_PREFIX = "";
	private static List<AbstractPage> pages;
	
	/**
	 * Adds page to navigation.
	 * @param page Page, which should be added to the navigation.
	 */
	public static void add(AbstractPage page){
		if(pages == null){
			pages = new LinkedList<AbstractPage>();
		}
		pages.add(page);
	}
	
	/**
	 * Initialises all pages.
	 */
	public static void init(){
		Iterator<AbstractPage> iterator = pages.iterator();
		while( iterator.hasNext()){
			AbstractPage page = iterator.next();
			page.init();
		}
	}
	
	/**
	 * Navigates to a specific page.
	 * @param page Page to which the user want to navigate.
	 */
	public static void goToPage(AbstractPage page){
		goToPage(page,null);
	}
	
	/**
	 * 
	 * @param page Target Page, to which the user wants to navigate.
	 * @param id Id of the ressource of the target Page (Example: PersonId of person detail page)
	 */
	public static void goToPage(AbstractPage page,String id){
		if(page != null){
		RootPanel.get().clear();
		RootPanel.get().add(page);
		
		/* TODO solve Resource Logger*/
		RessourceLogger.logRessource(page, activePage, id);
		
		activePage = page;
		page.onShow();
		if(id != null){
			try {
				AbstractDetailPage detailPage = (AbstractDetailPage) page;
				detailPage.setId(idForService(id));
				History.newItem(page.getHistoryKey()+"/"+idForURL(id), false);
			} catch (Throwable e) {
				History.newItem(page.getHistoryKey(), false);
			}
		}else{
			History.newItem(page.getHistoryKey(), false);
		}
		
		}else{
			//Window.alert("Nav to NULL !");
		}
	}
	
	/**
	 * 
	 * @param key Key of the Target Page.
	 * @param id
	 */
	public static void goToPage(String key, String id){
		AbstractPage page = getPageByKey(key);
		if(page != null){
			goToPage(page, id);
		}
	}

	public static void goToPage(String key){
		AbstractPage page = getPageByKey(key);
		if(page != null){
			goToPage(page);
		}
	}
	
	/**
	 * Searchs for a specific page by key
	 * @param key
	 * @return the page, to the given key
	 */
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
	
	public static String idForService(String idForUrl){
		return ID_PREFIX + idForUrl.replace(ID_PREFIX, "");
	}
	public static String idForURL(String idForService){
		return idForService.replace(ID_PREFIX, "");
	}

}
