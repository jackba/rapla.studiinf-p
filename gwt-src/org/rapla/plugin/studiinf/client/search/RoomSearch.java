package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;


public class RoomSearch extends AbstractSearch{
private static final String ROOMS = "rooms";
private static final int NAME = 0;
private static final int TYPE = 1;


public RoomSearch(String searchTerm, RoomSearchPage rsPage)
{
	super(searchTerm,rsPage);
}



@Override
protected NoDuplicatesList<ResourceDescriptor> searchRessources(List<ResourceDescriptor> resources) {
	
	NoDuplicatesList<ResourceDescriptor> roomMatched = new NoDuplicatesList<ResourceDescriptor>();
	
	roomMatched.addAll(SearchUtils.startsWithSearchTerm(NAME, searchTerm, resources));
	roomMatched.addAll(SearchUtils.startsWithSearchTerm(TYPE, searchTerm, resources));
	roomMatched.addAll(SearchUtils.containsSearchTerm(NAME, searchTerm, resources));
	//roomMatched.addAll(SearchUtils.containsSearchTerm(TYPE, searchTerm, resources));
	roomMatched.addAll(SearchUtils.containsName(searchTerm, resources));
		
	return roomMatched;
}

@Override
protected String getResourceType() {
	return ROOMS;
}

	
	
}
