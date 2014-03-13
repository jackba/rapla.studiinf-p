package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;

public class PoiSearch extends AbstractSearch {

	public PoiSearch(String searchTerm, PoiSearchPage page) {
		super(searchTerm, page);
	}


	@Override
	public NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources) {
		NoDuplicatesList<ResourceDescription> pOIMatched = new NoDuplicatesList<ResourceDescription>();
			pOIMatched.addAll(resources);
		return pOIMatched;
	}


}
