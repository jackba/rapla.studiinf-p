package org.rapla.plugin.studiinf.client.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

/**
 * 
 *
 */
public abstract class AbstractOrganigramSearch implements AsyncCallback<List<ResourceDescription>> {
	protected String searchString;
	protected AbstractSearchPage page;
	protected static Map<String,List<ResourceDescription>> resourcesMap = new HashMap<String,List<ResourceDescription>>();
	
	protected String categoryId;
	
	public AbstractOrganigramSearch(String searchTerm, String categoryId,AbstractSearchPage page,boolean autoinit) {
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
	
	public AbstractOrganigramSearch(String searchTerm,String categoryId,AbstractSearchPage page) {
		this(searchTerm,categoryId,page,true);
	}
	
	public void init(){
		if( !resourcesMap.containsKey(page.getHistoryKey()+"_"+categoryId))
			{
			ServiceProvider.getResources(getResourceType(), categoryId, this);
			}
		else
		{
			this.onSuccess(resourcesMap.get(page.getHistoryKey()+"_"+categoryId));
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
		if(!resourcesMap.containsKey(page.getHistoryKey()+"_"+categoryId)){
			resourcesMap.put(page.getHistoryKey()+"_"+categoryId, arg0);
		}
		NoDuplicatesList<ResourceDescription> ressourcesMatched = searchRessources(resourcesMap.get(page.getHistoryKey()+"_"+categoryId));
		page.updateResults(ressourcesMatched);
	}

	protected abstract NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources);

	protected final String getResourceType() {
		return page.getResourceType();
	}
}
