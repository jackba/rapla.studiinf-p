package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

/**
 * A Tile is a big button on the homepage of the StudiInf application
 *
 */
public class Tile extends NavButton {

	public Tile(String title, AbstractPage targetPage, FontIcon icon){
		//TODO: layouting der Bilder
		super(icon, title,targetPage,null);
		this.addStyleName(this.style.tile());

		if (DisplayMode.isMobile()){
			this.addStyleName(this.style.mobile());
		}
		
	}

}
