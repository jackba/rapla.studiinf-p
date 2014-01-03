package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.PersonSearchPage;


public class PersonSearch extends AbstractSearch{
private static final String PERSONS = "persons";
private static final int FIRST_NAME = 0;
private static final int LAST_NAME = 1;


public PersonSearch(String searchTerm, PersonSearchPage psPage)
{
	super(searchTerm,psPage);
}


@Override
protected NoDuplicatesList<ResourceDescriptor> searchRessources(List<ResourceDescriptor> resources) {
	NoDuplicatesList<ResourceDescriptor> personMatched = new NoDuplicatesList<ResourceDescriptor>();
	
	personMatched.addAll(SearchUtils.startsWithSearchTerm(LAST_NAME, searchTerm, resources));
	personMatched.addAll(SearchUtils.startsWithSearchTerm(FIRST_NAME, searchTerm, resources));
	personMatched.addAll(SearchUtils.containsSearchTerm(LAST_NAME, searchTerm, resources));
	personMatched.addAll(SearchUtils.containsSearchTerm(FIRST_NAME, searchTerm, resources));
	personMatched.addAll(SearchUtils.containsName(searchTerm, resources));
		
	return personMatched;
}

@Override
protected String getResourceType() {
	return PERSONS;
}

	
	
}