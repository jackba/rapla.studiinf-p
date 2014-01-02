package org.rapla.plugin.studiinf.client.pages;

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
	
	private final String nameImgString = new String("img/Raum.svg");
	private final String typeImgString = new String("img/Raum.svg");
	private final String studyImgString = new String("img/Organigramm.svg");
	private final String roomImgString = new String("img/Räume.svg");
	private final String noNavigationImgString = new String("img/KeineKarte.svg");
	

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		roomPanel.setStyleName("roomPanel");
		infoLabel.setStyleName("infoLabel");
		infos.setStyleName("infos");
		
		Image roomNameImg = new Image(nameImgString);
		Image roomTypeImg = new Image(typeImgString);
		Image studyImg = new Image(studyImgString);
		Image roomImg = new Image(roomImgString);
		Image noNavigationImg = new Image(noNavigationImgString);
		
		
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
		
		final String occcupancyImgString = new String("img/Kurse.svg");		
		Image occupancyImg = new Image(occcupancyImgString);
		
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
	protected void handleId(String id) {
		roomNumber = "D "+id;
		refresh();
		
	}
	
	@Override
	protected void refresh() {
		super.refresh();
		nameBtn.setText(roomNumber);
	}

}
