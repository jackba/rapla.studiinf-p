package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Widget;

/**
 * Button, which displays free rooms. 
 * Consists out of two horizontal Buttons: One displays the room and the other how long it is free.
 *
 */
public class FreeRoomButton extends NavButton implements ResultObject {
	
	private final NavButton freeUntil;
	private List<Widget> cellList;
	private NavButton footerButton; 
	private boolean showFooterButton = true;
	
	public FreeRoomButton(Event e) {
		super(0,FontIcon.Rooms,e.getResources().get(0).getName().toString(),Navigation.roomDetail,e.getResources().get(0).getId());
		this.setWidth("100%");
		this.setSize(0.8);
		setTargetPage(Navigation.roomDetail);
		targetId = e.getResources().get(0).getId();
		if(DisplayMode.isMobile()){
			freeUntil =  new NavButton(FontIcon.Calendar, Studiinf.i18n.freeUntilMobile(e.getEnd()) , null, null);			
		}else{
			freeUntil =  new NavButton(FontIcon.Calendar, Studiinf.i18n.freeUntil(e.getEnd()) , null, null);
		}
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
			footerButton = new NavButton(FontIcon.Rooms,null, targetPage, targetId);
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

	@Override
	public void setShowFooter(boolean show) {
		showFooterButton = show;
		
	}

	@Override
	public boolean getShowFooter() {
		return showFooterButton;
	}

	@Override
	public void hideFooterButton() {
		this.footerButton.getElement().getStyle().setDisplay(Display.NONE);		
	}
}
