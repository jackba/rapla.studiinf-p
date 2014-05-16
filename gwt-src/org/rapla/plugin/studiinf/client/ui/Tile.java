package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

/**
 * 
 *
 */
public class Tile extends NavButton {

	public Tile(String title, AbstractPage targetPage, FontIcon icon){
		//TODO: layouting der Bilder
		super(icon, title,targetPage,null);
		this.addStyleName(this.style.tile());
		
	}

}
