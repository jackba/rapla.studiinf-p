package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class QRBox extends FlowPanel {
	
	private Label qrLabel = new Label(Studiinf.i18n.qrInfoText());
	private Image qrLabelImg = new Image("img/QR.svg");
	private FlowPanel qrCodeBox = new FlowPanel();
	private Image qrCode = new Image("img/qr_sample.jpg");
	
	private final String hash;
	
	
	
	public QRBox( String hash) {
		super();
		
		this.hash = hash;
		
		this.setStyleName("qrBox");
		qrLabel.setStyleName("qrLabel");
		qrLabelImg.setStyleName("qrLabelImg");
		qrCode.setStyleName("qrCode");
		qrCodeBox.setStyleName("qrCode");
		qrCodeBox.getElement().setId(HTMLPanel.createUniqueId());
		
		
		this.add(qrCodeBox);
		this.add(qrLabelImg);
		this.add(qrLabel);
		
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
	qrCodeBox.getElement().setInnerHTML("");
		UrlBuilder newUrl = Window.Location.createUrlBuilder();
		newUrl.setHash(hash);
		Studiinf.newQRCode(qrCodeBox.getElement().getId(), newUrl.buildString());
	}
}
