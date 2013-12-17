package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ui.Tile;
import org.rapla.plugin.studiinf.client.ui.TileContainer;


public class HomePage extends AbstractPage {
	
	
	@Override
	public void init() {
		super.init();
		
		TileContainer tileContainer = new TileContainer();
		
		Tile courseBtn = new Tile(i18n.courses(),Navigation.course);
		Tile personBtn = new Tile(i18n.people(),Navigation.person);
		Tile roomBtn = new Tile(i18n.rooms(),Navigation.room);
		Tile poiBtn = new Tile(i18n.pointsOfInterest(),Navigation.poi);
		
		tileContainer.add(courseBtn);
		tileContainer.add(personBtn);
		tileContainer.add(roomBtn);
		tileContainer.add(poiBtn);
		
		this.add(tileContainer);
		
	};
	
	@Override
	public String getTitle() {
		return i18n.homeScreenTitle();
	}

}
