package org.rapla.plugin.studiinf.client;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.Iterator;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

public class LocalStorage {

	private Storage localStorage = Storage.getLocalStorageIfSupported();
//	private Integer count = 0;
	int count = 0;
	private Map<String, String> map;
	

	 
	public void writeStorage(String targetID){

		String mytargetID = "org.rapla.plugins.studiinf_" + Navigation.idForURL(targetID);
		//System.out.println("1: " + targetID);
		
		if (readStorage(targetID) == null){
			count = 0;
		}
		
		else if (count < 0) {
			count = 0;
		}
		
		else {
			count = Integer.parseInt(readStorage(targetID));
		}
		
		count++;
		localStorage.setItem(mytargetID, String.valueOf(count));
//		System.out.println("2: "+ targetID +"|"+ readStorage(targetID));
	}
	
	public String readStorage(String targetID){
//		System.out.println("3: " + targetID + " " + count);
		targetID = "org.rapla.plugins.studiinf_" + Navigation.idForURL(targetID);
//		System.out.println("4: " + targetID + "|" +localStorage.getItem(targetID));
		return localStorage.getItem(targetID);
	}
	
	public void fillMap(){
			map = new StorageMap(localStorage);
//			Map<String, Integer> mapInt = new HashMap<String, Integer>();
//			
//			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
//			while (iterator.hasNext()) {
//				@SuppressWarnings("rawtypes")
//				Map.Entry mapEntry = (Map.Entry) iterator.next();
//				System.out.println(mapEntry.getValue());
//				System.out.println(mapEntry.getKey());
//				//mapInt.put((String) mapEntry.getKey(), Integer.parseInt(mapEntry.getValue().toString()));
//			}
			
			ValueComparator vc = new ValueComparator(map);
			System.out.println("");
			System.out.println("map 1:"+ map);		
			System.out.println("Sorted:" + vc.getSortedSet());
//			TreeMap<String,Double> sorted_map = new TreeMap<String,Double>(bvc);			
//			System.out.println("Test 5: "+sorted_map);
//			for (Map.Entry<String,String> entry : map.entrySet()){
//				if (entry.getKey().startsWith("org.rapla.plugins.studiinf_")){
//					System.out.println("Sorted Map 1: " + Navigation.idForService(entry.getKey().replace("org.rapla.entities.domain.Allocatable_org.rapla.plugins.studiinf_", "")));
//					sorted_map.put(Navigation.idForService(entry.getKey().replace("org.rapla.entities.domain.Allocatable_org.rapla.plugins.studiinf_", "")), Double.parseDouble(entry.getValue()));
//				System.out.println("Test 6: "+sorted_map);
//				}
//			}
//			System.out.println("map:" + map);
//			System.out.println("sorted_map: " + sorted_map);
			
	}
	
}
