package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class RoomSearchPage extends AbstractSearchPage {
	
	private static final String ROOMS = "rooms";
	
	public RoomSearchPage() {
		super(false, true,true);
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
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
		int counter = 1;
		for(ResourceDescriptor room : results)
		{
			addResult(new ResultButton(counter, room.getName(), Navigation.roomDetail, room.getId(), new Image(IconProvider.ROOMS)));
			counter++;
			
		}
	}
	
	@Override
	protected void handleSearch(String searchTerm) {
		new RoomSearch(searchTerm, this);
		
	}

	@Override
	public String getResourceType() {
		return ROOMS;
	}

}
