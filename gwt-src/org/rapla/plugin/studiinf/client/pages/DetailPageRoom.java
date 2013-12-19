package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailPageRoom extends AbstractPage {

	private FlowPanel infoPanel = new FlowPanel();
	private FlowPanel roomPanel = new FlowPanel();
	private Label infoLabel = new Label("Information");
	private QRBox qrBox = new QRBox();
	private Grid infos = new Grid(4, 1);
	
	private String roomNumber = "D 459";
	private String roomType = "Hörsaal";

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		roomPanel.setStyleName("roomPanel");
		infoLabel.setStyleName("infoLabel");
		qrBox.setStyleName("infoQrBox");
		infos.setStyleName("infos");
		
		Image img1 = new Image("img/Kurse.svg");
		Image img2 = new Image("img/Kurse.svg");
		Image img3 = new Image("img/Kurse.svg");
		Image img4 = new Image("img/Kurse.svg");
		
		Widget nameBtn = new IconButton(roomNumber, img1);
		Widget typeBtn = new IconButton(roomType, img2);
		Widget studyBtn = new IconButton("Wirtschaftsinformatik", img3);
		Widget roomBtn = new IconButton("Raumbelegung", img4);
		
		infos.setWidget(0, 0, nameBtn);
		infos.setWidget(1, 0, typeBtn);
		infos.setWidget(2, 0, studyBtn);
		infos.setWidget(3, 0, roomBtn);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		Image img5 = new Image("img/Kurse.svg");
		
		Widget bottomRoomBtn = new IconButton("Raumbelegung anzeigen", img5);
		roomPanel.add(bottomRoomBtn);
		
		this.add(infoPanel);
		this.add(roomPanel);
		this.add(qrBox);
	}
	
	
	@Override
	public String getHistoryKey() {
		return "roomDetail";
	}

	@Override
	public String getTitle() {
		return "D 459";
	}

}
