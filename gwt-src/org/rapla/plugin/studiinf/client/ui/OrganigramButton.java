package org.rapla.plugin.studiinf.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Team StudiInf
 * 
 * Button with text and navigation function to use in organigram
 */
public class OrganigramButton extends NavButton implements ResultObject {
	
	private NavButton clone;
	
	public OrganigramButton(String text, AbstractPage targetPage, String targetId, boolean showFooterButton) {
		super(FontIcon.Organigram, text, targetPage, targetId);
		clone = new NavButton(FontIcon.Organigram, null, targetPage, targetId);
	}

	public OrganigramButton(String text, AbstractPage targetPage, String targetId) {
		this(text, targetPage, targetId, true);
	}
	
	@Override
	public List<Widget> getCellObjects() {
		ArrayList<Widget> returnList = new ArrayList<Widget>();
		returnList.add(this);
		return returnList;
	}

	@Override
	public NavButton getFooterButton() {
		return clone;
	}

	
	@Override
	public void setNumber(int numberValue) {
		super.setNumber(numberValue);
		clone.setNumber(numberValue);
	}

	@Override
	public void setShowFooter(boolean show) {
		//ignore FooterButton is always visible
	}

	@Override
	public boolean getShowFooter() {
		return true; //always show footerButton
	}
	@Override
	public void hideFooterButton() {
		this.clone.getElement().getStyle().setDisplay(Display.NONE);		
	}

}
