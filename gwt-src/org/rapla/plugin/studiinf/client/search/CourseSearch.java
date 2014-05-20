package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;

/**
 * 
 * @author Team StudiInf
 *
 *Search to find all courses for a given searchTerm
 */
public class CourseSearch extends AbstractSearch {
	
	public CourseSearch(String searchTerm, SearchPageInterface page) {
		super(searchTerm, page);
	}

	@Override
	public NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources) {
		NoDuplicatesList<ResourceDescription> coursesMatched = new NoDuplicatesList<ResourceDescription>();
		coursesMatched.addAll(SearchUtils.startsWithSearchTerm(0, searchString, resources));
		coursesMatched.addAll(SearchUtils.containsSearchTerm(0, searchString, resources));
		coursesMatched.addAll(SearchUtils.containsName(searchString, resources));
			
		return coursesMatched;
	}



}
