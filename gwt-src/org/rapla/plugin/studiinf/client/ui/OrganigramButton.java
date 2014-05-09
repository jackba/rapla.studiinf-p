package org.rapla.plugin.studiinf.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.Widget;

public class OrganigramButton extends NavButton implements ResultObject {
	
	private NavButton clone;

	public OrganigramButton(String text, AbstractPage targetPage, String targetId) {
		super(IconProvider.Organigram, text, targetPage, targetId);
		clone = new NavButton(IconProvider.Organigram, null, targetPage, targetId);
//		this.getElement().getStyle().setWidth(100, Unit.PCT);
		// TODO Auto-generated constructor stub
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

}
