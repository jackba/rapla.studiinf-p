package org.rapla.plugin.studiinf.client;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class to sort the LocalStorage-objects
 *
 */
public class RessourceSetSorter implements Comparator<Map.Entry<String,Integer>> {

	    private static final int DESCENDING = -1;
		private final SortedSet<Map.Entry<String,Integer>> sortedEntries;
	    
	    public RessourceSetSorter(Map<String, String> map, HasPrefix ls) {
		 sortedEntries = new TreeSet<Map.Entry<String,Integer>>( this );
		 
		    for(Map.Entry<String, String> entry : map.entrySet() ){
		    	if(entry.getKey().startsWith(ls.getPrefix())){
		    		sortedEntries.add(new ResourceEntry(entry));
		    	}    	
		    }

	    }
	    /**
	     * Returns the sorted set
	     * @return the sorted set
	     */
	    public SortedSet<Map.Entry<String,Integer>> getSortedSet(){
	    	return sortedEntries;
	    }

	    @Override 
	    public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2) {
                return e1.getValue().compareTo(e2.getValue()) * DESCENDING;
        }
		
	}
