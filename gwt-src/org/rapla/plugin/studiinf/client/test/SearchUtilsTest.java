package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.search.SearchUtils;

import com.google.gwt.junit.client.GWTTestCase;

public class SearchUtilsTest extends GWTTestCase {
	
	ResourceDescriptor person1;
	ResourceDescriptor person2;
	ResourceDescriptor person3;
	ResourceDescriptor person4;
	ResourceDescriptor room1;
	ResourceDescriptor room2;
	ResourceDescriptor room3;
	ResourceDescriptor room4;
	ResourceDescriptor course1;
	ResourceDescriptor course2;
	ResourceDescriptor course3;
	ResourceDescriptor course4;
	ResourceDescriptor poi1;
	ResourceDescriptor poi2;
	ResourceDescriptor poi3;
	ResourceDescriptor poi4;
	
	List<ResourceDescriptor> pList;
	List<ResourceDescriptor> resultList;
	List<ResourceDescriptor> expectedList;

	@Override
	public String getModuleName() {
		return "org.rapla.plugin.studiinf.studiinf";//"org.rapla.plugin.studiinf.client.search.SearchUtils";
	}
	
	
	public void initPersonSearch()
	{
		List<String> searchTerms1 = new ArrayList<String>();
		List<String> searchTerms2 = new ArrayList<String>();
		List<String> searchTerms3 = new ArrayList<String>();
		List<String> searchTerms4 = new ArrayList<String>();
		pList = new ArrayList<ResourceDescriptor>();
		searchTerms1.add("Roland");
		searchTerms1.add("Küstermann");
		searchTerms2.add("Silvia");
		searchTerms2.add("Lauer");
		searchTerms3.add("Antje");
		searchTerms3.add("Müller-Meier");
		searchTerms4.add("Jürgen");
		searchTerms4.add("Lausen");
		person1 = new ResourceDescriptor("01", "Prof. Dr. R. Küstermann" , "testURL", searchTerms1);
		person2 = new ResourceDescriptor("02", "Prof. Dr. S. Lauer" , "testURL", searchTerms2);
		person3 = new ResourceDescriptor("03", "Prof. Dr. A. Müller-Meier" , "testURL", searchTerms3);
		person4 = new ResourceDescriptor("04", "Prof. Dr. J. Lausen" , "testURL", searchTerms4);
		
		pList.add(person1);
		pList.add(person2);
		pList.add(person3);
		pList.add(person4);
			
	}
	
	public void initRoomSearch()
	{
		List<String> searchTerms1 = new ArrayList<String>();
		List<String> searchTerms2 = new ArrayList<String>();
		List<String> searchTerms3 = new ArrayList<String>();
		List<String> searchTerms4 = new ArrayList<String>();
		pList = new ArrayList<ResourceDescriptor>();
		searchTerms1.add("A51");
		searchTerms1.add("Planspielraum");
		searchTerms2.add("A52");
		searchTerms2.add("Planspielraum");
		searchTerms3.add("B354");
		searchTerms3.add("Hörsaal");
		searchTerms4.add("BAudimaxL");
		searchTerms4.add("Hörsaal");
		room1 = new ResourceDescriptor("01", "A51 Planspielraum" , "testURL", searchTerms1);
		room2 = new ResourceDescriptor("02", "A52 Planspielraum" , "testURL", searchTerms2);
		room3 = new ResourceDescriptor("03", "B354 WI Hörsaal" , "testURL", searchTerms3);
		room4 = new ResourceDescriptor("04", "BAudimaxL Hörsaal" , "testURL", searchTerms4);
		
		pList.add(room1);
		pList.add(room2);
		pList.add(room3);
		pList.add(room4);
			
	}
	
	public void initCourseSearch()
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
		course4 = new ResourceDescriptor("04", "WBK13B1" , "testURL", searchTerms4);
		
