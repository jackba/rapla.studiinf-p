package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class DetailPagePerson extends AbstractPage {

	private QRBox qrBox = new QRBox();
	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	
	
	@Override
	public void init(){
		super.init();
		
		qrBox.setStyleName("personQrBox");
		personInfoPanel.setStyleName("personInfoPanel");
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		
		personInfoPanel.add(personInfoLabel);
		
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
