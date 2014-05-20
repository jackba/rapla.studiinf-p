package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPageRoom;
import org.rapla.plugin.studiinf.client.search.RoomSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class RoomSearchTest extends GWTTestCase {
	
	ResourceDescription room1;
	ResourceDescription room2;
	ResourceDescription room3;
	ResourceDescription room4;
	
	List<ResourceDescription> pList;
	List<ResourceDescription> resultList;
	List<ResourceDescription> expectedList;
	SearchPageRoom rsp;

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
		pList = new ArrayList<ResourceDescription>();
		searchTerms1.add("A51");
		searchTerms1.add("Planspielraum");
		searchTerms2.add("H52");
		searchTerms2.add("Planspielraum");
		searchTerms3.add("B354");
		searchTerms3.add("Hörsaal");
		searchTerms4.add("BAudimaxL");
		searchTerms4.add("Hörsaal");
		room1 = new ResourceDescription("01", "A51 Planspielraum" , "testURL", searchTerms1);
		room2 = new ResourceDescription("02", "H52 Planspielraum" , "testURL", searchTerms2);
		room3 = new ResourceDescription("03", "B354 WI Hörsaal" , "testURL", searchTerms3);
		room4 = new ResourceDescription("04", "BAudimaxL Hörsaal" , "testURL", searchTerms4);
		
		pList.add(room1);
		pList.add(room2);
		pList.add(room3);
		pList.add(room4);
		rsp = new SearchPageRoom();
			
	}
	
	
	public void testSearchResourcesOrder()
	{	
		init();
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
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
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
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
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
		RoomSearch rs = new RoomSearch("xüx", rsp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
}
