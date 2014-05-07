package org.rapla.plugin.studiinf.client;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

//import edu.emory.mathcs.backport.java.util.TreeMap;
/**
 * 
 *
 */
public class RessourceSetSorter implements Comparator<Map.Entry<String,Integer>> {

	    private SortedSet<Map.Entry<String,Integer>> sortedEntries;
	    
	    public RessourceSetSorter(Map<String, String> map, LocalStorage ls) {
		 sortedEntries = new TreeSet<Map.Entry<String,Integer>>( this );
		 
		    for(Map.Entry<String, String> entry : map.entrySet() ){
		    	if(entry.getKey().startsWith(ls.getPrefix())){
		    		sortedEntries.add(new ResourceEntry(entry));
		    	}    	
		    }

	    }
	    
	    public SortedSet<Map.Entry<String,Integer>> getSortedSet(){
	    	return sortedEntries;
	    }

	    @Override public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
            int res =e1.getValue().compareTo(e2.getValue()) * (-1);
            if (e1.getKey().equals(e2.getKey())) {
                return res; // Code will now handle equality properly
            } else {
                return res != 0 ? res : 1; // While still adding all entries
            }
        }
		
	}
