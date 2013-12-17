package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.freiraum.common.CategoryDescription;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Studiinf implements EntryPoint {
	String id;
	CategoryDescription studiengaenge;
	//private I18n i18n = GWT.create(I18n.class);

	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {	
		Navigation.init();
		Navigation.goToPage(Navigation.homePage);	
	
	}

	
}
