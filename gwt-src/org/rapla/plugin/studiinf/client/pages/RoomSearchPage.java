package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;


public class RoomSearchPage extends AbstractSearchPage {
	
	private static final String ROOMS = "rooms";
	
	public RoomSearchPage() {
		super(false, true,true);
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
	public void updateResults(List<ResourceDescriptor> results)
	{
		clearResult();
		for(ResourceDescriptor room : results)
		{
			addResult(new ResultButton(room.getName(), Navigation.roomDetail, room.getId(), IconProvider.Rooms));
			
		}
		refresh();
	}
	
	@Override
	protected void handleSearch(String searchTerm) {
		new RoomSearch(searchTerm, this);
		
	}

	@Override
	public String getResourceType() {
		return ROOMS;
	}

	@Override
	protected void handleMostFrequent() {
		clearResult();
	}

}
