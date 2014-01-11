package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.CourseSearchPage;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;
import org.rapla.plugin.studiinf.client.search.CourseSearch;
import org.rapla.plugin.studiinf.client.search.RoomSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class CourseSearchTest extends GWTTestCase {
	
	ResourceDescriptor course1;
	ResourceDescriptor course2;
	ResourceDescriptor course3;
	ResourceDescriptor course4;
	
	List<ResourceDescriptor> pList;
	List<ResourceDescriptor> resultList;
	List<ResourceDescriptor> expectedList;
	CourseSearchPage csp;

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
		searchTerms1.add("WWI11B1");
		searchTerms2.add("International Students");
		searchTerms3.add("TWIW12ITV");
		searchTerms4.add("WBK131");
		course1 = new ResourceDescriptor("01", "WWI11B1" , "testURL", searchTerms1);
		course2 = new ResourceDescriptor("02", "International Students" , "testURL", searchTerms2);
		course3 = new ResourceDescriptor("03", "TWIW12ITV" , "testURL", searchTerms3);
		course4 = new ResourceDescriptor("04", "WBK12B1" , "testURL", searchTerms4);
		
		
		pList.add(course2);
		pList.add(course3);
		pList.add(course4);
		pList.add(course1);
		
		csp = new CourseSearchPage();
			
	}
	
	
	public void testSearchResourcesOrder()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(course4);
		expectedList.add(course1);
		expectedList.add(course3);
		
		CourseSearch rs = new CourseSearch("W", csp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testSearchResourcesOrder2()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(course3);
		expectedList.add(course4);
		CourseSearch rs = new CourseSearch("12", csp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testSearchResourcesFail()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		CourseSearch rs = new CourseSearch("xüx", csp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
}
