package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.plugin.studiinf.client.pages.TestSearchPage;
import org.rapla.plugin.studiinf.client.search.PersonSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class PersonSearchTest extends GWTTestCase{

	ResourceDescription person1;
	ResourceDescription person2;
	ResourceDescription person3;
	ResourceDescription person4;
	
	List<ResourceDescription> pList;
	List<ResourceDescription> resultList;
	List<ResourceDescription> expectedList;
	SearchPageInterface psp;
	
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
		searchTerms1.add("Roland");
		searchTerms1.add("Küstermann");
		searchTerms2.add("Silvia");
		searchTerms2.add("Lauer");
		searchTerms3.add("Klaus");
		searchTerms3.add("Müller-Meier");
		searchTerms4.add("Jürgen");
		searchTerms4.add("Lausen");
		person1 = new ResourceDescription("01", "Prof. Dr. R. Küstermann" , "testURL", searchTerms1);
		person2 = new ResourceDescription("02", "Prof. Dr. S. Lauer" , "testURL", searchTerms2);
		person3 = new ResourceDescription("03", "Prof. Dr. K. Müller-Meier" , "testURL", searchTerms3);
		person4 = new ResourceDescription("04", "Prof. Dr. J. Lausen" , "testURL", searchTerms4);
		
		pList.add(person1);
		pList.add(person2);
		pList.add(person3);
		pList.add(person4);
		psp = new TestSearchPage();
	}
		
		public void testSearchResourcesMixedNames()
		{	
			init();
			resultList = new ArrayList<ResourceDescription>();
			expectedList = new ArrayList<ResourceDescription>();
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
			resultList = new ArrayList<ResourceDescription>();
			expectedList = new ArrayList<ResourceDescription>();
			expectedList.add(person1);
			PersonSearch ps = new PersonSearch("rm", psp);
			resultList = ps.searchRessources(pList);
			assertEquals(expectedList, resultList);
		}
		
		public void testSearchResourcesFail()
		{	
			init();
			resultList = new ArrayList<ResourceDescription>();
			expectedList = new ArrayList<ResourceDescription>();
			PersonSearch ps = new PersonSearch("xüx", psp);
			resultList = ps.searchRessources(pList);
			assertEquals(expectedList, resultList);
		}
			
	

}
