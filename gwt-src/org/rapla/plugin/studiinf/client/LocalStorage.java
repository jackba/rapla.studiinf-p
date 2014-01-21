package org.rapla.plugin.studiinf.client;

import java.util.Map;
import java.util.Map.Entry;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.search.IdSearch;
import org.rapla.plugin.studiinf.client.search.MFRButtonHandler;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.storage.client.Storage;
import com.google.gwt.storage.client.StorageMap;

public class LocalStorage {

	private Storage localStorage = Storage.getLocalStorageIfSupported();
//	private Integer count = 0;
	int count = 0;
	private Map<String, String> map;
	private String resourceType;
	private ResultTable resultTable;
	private FontIcon icon;
	private AbstractPage targetPage;
	private AbstractSearchPage searchPage;

	public LocalStorage(String resourceType, ResultTable resultTable, FontIcon icon, AbstractPage targetPage, AbstractSearchPage searchPage){
		this.resourceType = resourceType;
		this.resultTable = resultTable;
		this.icon = icon;
		this.targetPage = targetPage;
		this.searchPage = searchPage;
	}
	 
	public void writeStorage(String targetID){

		String mytargetID = getPrefix() + Navigation.idForURL(targetID);
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
		targetID = getPrefix() + Navigation.idForURL(targetID);
//		System.out.println("4: " + targetID + "|" +localStorage.getItem(targetID));
		return localStorage.getItem(targetID);
	}
	
	public String getPrefix(){
		return "org.rapla.plugins.studiinf_" + resourceType + "_";
		
	}
	
	public void fillMap(){
			map = new StorageMap(localStorage);
			ValueComparator vc = new ValueComparator(map,this);
			
			resultTable.clearResults();
			
			int i = 0;
			for (Entry<String, Integer> keyValuePair : vc.getSortedSet()){
				if(i >= 8){
					break;
				}
				i++;
				new IdSearch(new MFRButtonHandler(resultTable, icon, targetPage, searchPage), searchPage, Navigation.idForService(keyValuePair.getKey().replace(getPrefix(), "")));
			}
			
			/*System.out.println("");
			System.out.println("map 1:"+ map);		
			System.out.println("Sorted:" + vc.getSortedSet());*/

			
			
	}
	
}
