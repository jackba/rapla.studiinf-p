package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailPageRoom extends AbstractPage {

	private FlowPanel infoPanel = new FlowPanel();
	private FlowPanel roomPanel = new FlowPanel();
	private Label infoLabel = new Label("Information");
	private QRBox qrBox = new QRBox();
	private Grid infos = new Grid(4, 1);

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		roomPanel.setStyleName("roomPanel");
		infoLabel.setStyleName("infolabel");
		qrBox.setStyleName("infoQrBox");
		infos.setStyleName("infos");
		
		Widget nameBtn = new IconButton("D 459");
		Widget typeBtn = new IconButton("Hörsaal");
		Widget studyBtn = new IconButton("Wirtschaftsinformatik");
		Widget roomBtn = new IconButton("Raumbelegung");
		
		infos.setWidget(0, 0, nameBtn);
		infos.setWidget(1, 0, typeBtn);
		infos.setWidget(2, 0, studyBtn);
		infos.setWidget(3, 0, roomBtn);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		Widget bottomRoomBtn = new IconButton("Raumbelegung anzeigen");
		roomPanel.add(bottomRoomBtn);
		
		
		this.add(infoPanel);
		this.add(roomPanel);
		this.add(qrBox);
	}
	
	
	@Override
	public String getHistoryKey() {
		return "detailRoom";
	}

	@Override
	public String getTitle() {
		return "Audimax";
	}

}
