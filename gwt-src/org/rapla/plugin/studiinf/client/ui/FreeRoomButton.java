package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.Widget;

public class FreeRoomButton extends NavButton implements ResultObject {
	
	private final NavButton freeUntil;
	private List<Widget> cellList;
	private NavButton footerButton; 
	
	public FreeRoomButton(Event e) {
		super(0,IconProvider.Rooms,e.getResources().get(0).getName().toString(),Navigation.roomDetail,e.getResources().get(0).getId());
		this.setWidth("100%");
		this.setSize(0.8);
		setTargetPage(Navigation.roomDetail);
		targetId = e.getResources().get(0).getId();
		freeUntil =  new NavButton(IconProvider.Calendar,"frei bis " + e.getEnd(), null, null);
		freeUntil.setSize(0.8);
		freeUntil.setWidth("100%");
	}

	@Override
	public List<Widget> getCellObjects() {
		if(cellList == null){
			cellList = new LinkedList<Widget>();
			cellList.add(this);
			cellList.add(freeUntil);
		}
		return cellList;
	}

	@Override
	public NavButton getFooterButton() {
		if(footerButton == null){
			footerButton = new NavButton(new FontIcon("icon-Raeume"),null, targetPage, targetId);
		}
		return footerButton;
	}
	
	@Override
	public void setNumber(int numberValue) {
		getFooterButton().setNumber(numberValue);
		super.setNumber(numberValue);
	}
	
	@Override
	public void setTargetPage( AbstractPage targetPage){
		getFooterButton().setTargetPage(targetPage);
		super.setTargetPage(targetPage);
	}
	
	@Override
	public void setTargetId(String targetId) {
		getFooterButton().setTargetId(targetId);
		super.setTargetId(targetId);
	}
	
	@Override
	public void setIcon(FontIcon fontIcon) {
		getFooterButton().setIcon(fontIcon);
		super.setIcon(fontIcon);
	}
}
