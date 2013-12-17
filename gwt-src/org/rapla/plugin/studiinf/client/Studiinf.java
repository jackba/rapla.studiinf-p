package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.studiinf.client.i18n.I18n;

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
		
		
		
	
	}

	private void initToken() {
		// initToken
		String initToken = History.getToken();
	    if (initToken.length() == 0) {
	      History.newItem("");
	    }
	}

	
}
