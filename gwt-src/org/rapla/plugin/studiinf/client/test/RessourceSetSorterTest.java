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
			// TODO Auto-generated method stub
			return "test";
		}
	};

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return "org.rapla.plugin.studiinf.studiinf";
	}
	
	public void init()
	{
		map = new LinkedHashMap<String, String>();
		map.put("test2", "2");
		map.put("test5", "5");
		map.put("test4", "4");
		map.put("test1", "1");
		map.put("test3", "3");
		
		sorter = new RessourceSetSorter(map, hasPrefix);
	}
	
	public void testOrder()
	{
		init();
		SortedMap = new LinkedHashMap<String, Integer>();
		SortedMap.put("test5", 5);
		SortedMap.put("test4", 4);
		SortedMap.put("test3", 3);
		SortedMap.put("test2", 2);
		SortedMap.put("test1", 1);
		
		assertEquals(SortedMap.toString(), sorter.getSortedSet().toString());
	}

}
