package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;
import org.rapla.plugin.studiinf.client.search.RoomSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class RoomSearchTest extends GWTTestCase {
	
	ResourceDescriptor room1;
	ResourceDescriptor room2;
	ResourceDescriptor room3;
	ResourceDescriptor room4;
	
	List<ResourceDescriptor> pList;
	List<ResourceDescriptor> resultList;
	List<ResourceDescriptor> expectedList;
	RoomSearchPage rsp;

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return "org.rapla.plugin.studiinf.studiinf";
	}

	public void init()
	{
		List<String> searchTerms1 = new ArrayList<String>();
		List<String> searchTerms2 = new ArrayList<String>();
		List<String> searchTerms3 = new ArrayList<String>();
		List<String> searchTerms4 = new ArrayList<String>();
		pList = new ArrayList<ResourceDescriptor>();
		searchTerms1.add("A51");
		searchTerms1.add("Planspielraum");
		searchTerms2.add("H52");
		searchTerms2.add("Planspielraum");
		searchTerms3.add("B354");
		searchTerms3.add("Hörsaal");
		searchTerms4.add("BAudimaxL");
		searchTerms4.add("Hörsaal");
		room1 = new ResourceDescriptor("01", "A51 Planspielraum" , "testURL", searchTerms1);
		room2 = new ResourceDescriptor("02", "H52 Planspielraum" , "testURL", searchTerms2);
		room3 = new ResourceDescriptor("03", "B354 WI Hörsaal" , "testURL", searchTerms3);
		room4 = new ResourceDescriptor("04", "BAudimaxL Hörsaal" , "testURL", searchTerms4);
		
		pList.add(room1);
		pList.add(room2);
		pList.add(room3);
		pList.add(room4);
		rsp = new RoomSearchPage();
			
	}
	
	
	public void testSearchResourcesOrder()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(room1);
		expectedList.add(room4);
		expectedList.add(room2);
		expectedList.add(room3);
		
		RoomSearch rs = new RoomSearch("A", rsp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testSearchResourcesOrder2()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(room2);
		expectedList.add(room3);
		expectedList.add(room4);
		RoomSearch rs = new RoomSearch("H", rsp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testSearchResourcesFail()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		RoomSearch rs = new RoomSearch("xüx", rsp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
}
