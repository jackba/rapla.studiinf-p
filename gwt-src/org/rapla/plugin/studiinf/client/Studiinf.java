package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.studiinf.client.i18n.I18n;
import org.rapla.plugin.studiinf.client.ui.ModalSpinner;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.History;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Studiinf implements EntryPoint {
	String id;
	CategoryDescription studiengaenge;
	
	public static final I18n i18n = GWT.create(I18n.class);
	public static final String urlToRapla = "../";
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {	
		Navigation.init();
		History.addValueChangeHandler(new HistoyChangeHandler());
		initToken();
		History.fireCurrentHistoryState();
		ModalSpinner.hide();
	}

	/**
	 * initializes the navigation-token
	 */
	private void initToken() {
		String initToken = History.getToken();
	    if (initToken.length() == 0) {
	      History.newItem("");
	    }
	}
	
	
	/**
	 * Generates a new QR-code on the given Element for the URL 
	 * @param element Element to place the QR-code
	 * @param url URL to be encoded to the QR-code
	 */
	public static native void newQRCode(Element element,String url) /*-{
	if(element){
		var qr = new $wnd.QRCode(element, {
			text: url,
			correctLevel : $wnd.QRCode.CorrectLevel.L
		});
	}else{
		$wnd.alert(JSON.stringify(element));
	}

}-*/;

	
}
