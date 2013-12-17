package org.rapla.plugin.studiinf.client;


public class Tile extends NavigationButton {

	public Tile(String title, AbstractPage targetPage){
		
		super("<span>"+title+"</span>",targetPage);

		this.setStyleName("tile");

		
	}

}
