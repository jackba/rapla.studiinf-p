package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * White Box, which contains a QR Code, an image of a smartphone and a explanation.
 *
 */
public class QRBox extends Composite {

	private static QRBoxUiBinder uiBinder = GWT.create(QRBoxUiBinder.class);
	private String hash;

	interface QRBoxUiBinder extends UiBinder<Widget, QRBox> {
	}

	public QRBox() {
		initWidget(uiBinder.createAndBindUi(this));
		qrLabel.setInnerSafeHtml(SafeHtmlUtils.fromString(Studiinf.i18n.qrInfoText()));
		qrCode.setId(HTMLPanel.createUniqueId());
		scanMeIcon.setSrc(IconProvider.QR_BOX);
		
		/*fix for quick mobile*/
		bindQRBox.addStyleName("BindQRBox");
		qrLabel.addClassName("qrLabel");
		qrCode.addClassName("qrCode");
		scanMeIcon.addClassName("scanMeIcon");
		
	}
	
	public QRBox(String hash) {
		this();
		setHash(hash);
	}
	

	@UiField
	ParagraphElement qrLabel;
	
	@UiField
	ImageElement scanMeIcon;
	
	@UiField
	DivElement qrCode;
	
	@UiField
	HTMLPanel bindQRBox;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
		qrCode.setInnerHTML("");
		
		UrlBuilder newUrl = Window.Location.createUrlBuilder();
		newUrl.setPath("studiinf-mobile.html");
		newUrl.setHash(hash);
				
		Studiinf.newQRCode(qrCode, newUrl.buildString());
	}

}
