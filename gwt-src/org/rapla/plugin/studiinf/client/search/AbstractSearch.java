package org.rapla.plugin.studiinf.client.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;

import com.google.gwtjsonrpc.common.AsyncCallback;

public abstract class AbstractSearch implements AsyncCallback<List<ResourceDescriptor>> {
	protected String searchTerm;
	protected AbstractSearchPage page;

	 protected static Map<AbstractSearchPage,List<ResourceDescriptor>> resourcesMap = new HashMap<AbstractSearchPage,List<ResourceDescriptor>>();
	
	public AbstractSearch(String searchTerm,AbstractSearchPage page) {
		if(searchTerm == null){
			searchTerm ="";
		}
		this.searchTerm = searchTerm.toLowerCase();
		
		this.page = page;
		if( !resourcesMap.containsKey(page))
			{
			ServiceProvider.getResources(getResourceType(), this);
			}
		else
		{
			this.onSuccess(resourcesMap.get(page));
		}
		
	}
	

@Override
public void onFailure(Throwable arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onSuccess(List<ResourceDescriptor> arg0) {
	if(!resourcesMap.containsKey(page)){
		resourcesMap.put(page, arg0);
	}
	NoDuplicatesList<ResourceDescriptor> ressourcesMatched = searchRessources(resourcesMap.get(page));
	page.updateResults(ressourcesMatched);

}


	protected abstract NoDuplicatesList<ResourceDescriptor> searchRessources(List<ResourceDescriptor> resources);

	protected final String getResourceType() {
		return page.getResourceType();
	}
}
