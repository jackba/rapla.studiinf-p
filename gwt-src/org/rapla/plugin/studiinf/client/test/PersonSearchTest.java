package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.PersonSearchPage;
import org.rapla.plugin.studiinf.client.search.PersonSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonSearchTest extends GWTTestCase{

	ResourceDescriptor person1;
	ResourceDescriptor person2;
	ResourceDescriptor person3;
	ResourceDescriptor person4;
	
	List<ResourceDescriptor> pList;
	List<ResourceDescriptor> resultList;
	List<ResourceDescriptor> expectedList;
	PersonSearchPage psp;
	
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
		searchTerms1.add("Roland");
		searchTerms1.add("Küstermann");
		searchTerms2.add("Silvia");
		searchTerms2.add("Lauer");
		searchTerms3.add("Klaus");
		searchTerms3.add("Müller-Meier");
		searchTerms4.add("Jürgen");
		searchTerms4.add("Lausen");
		person1 = new ResourceDescriptor("01", "Prof. Dr. R. Küstermann" , "testURL", searchTerms1);
		person2 = new ResourceDescriptor("02", "Prof. Dr. S. Lauer" , "testURL", searchTerms2);
		person3 = new ResourceDescriptor("03", "Prof. Dr. K. Müller-Meier" , "testURL", searchTerms3);
		person4 = new ResourceDescriptor("04", "Prof. Dr. J. Lausen" , "testURL", searchTerms4);
		
		pList.add(person1);
		pList.add(person2);
		pList.add(person3);
		pList.add(person4);
		psp = new PersonSearchPage();
	}
		
		public void testSearchResourcesMixedNames()
		{	
			init();
			resultList = new ArrayList<ResourceDescriptor>();
			expectedList = new ArrayList<ResourceDescriptor>();
			expectedList.add(person2);
			expectedList.add(person4);
			expectedList.add(person3);
			PersonSearch ps = new PersonSearch("lau", psp);
			resultList = ps.searchRessources(pList);
			assertEquals(expectedList, resultList);
		}
		
		public void testSearchResourcesMixedNames2()
		{	
			init();
			resultList = new ArrayList<ResourceDescriptor>();
			expectedList = new ArrayList<ResourceDescriptor>();
			expectedList.add(person1);
			expectedList.add(person3);
			expectedList.add(person4);
			PersonSearch ps = new PersonSearch("ü", psp);
			resultList = ps.searchRessources(pList);
			assertEquals(expectedList, resultList);
		}
		
		public void testSearchResourcesFail()
		{	
			init();
			resultList = new ArrayList<ResourceDescriptor>();
			expectedList = new ArrayList<ResourceDescriptor>();
			PersonSearch ps = new PersonSearch("xüx", psp);
			resultList = ps.searchRessources(pList);
			assertEquals(expectedList, resultList);
		}
			
	

}
