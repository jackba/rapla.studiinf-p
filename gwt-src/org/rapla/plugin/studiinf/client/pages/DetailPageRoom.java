package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailPageRoom extends AbstractDetailPage {

	private FlowPanel infoPanel = new FlowPanel();
	private FlowPanel roomPanel = new FlowPanel();
	private Label infoLabel = new Label("Information");
	
	private Grid infos = new Grid(4, 1);
	
	private String roomNumber = "D 459";
	private String roomType = "Hörsaal";
	
	private IconButton nameBtn;
	
	

	@Override
	public void init(){
		super.init();
		
		
		infoPanel.setStyleName("infoPanel");
		roomPanel.setStyleName("roomPanel");
		infoLabel.setStyleName("infoLabel");
		infos.setStyleName("infos");
		
		Image roomNameImg = new Image(IconProvider.ROOMS);
		Image roomTypeImg = new Image(IconProvider.ROOM_TYPE);
		Image studyImg = new Image(IconProvider.COURSE);
		Image roomImg = new Image(IconProvider.CALENDAR);
		Image noNavigationImg = new Image(IconProvider.MISSING_MAP);
		
		
		noNavigationImg.setStyleName("navigationPicture");
		
		 nameBtn = new IconButton(roomNumber, roomNameImg);
		Widget typeBtn = new IconButton(roomType, roomTypeImg);
		Widget studyBtn = new IconButton("Wirtschaftsinformatik", studyImg);
		Widget roomBtn = new IconButton("Raumbelegung", roomImg);
		
		infos.setWidget(0, 0, nameBtn);
		infos.setWidget(1, 0, typeBtn);
		infos.setWidget(2, 0, studyBtn);
		infos.setWidget(3, 0, roomBtn);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
			
		Image occupancyImg = new Image(IconProvider.CALENDAR);
		
		Widget bottomRoomBtn = new IconButton("Raumbelegung anzeigen", occupancyImg);
		roomPanel.add(bottomRoomBtn);
		
		this.add(infoPanel);
		this.add(roomPanel);
		this.add(noNavigationImg);
		
	}
	
	
	@Override
	public String getHistoryKey() {
		return "roomDetail";
	}

	@Override
	public String getTitle() {
		if(roomNumber == null){
			roomNumber = "";
		}
		return roomNumber;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
		nameBtn.setText(roomNumber);
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		roomNumber = "D "+id;
		refresh();
		
	}

}
