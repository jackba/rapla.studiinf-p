package org.rapla.plugin.studiinf.client.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

/**
 * 
 * @author Team StudiInf
 * Template for organigramm search
 */
public abstract class AbstractOrganigramSearch implements AsyncCallback<List<ResourceDescription>> {
	protected String searchString;
	protected SearchPageInterface page;
	protected static Map<String,List<ResourceDescription>> resourcesMap = new HashMap<String,List<ResourceDescription>>();
	
	protected String categoryId;
		
	public AbstractOrganigramSearch(String searchTerm, String categoryId,SearchPageInterface page,boolean autoinit) {
		if(searchTerm == null){
			searchTerm ="";
		}
		this.categoryId = categoryId;
		this.searchString = searchTerm.toLowerCase();
		this.page = page;
		if(autoinit){
			init();
		}
	}
	
	public AbstractOrganigramSearch(String searchTerm,String categoryId,SearchPageInterface page) {
		this(searchTerm,categoryId,page,true);
	}
	
	/**
	 * loads needed resources for given category
	 */
	public void init(){
		if( !resourcesMap.containsKey(cacheKey()))
			{
			ServiceProvider.getResources(getResourceType(), categoryId, this);
			}
		else
		{
			this.onSuccess(resourcesMap.get(cacheKey()));
		}
	}

	/**
	 * @return
	 */
	protected String cacheKey() {
		if(categoryId == null){
			return page.getHistoryKey()+"_null";
		}else{
			return page.getHistoryKey()+"_"+categoryId;
		}
	}
	
	/**
	 * 
	 * @param arg0
	 */
	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param arg0
	 */
	@Override
	public void onSuccess(List<ResourceDescription> arg0) {
		if(!resourcesMap.containsKey(cacheKey())){
			resourcesMap.put(cacheKey(), arg0);
		}
		NoDuplicatesList<ResourceDescription> ressourcesMatched = searchRessources(resourcesMap.get(cacheKey()));
		page.updateResults(ressourcesMatched);
	}

	/**
	 * Searches in a list resources and returns the resources that match the search conditions
	 * @param resources List of resources you want to search in
	 * @return resources that match the search conditions
	 */
	protected abstract NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources);

	protected final String getResourceType() {
		return page.getResourceType();
	}
}
