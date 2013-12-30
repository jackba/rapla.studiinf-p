package org.rapla.plugin.studiinf.client.search;

	import java.util.List;

	import org.rapla.plugin.freiraum.common.ResourceDescriptor;

import org.rapla.plugin.studiinf.client.pages.PoiSearchPage;


	public class PoiSearch extends AbstractSearch{
		
		// TO DO!! Hier muss noch ne richtige Suche rein, oder hinterher nach nem Label gesucht werden
	private static final String POI = "room";
	private static final int NAME = 0;
	private static final int TYPE = 1;


	public PoiSearch(String searchTerm, PoiSearchPage poisPage)
	{
		super(searchTerm,poisPage);
	}



	@Override
	protected NoDuplicatesList<ResourceDescriptor> searchRessources(List<ResourceDescriptor> resources) {
		
		NoDuplicatesList<ResourceDescriptor> poiMatched = new NoDuplicatesList<ResourceDescriptor>();
		
//		poiMatched.addAll(SearchUtils.startsWithSearchTerm(NAME, searchTerm, resources));
//		poiMatched.addAll(SearchUtils.startsWithSearchTerm(TYPE, searchTerm, resources));
//		poiMatched.addAll(SearchUtils.containsSearchTerm(NAME, searchTerm, resources));
		//poiMatched.addAll(SearchUtils.containsSearchTerm(TYPE, searchTerm, resources));
		poiMatched.addAll(SearchUtils.containsName(searchTerm, resources));
		return poiMatched;
	}

	@Override
	protected String getResourceType() {
		return POI;
	}

		
		

}
