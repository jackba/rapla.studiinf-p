package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.search.PersonSearch;
import org.rapla.plugin.studiinf.client.search.SearchUtils;

import com.google.gwt.junit.client.GWTTestCase;

public class SearchUtilsTest extends GWTTestCase {
	
	ResourceDescriptor person1;
	ResourceDescriptor person2;
	ResourceDescriptor person3;
	ResourceDescriptor person4;
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
	
	public void testStartsWithSearchTermPersonLastName()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person2);
		expectedList.add(person4);
		SearchUtils su = new SearchUtils();
		resultList = su.startsWithSearchTerm(1, "lau", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsSearchTermPersonLastName()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		expectedList.add(person3);
		SearchUtils su = new SearchUtils();
		resultList = su.containsSearchTerm(1, "ü", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testStartsWithSearchTermPersonFirstName()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		SearchUtils su = new SearchUtils();
		resultList = su.startsWithSearchTerm(0, "roland", pList);
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
		SearchUtils su = new SearchUtils();
		resultList = su.containsSearchTerm(0, "n", pList);
		assertEquals(expectedList, resultList);
	}
	
	public void testContainsNamePerson()
	{	
		initPersonSearch();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(person1);
		expectedList.add(person3);
		SearchUtils su = new SearchUtils();
		resultList = su.containsName("ü", pList);
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
		SearchUtils su = new SearchUtils();
		resultList = su.startsWithName("Prof", pList);
		assertEquals(expectedList, resultList);
	}

}
