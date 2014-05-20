package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPagePoI;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.plugin.studiinf.client.pages.TestSearchPage;
import org.rapla.plugin.studiinf.client.search.PoiSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class POISearchTest extends GWTTestCase {
	
	ResourceDescription poi1;
	ResourceDescription poi2;
	ResourceDescription poi3;
	ResourceDescription poi4;
	
	List<ResourceDescription> pList;
	List<ResourceDescription> resultList;
	List<ResourceDescription> expectedList;
	SearchPageInterface psp;

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
		searchTerms1.add("Casino");
		searchTerms2.add("Audimax");
		searchTerms3.add("Rektorat");
		searchTerms4.add("Planspielräume");
		poi1 = new ResourceDescription("01", "Casino" , "testURL", searchTerms1);
		poi2 = new ResourceDescription("02", "Audimax" , "testURL", searchTerms2);
		poi3 = new ResourceDescription("03", "Rektorat" , "testURL", searchTerms3);
		poi4 = new ResourceDescription("04", "Planspielräume" , "testURL", searchTerms4);
		
		pList.add(poi1);
		pList.add(poi2);
		pList.add(poi3);
		pList.add(poi4);
		
		psp = new TestSearchPage();
			
	}
	
	
	public void testSearchResources()
	{	
		init();
		resultList = new ArrayList<ResourceDescription>();
		expectedList = new ArrayList<ResourceDescription>();
		expectedList.add(poi1);
		expectedList.add(poi2);
		expectedList.add(poi3);
		expectedList.add(poi4);
		
		PoiSearch ps = new PoiSearch("test",psp);
		resultList = ps.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}

}
