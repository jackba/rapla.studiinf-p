package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;

/**
 * 
 * @author Team StudiInf
 *
 * Search to find persons
 */
public class PersonSearch extends AbstractSearch{

private static final int FIRST_NAME = 1;
private static final int LAST_NAME = 0;


public PersonSearch(String searchTerm, SearchPageInterface psPage)
{
	super(searchTerm,psPage);
}


@Override
public NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources) {
	NoDuplicatesList<ResourceDescription> personMatched = new NoDuplicatesList<ResourceDescription>();
	
	personMatched.addAll(SearchUtils.startsWithSearchTerm(LAST_NAME, searchString, resources));
	personMatched.addAll(SearchUtils.startsWithSearchTerm(FIRST_NAME, searchString, resources));
	personMatched.addAll(SearchUtils.containsSearchTerm(LAST_NAME, searchString, resources));
	personMatched.addAll(SearchUtils.containsSearchTerm(FIRST_NAME, searchString, resources));
	personMatched.addAll(SearchUtils.containsName(searchString, resources));
		
	return personMatched;
}


	
	
}
