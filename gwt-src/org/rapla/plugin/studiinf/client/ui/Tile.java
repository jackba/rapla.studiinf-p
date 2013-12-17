package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;


public class Tile extends NavigationButton {

	public Tile(String title, AbstractPage targetPage){
		super("<span>"+title+"</span>",targetPage);

		this.setStyleName("tile");

		
	}

}
