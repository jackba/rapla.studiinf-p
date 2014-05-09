package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;

public class CourseOrganigramSearch extends AbstractOrganigramSearch {

	public CourseOrganigramSearch(String categoryId) {
		super("", categoryId, (AbstractSearchPage) Navigation.organisationChart);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected NoDuplicatesList<ResourceDescription> searchRessources(
			List<ResourceDescription> resources) {
		// TODO Auto-generated method stub
		return null;
	}

}
