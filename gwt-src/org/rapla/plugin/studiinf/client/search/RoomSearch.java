package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;


public class RoomSearch extends AbstractSearch{

private static final int NAME = 0;
private static final int TYPE = 1;


public RoomSearch(String searchTerm, RoomSearchPage rsPage)
{
	super(searchTerm,rsPage);
}



@Override
public NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources) {
	
	NoDuplicatesList<ResourceDescription> roomMatched = new NoDuplicatesList<ResourceDescription>();
	
	roomMatched.addAll(SearchUtils.startsWithSearchTerm(NAME, searchString, resources));
	roomMatched.addAll(SearchUtils.startsWithSearchTerm(TYPE, searchString, resources));
	roomMatched.addAll(SearchUtils.containsSearchTerm(NAME, searchString, resources));
	//roomMatched.addAll(SearchUtils.containsSearchTerm(TYPE, searchTerm, resources));
	roomMatched.addAll(SearchUtils.containsName(searchString, resources));
		
	return roomMatched;
}


	
	
}
