package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class QRBox extends FlowPanel {
	
	private Label qrLabel = new Label(Studiinf.i18n.qrInfoText());
	private Image qrLabelImg = new Image("img/QR.svg");
	private Image qrCode = new Image("img/qr_sample.jpg");
	
	
	
	public QRBox() {
		super();
		
		this.setStyleName("qrBox");
		qrLabel.setStyleName("qrLabel");
		qrLabelImg.setStyleName("qrLabelImg");
		qrCode.setStyleName("qrCode");
		
		this.add(qrCode);
		this.add(qrLabelImg);
		this.add(qrLabel);
		
	}
}
