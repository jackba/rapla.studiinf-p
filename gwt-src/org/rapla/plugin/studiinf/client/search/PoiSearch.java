package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;

public class PoiSearch extends AbstractSearch {

	public PoiSearch(String searchTerm, PoiSearchPage page) {
		super(searchTerm, page);
	}


	@Override
	protected NoDuplicatesList<ResourceDescriptor> searchRessources(List<ResourceDescriptor> resources) {
		NoDuplicatesList<ResourceDescriptor> POIMatched = new NoDuplicatesList<ResourceDescriptor>();
		POIMatched.addAll(SearchUtils.startsWithName(searchTerm, resources));
		POIMatched.addAll(SearchUtils.containsName(searchTerm, resources));
			
		return POIMatched;
	}


	@Override
	protected String getResourceType() {
		return "sonstiges";
	}

}
