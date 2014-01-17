package org.rapla.plugin.studiinf.client.test;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;
import org.rapla.plugin.studiinf.client.search.PoiSearch;

import com.google.gwt.junit.client.GWTTestCase;

public class POISearchTest extends GWTTestCase {
	
	ResourceDescriptor poi1;
	ResourceDescriptor poi2;
	ResourceDescriptor poi3;
	ResourceDescriptor poi4;
	
	List<ResourceDescriptor> pList;
	List<ResourceDescriptor> resultList;
	List<ResourceDescriptor> expectedList;
	PoiSearchPage psp;

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
		
		psp = new PoiSearchPage();
			
	}
	
	
	public void testSearchResources()
	{	
		init();
		resultList = new ArrayList<ResourceDescriptor>();
		expectedList = new ArrayList<ResourceDescriptor>();
		expectedList.add(poi1);
		expectedList.add(poi2);
		expectedList.add(poi3);
		expectedList.add(poi4);
		
		PoiSearch ps = new PoiSearch("test",psp);
		resultList = ps.searchRessources(pList);
		assertEquals(expectedList, resultList);
	}

}
