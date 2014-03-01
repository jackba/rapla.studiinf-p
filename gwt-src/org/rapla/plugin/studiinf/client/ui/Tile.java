package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

/**
 * 
 *
 */
public class Tile extends NavButton {

	public Tile(String title, AbstractPage targetPage){
		super(title,targetPage,null);
		this.addStyleName(this.style.tile());
		
	}

}
