package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.studiinf.client.i18n.I18n;
import org.rapla.plugin.studiinf.client.ui.ModalSpinner;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Studiinf implements EntryPoint {
	String id;
	CategoryDescription studiengaenge;
	
	public static final I18n i18n = GWT.create(I18n.class);
	
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

	private void initToken() {
		// initToken
		String initToken = History.getToken();
	    if (initToken.length() == 0) {
	      History.newItem("");
	    }
	}
	
	public static native void newQRCode(String elementId,String text) /*-{
		var elm =  $doc.getElementById(elementId);
		if(elm){
			var qr = new $wnd.QRCode(elm, {
    			text: text,
    		});
		}else{
			$wnd.alert(JSON.stringify(elm));
		}
	
	}-*/;

	
}
