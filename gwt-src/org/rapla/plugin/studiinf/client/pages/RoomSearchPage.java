package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class RoomSearchPage extends AbstractSearchPage {

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Studiinf.i18n.roomSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "room";
	}
	
	@Override
	public void init() {
		super.init();
		addResult( new ResultButton(1, "D 001", Navigation.roomDetail,"001", new Image("img/Kurse.svg")));
		addResult(new ResultButton(2, "D 002",Navigation.roomDetail,"002", new Image("img/Kurse.svg")));
		addResult(new ResultButton(3, "D 003",Navigation.roomDetail,"003", new Image("img/Kurse.svg")));
		addResult(new ResultButton(4, "D 004",Navigation.roomDetail,"004", new Image("img/Kurse.svg")));
		addResult(new ResultButton(5, "D 005",Navigation.roomDetail,"005", new Image("img/Kurse.svg")));
		addResult(new ResultButton(6, "D 006",Navigation.roomDetail,"006", new Image("img/Kurse.svg")));
	}

	@Override
	public boolean hasOrganigramm() {
		return false;
	}

	@Override
	protected void handleSearch(String searchTerm) {
		// TODO Auto-generated method stub
		
	}

}
