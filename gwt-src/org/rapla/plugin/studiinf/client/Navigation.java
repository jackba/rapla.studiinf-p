package org.rapla.plugin.studiinf.client;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractDetailPage;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.CourseRaplaPage;
import org.rapla.plugin.studiinf.client.pages.CourseSearchPage;
import org.rapla.plugin.studiinf.client.pages.DetailPageCourse;
import org.rapla.plugin.studiinf.client.pages.DetailPagePerson;
import org.rapla.plugin.studiinf.client.pages.DetailPagePoi;
import org.rapla.plugin.studiinf.client.pages.DetailPageRoom;
import org.rapla.plugin.studiinf.client.pages.HomePage;
import org.rapla.plugin.studiinf.client.pages.OrganisationChart;
import org.rapla.plugin.studiinf.client.pages.PersonRaplaPage;
import org.rapla.plugin.studiinf.client.pages.PersonSearchPage;
import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;
import org.rapla.plugin.studiinf.client.pages.RoomRaplaPage;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;
import org.rapla.plugin.studiinf.client.pages.extraInfoPage;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.Window; Window.alert("text");
import com.google.gwt.user.client.ui.RootPanel;

public final class Navigation {
	
	public static final AbstractPage homePage = new HomePage();
	public static final AbstractPage person = new PersonSearchPage();
	public static final AbstractPage course = new CourseSearchPage();
	public static final AbstractPage poi = new PoiSearchPage();
	public static final AbstractPage room = new RoomSearchPage();
	public static final AbstractPage roomDetail = new DetailPageRoom();
	public static final AbstractPage poiDetail = new DetailPagePoi();
	public static final AbstractPage organisationChart = new OrganisationChart();
	public static final AbstractPage personDetail = new DetailPagePerson();
	public static final AbstractPage courseDetail = new DetailPageCourse();
	public static final AbstractPage extraInfo = new extraInfoPage();
	public static final AbstractPage raplaPersonLink = new PersonRaplaPage();
	public static final AbstractPage raplaRoomLink = new RoomRaplaPage();
	public static final AbstractPage raplaCourseLink = new CourseRaplaPage();
	
	public static final String ID_PREFIX = "org.rapla.entities.domain.Allocatable_";
	
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
		
		goToPage(page,null);
	}
	public static void goToPage(AbstractPage page,String id){
		if(page != null){
		RootPanel.get().clear();
		RootPanel.get().add(page);
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
			Window.alert("Nav to NULL !");
		}
	}
	
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
