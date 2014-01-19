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
			ValueComparator vc = new ValueComparator(map);
			System.out.println("");
			System.out.println("map 1:"+ map);		
			System.out.println("Sorted:" + vc.getSortedSet());

			
			
	}
	
}
