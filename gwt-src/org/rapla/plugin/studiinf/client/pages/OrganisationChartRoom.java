package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

public class OrganisationChartRoom extends OrganisationChart {

	@Override
	public String getHistoryKey() {
		return "orgRoom";
	}
	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		organigram.clearResults();	
		for(ResourceDescription room : ressourcesMatched)
		{
			organigram.addResult(new ResultButton(room.getName(), Navigation.roomDetail, room.getId(), FontIcon.Rooms, this));
		}
		organigram.refresh();
	}
	@Override
	public String getResourceType() {
		return SearchPageRoom.ResourceType;
	}
}
