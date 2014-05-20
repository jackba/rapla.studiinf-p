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

/**
 * Class to handle access to the LocalStorage of the Browser to read the most frequent results
 *
 */
public class LocalStorage implements HasPrefix{

	private static final int MAX_ITEMS_IN_MOST_FREQUENT_RESULTS = 8;
	private Storage localStorage = Storage.getLocalStorageIfSupported();
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
	
	 /**
	  * Increases the numbers of accesses for a resource by one
	  * 
	  * @param targetID The Id of the resource to increase
	  */
	public void increaseInStorage(String targetID){

		String mytargetID = getPrefix() + Navigation.idForURL(targetID);
				
		if (readFromStorage(targetID) == null){
			count = 0;
		}else if (count < 0) {
			count = 0;
		}else {
			count = Integer.parseInt(readFromStorage(targetID));
		}	
		count++;
		localStorage.setItem(mytargetID, String.valueOf(count));
	}
	
	/**
	 * reads the current number of page visits for a resource by given id
	 * @param targetID the id of the resource
	 * @return how often the resource was accessed
	 */
	public String readFromStorage(String targetID){
		targetID = getPrefix() + Navigation.idForURL(targetID);
		return localStorage.getItem(targetID);
	}
	
	@Override
	public String getPrefix(){
		return "org.rapla.plugins.studiinf_" + resourceType + "_";
	}
	
	/**
	 * fills the ResultTable with the most frequent results
	 */
	public void fillMap(){
			map = new StorageMap(localStorage);
			RessourceSetSorter vc = new RessourceSetSorter(map,this);
			
			resultTable.clearResults();
			
			int i = 0;
			for (Entry<String, Integer> keyValuePair : vc.getSortedSet()){
				if(i >= MAX_ITEMS_IN_MOST_FREQUENT_RESULTS){
					break;
				}
				i++;
				new IdSearch(new MFRButtonHandler(resultTable, icon, targetPage, searchPage), searchPage, Navigation.idForService(keyValuePair.getKey().replace(getPrefix(), "")));
			}
			
	}
	
}
