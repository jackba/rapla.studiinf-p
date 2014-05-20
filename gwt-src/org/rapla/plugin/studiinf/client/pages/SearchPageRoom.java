package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomSearch;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

/**
 * 
 * @author Team StudiInf
 * Page for searching rooms
 */
public class SearchPageRoom extends AbstractSearchPage {
	
	public static final String ResourceType = "rooms";
	
	public SearchPageRoom() {
		super(true, true,true, FontIcon.Rooms, Navigation.roomDetail);
	}

	@Override
	public String getTitle() {
		return Studiinf.i18n.roomSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "room";
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public void updateResults(List<ResourceDescription> results)
	{
		clearResult();
		for(ResourceDescription room : results)
		{
			addResult(new ResultButton(room.getName(), Navigation.roomDetail, room.getId(), FontIcon.Rooms, this));
			
		}
		refresh();
	}
	
	@Override
	protected void handleSearch(String searchTerm) {
		new RoomSearch(searchTerm, this);
		
	}

	@Override
	public String getResourceType() {
		return ResourceType;
	}

	@Override
	AbstractPage getOrganisationType() {
		return Navigation.organisationChartRoom;
	}

}
