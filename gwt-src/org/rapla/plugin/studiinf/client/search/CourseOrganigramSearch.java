package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;

public class CourseOrganigramSearch extends AbstractOrganigramSearch {

	public CourseOrganigramSearch(String categoryId, SearchPageInterface page) {
		super("", categoryId, page);
	}

	@Override
	protected NoDuplicatesList<ResourceDescription> searchRessources(List<ResourceDescription> resources) {
		NoDuplicatesList<ResourceDescription> coursesMatched = new NoDuplicatesList<ResourceDescription>();
		coursesMatched.addAll(resources);
		return coursesMatched;
	}

}
