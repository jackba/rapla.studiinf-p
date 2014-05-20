package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPagePoI;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;

public class PoiSearch extends AbstractSearch {

	/**
	 * 
	 * @author Team StudiInf
	 *
	 * Search to find POIs
	 */
	public PoiSearch(String searchTerm, SearchPageInterface page) {
		super(searchTerm, page);
	}


	@Override
	public NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources) {
		NoDuplicatesList<ResourceDescription> pOIMatched = new NoDuplicatesList<ResourceDescription>();
			pOIMatched.addAll(resources);
		return pOIMatched;
	}


}
