package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.search.RoomSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class RoomSearchPage extends AbstractSearchPage {

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
		addResult( new ResultButton(1, "D 001", Navigation.roomDetail,"001", new Image(IconProvider.ROOMS)));
		addResult(new ResultButton(2, "D 002",Navigation.roomDetail,"002", new Image(IconProvider.ROOMS)));
		addResult(new ResultButton(3, "D 003",Navigation.roomDetail,"003", new Image(IconProvider.ROOMS)));
		addResult(new ResultButton(4, "D 004",Navigation.roomDetail,"004", new Image(IconProvider.ROOMS)));
		addResult(new ResultButton(5, "D 005",Navigation.roomDetail,"005", new Image(IconProvider.ROOMS)));
		addResult(new ResultButton(6, "D 006",Navigation.roomDetail,"006", new Image(IconProvider.ROOMS)));
	}

	@Override
	public boolean hasOrganigramm() {
		return false;
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

}
