package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.ui.NavButton;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.PopupPanel;

public class DetailPagePersonClickHandler implements ClickHandler {

	NavButton roomsShowButton;
	PopupPanel rooms;
	
	public DetailPagePersonClickHandler(NavButton roomsShowButton,PopupPanel rooms) {
		this.rooms = rooms;
		this.roomsShowButton = roomsShowButton;
	}
	
	@Override
	public void onClick(ClickEvent event) {

		if(rooms.isVisible() != true)
		{
			rooms.show();
			rooms.setVisible(true);
			rooms.setWidth(roomsShowButton.getElement().getClientWidth()+"px");
			rooms.showRelativeTo(roomsShowButton);
		}
		else
		{
			rooms.hide();
			rooms.setVisible(false);		
		}
	}

}
