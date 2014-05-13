package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.plugin.studiinf.client.ui.NavButton;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class FilterRooms extends AbstractSearch {
	private List<ResourceDescription> ressources;
	private FlowPanel panel;
	public FilterRooms(FlowPanel panel,List<ResourceDescription> ressources, boolean autoinit) {
		super("", (SearchPageInterface) Navigation.room, false);
		this.ressources = ressources;
		this.panel = panel;
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
		return found;
	}

}