		pList.add(course1);
		pList.add(course2);
		pList.add(course3);
		pList.add(course4);
			
	}
	
	public void initPoiSearch()
	{
		List<String> searchTerms1 = new ArrayList<String>();
		List<String> searchTerms2 = new ArrayList<String>();
		List<String> searchTerms3 = new ArrayList<String>();
		List<String> searchTerms4 = new ArrayList<String>();
		pList = new ArrayList<ResourceDescriptor>();
		searchTerms1.add("Casino");
		searchTerms2.add("Audimax");
		searchTerms3.add("Rektorat");
		searchTerms4.add("Planspielräume");
		poi1 = new ResourceDescriptor("01", "Casino" , "testURL", searchTerms1);
		poi2 = new ResourceDescriptor("02", "Audimax" , "testURL", searchTerms2);
		poi3 = new ResourceDescriptor("03", "Rektorat" , "testURL", searchTerms3);
		poi4 = new ResourceDescriptor("04", "Planspielräume" , "testURL", searchTerms4);
		
		pList.add(poi1);
		pList.add(poi2);
		pList.add(poi3);
		pList.add(poi4);
			
	}
	
	public void testStartsWithSearchTermPersonLastName()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person2);
		expectedList.add(person4);
		resultList = SearchUtils.startsWithSearchTerm(1, "lau", pList);
		assertEquals(expectedList, resultList);
	}
	
//	public void testContainsSearchTermPersonLastName()
//	{	
//		initPersonSearch();
//		resultList = new ArrayList<ResourceDescriptor>();
//		expectedList = new ArrayList<ResourceDescriptor>();
//		expectedList.add(person1);
//		expectedList.add(person3);
//		resultList = SearchUtils.containsSearchTerm(1, "ü", pList);
//		assertEquals(expectedList, resultList);
//	}
	
	public void testStartsWithSearchTermPersonFirstName()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		resultList = SearchUtils.startsWithSearchTerm(0, "roland", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsSearchTermPersonFirstName()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		expectedList.add(person3);
		expectedList.add(person4);
		resultList = SearchUtils.containsSearchTerm(0, "n", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsNamePerson()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		expectedList.add(person3);
		resultList = SearchUtils.containsName("ü", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testStartsWithNamePerson()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		expectedList.add(person2);
		expectedList.add(person3);
		expectedList.add(person4);
		resultList = SearchUtils.startsWithName("Prof", pList);
		assertEquals(expectedList, resultList);
	}
	
	
	
	
	public void testStartsWithSearchTermRoomName()
	{	
		initRoomSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(room1);
		expectedList.add(room2);
		resultList = SearchUtils.startsWithSearchTerm(0, "A5", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testStartsWithSearchTermRoomType()
	{	
		initRoomSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(room3);
		expectedList.add(room4);
		resultList = SearchUtils.startsWithSearchTerm(1, "Hörsaal", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testcontainsSearchTermRoomName()
	{	
		initRoomSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(room1);
		expectedList.add(room2);
		expectedList.add(room3);
		resultList = SearchUtils.containsSearchTerm(0, "5", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsNameRoom()
	{	
		initRoomSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(room4);
		resultList = SearchUtils.containsName("DIMAX", pList);
		assertEquals(expectedList, resultList);
	}
	
	
	
	
	
	public void testStartsWithSearchTermCourseName()
	{	
		initCourseSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(course1);
		expectedList.add(course4);
		resultList = SearchUtils.startsWithSearchTerm(0, "W", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testcontainsSearchTermCourseName()
	{	
		initCourseSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(course3);
		resultList = SearchUtils.containsSearchTerm(0, "12", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsNameCourse()
	{	
		initCourseSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(course2);
		resultList = SearchUtils.containsName("Students", pList);
		assertEquals(expectedList, resultList);
	}
	
	
	
	public void testStartsWithSearchTermPoiName()
	{	
		initPoiSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(poi3);
		resultList = SearchUtils.startsWithSearchTerm(0, "Rektorat", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testcontainsSearchTermPoiName()
	{	
		initPoiSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(poi1);
		resultList = SearchUtils.containsSearchTerm(0, "sino", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsNamePoi()
	{	
		initPoiSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(poi4);
		resultList = SearchUtils.containsName("Räume", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testClearSearchString()
	{
		String expected = "kuestermann";
		assertEquals(expected, SearchUtils.clearSearchString("Küstermann"));
		
	}
	
	public void testStartsWithSearchTermPersonLastNameUmlauts()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		resultList = SearchUtils.startsWithSearchTerm(1, "kue", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsSearchTermPersonLastNameUmlauts()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		expectedList.add(person2);
		expectedList.add(person3);
		resultList = SearchUtils.containsSearchTerm(1, "ue", pList);
		assertEquals(expectedList, resultList);
	}

}
