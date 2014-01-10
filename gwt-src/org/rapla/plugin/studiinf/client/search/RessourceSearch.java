package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;

public class RessourceSearch extends AbstractSearch {
	private RessourceButton button;
	public RessourceSearch(String searchTerm, AbstractSearchPage page, RessourceButton button) {
		super(searchTerm, page, false);
		this.button = button;
		init();
	}

	@Override
	protected NoDuplicatesList<ResourceDescriptor> searchRessources(
			List<ResourceDescriptor> resources) {
		NoDuplicatesList<ResourceDescriptor> roomMatched = new NoDuplicatesList<ResourceDescriptor>();
		
		roomMatched.addAll(SearchUtils.containsName(searchString, resources));
			
		return roomMatched;
	}
	
	@Override
	public void onSuccess(List<ResourceDescriptor> arg0) {
		if(!resourcesMap.containsKey(page)){
			resourcesMap.put(page, arg0);
		}
		NoDuplicatesList<ResourceDescriptor> ressourcesMatched = searchRessources(resourcesMap.get(page));
		if(ressourcesMatched.size() >= 1){
			button.updateResults(ressourcesMatched.getFirst());
		}

	}
	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
