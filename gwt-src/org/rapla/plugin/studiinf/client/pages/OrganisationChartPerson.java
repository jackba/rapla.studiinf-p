package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

public class OrganisationChartPerson extends OrganisationChart {

	@Override
	public String getHistoryKey() {
		return "orgPerson";
	}
	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		organigram.clearResults();	
		for(ResourceDescription person : ressourcesMatched)
		{
			organigram.addResult(new ResultButton(person.getName(), Navigation.personDetail, person.getId(), IconProvider.Persons, this));
		}
		organigram.refresh();
	}
	@Override
	public String getResourceType() {
		return PersonSearchPage.ResourceType;
	}
}
