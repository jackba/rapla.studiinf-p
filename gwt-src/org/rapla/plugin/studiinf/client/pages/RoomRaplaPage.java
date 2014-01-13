package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

public class RoomRaplaPage extends RaplaPage {

	public RoomRaplaPage() {
		super(Navigation.roomDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "roomRapla";
	}

}
