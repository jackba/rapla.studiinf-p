package org.rapla.plugin.studiinf.client;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;

import com.google.gwt.text.client.IntegerParser;

//import edu.emory.mathcs.backport.java.util.TreeMap;

public class ValueComparator implements Comparator<String> {

		
		private Map<String, Integer> sortedMap = new HashMap<String, Integer>();
		
	    private SortedSet<Map.Entry<String,Integer>> sortedEntries;
	    
	    private LocalStorage ls;
	   
	    public ValueComparator(Map<String, String> map, LocalStorage ls) {
			this.ls = ls;
		 sortedEntries = new TreeSet<Map.Entry<String,Integer>>(
	    		new Comparator<Map.Entry<String,Integer>>() {
	            @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
	                int res =e1.getValue().compareTo(e2.getValue()) * (-1);
	                if (e1.getKey().equals(e2.getKey())) {
	                    return res; // Code will now handle equality properly
	                } else {
	                    return res != 0 ? res : 1; // While still adding all entries
	                }
	            }
	        }
	    );
		 
	    //System.out.println("Map:  " + map);
	    for(Map.Entry<String, String> entry : map.entrySet() ){
	    	if(entry.getKey().startsWith(ls.getPrefix())){
	    		sortedEntries.add(new ResourceEntry(entry));
	    	}    	
	    }
	    
//	    System.out.println("erstes: "+sortedEntries.subSet(sortedEntries.first(), sortedEntries.last()));
//	    for (Entry<String, Integer> keyValuePair : sortedEntries) {
//	    	   System.out.println("key: " + keyValuePair.getKey() + " value: " + keyValuePair.getValue());
//			   sortedMap.put(keyValuePair.getKey(), keyValuePair.getValue());
//			   System.out.println("Test 1: "+sortedMap);
//	        }
	    
//	    System.out.println("Sorted Map:" + sortedMap);
		
	    }
	    
	    public SortedSet<Map.Entry<String,Integer>> getSortedSet(){
	    	return sortedEntries;
	    }
	    
	    
	    	
	    



		// Note: this comparator imposes orderings that are inconsistent with equals.    
	    public int compare(String a, String b) {


	    	
////	    	System.out.println("Test: " +map.get(a));
////	    	
////	    	
////	    	String aText = map.get(a);
////	    	 System.out.println("Map VC 2:" + aText);
////
////	    	String bText = map.get(b);
////	    	 System.out.println("Map VC 3:" + bText);
////	    	
//	    	if(aText == null){
//	    		aText = "0";
//	    	}
//	    	
//	    	if(bText == null){
//	    		bText = "0";
//	    	}
//	    	
//	        if (Integer.parseInt(aText) <= Integer.parseInt(bText)) {
//	            return -1;
//	        } else {
	            return 1;
	        } // returning 0 would merge keys
//	    }
	}
