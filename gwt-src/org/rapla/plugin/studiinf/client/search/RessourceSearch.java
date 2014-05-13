package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;

import com.google.gwt.user.client.Window;

public class RessourceSearch extends AbstractSearch {
	private RessourceButton button;
	public RessourceSearch(String searchTerm, SearchPageInterface page, RessourceButton button) {
		super(searchTerm, page, false);
		this.button = button;
		init();
	}

	@Override
	protected NoDuplicatesList<ResourceDescription> searchRessources(
			List<ResourceDescription> resources) {
		NoDuplicatesList<ResourceDescription> roomMatched = new NoDuplicatesList<ResourceDescription>();
		
		roomMatched.addAll(SearchUtils.containsName(searchString, resources));
			
		return roomMatched;
	}
	
	@Override
	public void onSuccess(List<ResourceDescription> arg0) {
		if(!resourcesMap.containsKey(cacheKey())){
			resourcesMap.put(cacheKey(), arg0);
		}
//		Window.alert("all: "+resourcesMap.get(page));
		NoDuplicatesList<ResourceDescription> ressourcesMatched = searchRessources(resourcesMap.get(cacheKey()));
		if(ressourcesMatched.size() >= 1){
			button.updateResults(ressourcesMatched.getFirst());
		}else{
			//Window.alert(searchString + " not found: "+ressourcesMatched.toString());
		}

	}
	@Override
	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub
		//Window.alert(arg0.toString());
		
	}
	
	

}
