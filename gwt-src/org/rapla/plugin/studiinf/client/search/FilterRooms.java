package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.plugin.studiinf.client.ui.NavButton;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class FilterRooms extends AbstractSearch {
	private List<ResourceDescription> ressources;
	private FlowPanel panel;
	NavButton roomsShowButton;
	
	public FilterRooms(FlowPanel panel,List<ResourceDescription> ressources,NavButton roomsShowButton,  boolean autoinit) {
		super("", (SearchPageInterface) Navigation.room, false);
		this.ressources = ressources;
		this.panel = panel;
		this.roomsShowButton = roomsShowButton;
		if(autoinit){
			init();
		}
	}

	@Override
	protected NoDuplicatesList<ResourceDescription> searchRessources(
			List<ResourceDescription> rooms) {
		NoDuplicatesList<ResourceDescription> found = new NoDuplicatesList<ResourceDescription>();
		for(ResourceDescription resource : this.ressources)
		{
			for(ResourceDescription room: rooms){
				if(room.getId().equals(resource.getId())){
					found.add(resource);
					NavButton lectureRoom = new NavButton(IconProvider.Rooms,resource.getName(), Navigation.roomDetail, resource.getId() );
					lectureRoom.setSize(0.87);
					lectureRoom.setWidth("100%");
					panel.add(lectureRoom);
					break;
				}
			}
		
		}
		if(found.size() == 1){
			roomsShowButton.setClickHandler(null);
			roomsShowButton.setTargetPage(Navigation.roomDetail);
			roomsShowButton.setTargetId(found.get(0).getId());
			roomsShowButton.setText(found.get(0).getName());
		}else if(found.size() <= 0){
			roomsShowButton.setVisible(false);
		}
		return found;
	}

}
