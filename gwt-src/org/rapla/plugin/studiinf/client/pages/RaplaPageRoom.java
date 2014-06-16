package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;
/**
 * 
 * @author Team StudiInf
 * Page that shows rapla timetable for a room
 *
 */
public class RaplaPageRoom extends RaplaPage {

	public RaplaPageRoom() {
		super(Navigation.roomDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "roomRapla";
	}

}
