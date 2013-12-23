package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class DetailPagePerson extends AbstractPage {

	private QRBox qrBox = new QRBox();
	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Grid infos = new Grid(4, 1);
	private FlowPanel picture = new FlowPanel();
	
	
	@Override
	public void init(){
		super.init();
		
		qrBox.setStyleName("personQrBox");
		personInfoPanel.setStyleName("personInfoPanel");
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		picture.setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(picture);
		personInfoPanel.add(infos);
		
		Image img1 = new Image("img/Kurse.svg");
		Image img2 = new Image("img/Raum.svg");
		Image img3 = new Image("img/Kurse.svg");
		Image img4 = new Image("img/Kurse.svg");
		
		Widget roomNrBtn = new IconButton("Raum 1234", img1);
		Widget mailBtn = new IconButton("test@mail.de", img2);
		Widget telephoneBtn = new IconButton("0122- 5675765", img3);
		Widget extraInfosBtn = new IconButton("Zusätzliche Infos", img4);
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		
		this.add(qrBox);
		this.add(personInfoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
	}
	
	@Override
	public String getHistoryKey() {
		// TODO Auto-generated method stub
		return "personDetail";
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Name of Person";
	}

}
