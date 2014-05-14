package org.rapla.plugin.studiinf.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.Widget;

public class OrganigramButton extends NavButton implements ResultObject {
	
	private NavButton clone;
	private boolean showFooterButton;
	
	public OrganigramButton(String text, AbstractPage targetPage, String targetId, boolean showFooterButton) {
		super(IconProvider.Organigram, text, targetPage, targetId);
		clone = new NavButton(IconProvider.Organigram, null, targetPage, targetId);
//		this.getElement().getStyle().setWidth(100, Unit.PCT);
		// TODO Auto-generated constructor stub
	}

	public OrganigramButton(String text, AbstractPage targetPage, String targetId) {
		this(text, targetPage, targetId, true);
	}
	
	@Override
	public List<Widget> getCellObjects() {
		// TODO Auto-generated method stub
		ArrayList<Widget> returnList = new ArrayList<Widget>();
		returnList.add(this);
		return returnList;
	}

	@Override
	public NavButton getFooterButton() {
		// TODO Auto-generated method stub
		return clone;
	}

	
	@Override
	public void setNumber(int numberValue) {
		super.setNumber(numberValue);
		clone.setNumber(numberValue);
	}

	@Override
	public void setShowFooter(boolean show) {
		showFooterButton = show;
		
	}

	@Override
	public boolean getShowFooter() {
		// TODO Auto-generated method stub
		return true; //showFooterButton;
	}

}
