package org.rapla.plugin.studiinf.client;


public class HomePage extends AbstractPage {
	
	public HomePage() {
		super();
		
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

	}
	
	@Override
	public String getTitle() {
		return i18n.homeScreenTitle();
	}

}
