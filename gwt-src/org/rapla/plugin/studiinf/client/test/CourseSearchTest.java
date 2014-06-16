package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.TestSearchPage;
import org.rapla.plugin.studiinf.client.search.CourseSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class CourseSearchTest extends GWTTestCase {
	
	ResourceDescription course1;
	ResourceDescription course2;
	ResourceDescription course3;
	ResourceDescription course4;
	
	List<ResourceDescription> pList;
	List<ResourceDescription> resultList;
	List<ResourceDescription> expectedList;
	static TestSearchPage csp;

	@Override
	public String getModuleName() {
		return "org.rapla.plugin.studiinf.studiinf";
	}

	public void init()
	{
		List<String> searchTerms1 = new ArrayList<String>();
		List<String> searchTerms2 = new ArrayList<String>();
		List<String> searchTerms3 = new ArrayList<String>();
		List<String> searchTerms4 = new ArrayList<String>();
		pList = new ArrayList<ResourceDescription>();
		searchTerms1.add("WWI11B1");
		searchTerms2.add("International Students");
		searchTerms3.add("TWIW12ITV");
		searchTerms4.add("WBK131");
		course1 = new ResourceDescription("01", "WWI11B1" , "testURL", searchTerms1);
		course2 = new ResourceDescription("02", "International Students" , "testURL", searchTerms2);
		course3 = new ResourceDescription("03", "TWIW12ITV" , "testURL", searchTerms3);
		course4 = new ResourceDescription("04", "WBK12B1" , "testURL", searchTerms4);
		
		pList.add(course2);
		pList.add(course3);
		pList.add(course4);
		pList.add(course1);
		
		csp = new TestSearchPage();
			
	}
	
	
	public void testSearchResourcesOrder()
	{	
		init();
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
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
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
		expectedList.add(course3);
		expectedList.add(course4);
		CourseSearch rs = new CourseSearch("12", csp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testSearchResourcesFail()
	{	
		init();
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
		CourseSearch rs = new CourseSearch("xüx", csp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testDuplicates()
	{
		init();
		pList.add(course2);
		
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
		expectedList.add(course2);

		CourseSearch rs = new CourseSearch("Inte", csp);
		resultList = rs.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}
}
