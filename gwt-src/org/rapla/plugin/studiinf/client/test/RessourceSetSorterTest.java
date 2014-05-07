package org.rapla.plugin.studiinf.client.test;

import java.util.LinkedHashMap;

import org.rapla.plugin.studiinf.client.HasPrefix;
import org.rapla.plugin.studiinf.client.RessourceSetSorter;

import com.google.gwt.junit.client.GWTTestCase;

public class RessourceSetSorterTest extends GWTTestCase{
	
	public LinkedHashMap<String, String> map;
	public LinkedHashMap<String, Integer> SortedMap;
	public RessourceSetSorter sorter;
	public HasPrefix hasPrefix = new HasPrefix() {
		
		@Override
		public String getPrefix() {
			return "test";
		}
	};

	@Override
	public String getModuleName() {
		return "org.rapla.plugin.studiinf.studiinf";
	}
	
	public void init()
	{
		SortedMap = new LinkedHashMap<String, Integer>();
		SortedMap.put("test5", 5);
		SortedMap.put("test4", 4);
		SortedMap.put("test3", 3);
		SortedMap.put("test2", 2);
		SortedMap.put("test1", 1);
		
		map = new LinkedHashMap<String, String>();		
	}
	
	public void testOrder()
	{
		init();
		map.put("test2", "2");
		map.put("test5", "5");
		map.put("test4", "4");
		map.put("test1", "1");
		map.put("test3", "3");
		sorter = new RessourceSetSorter(map, hasPrefix);
		checkResult();
	}
	
	public void testPrefix () 
	{
		init();
		map.put("test2", "2");
		map.put("XXtest2", "2");
		map.put("test5", "5");
		map.put("test4", "4");
		map.put("NoTest5", "5");
		map.put("test1", "1");
		map.put("test3", "3");

		sorter = new RessourceSetSorter(map, hasPrefix);
		checkResult();
	}
	
	public void checkDuplicateEntries(){
		init();
		
		map.put("test2", "2");
		map.put("test3", "3");
		map.put("test5", "5");
		map.put("test4", "4");
		map.put("test1", "1");
		map.put("test3", "3");
		
		sorter = new RessourceSetSorter(map, hasPrefix);
		checkResult();
	}
	
	public void checkResult(){
		while (SortedMap.keySet().iterator().hasNext()){
			Object value = SortedMap.get(SortedMap.keySet().iterator().next());
			assertEquals(value , sorter.getSortedSet().first().getValue());
			sorter.getSortedSet().remove(sorter.getSortedSet().first());
			SortedMap.remove(SortedMap.keySet().iterator().next());
		}
	}

}
