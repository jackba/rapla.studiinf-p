package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Studiinf;


public class RoomSearch extends AbstractSearchPage {

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Studiinf.i18n.roomSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "room";
	}

}
