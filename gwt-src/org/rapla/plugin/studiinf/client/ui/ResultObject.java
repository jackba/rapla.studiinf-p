package org.rapla.plugin.studiinf.client.ui;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

public interface ResultObject {

	List<Widget> getCellObjects();
	public void setNumber(int number);
	public NavButton getFooterButton();
	public void setShowFooter(boolean show);
	public boolean getShowFooter();
	Element getElement();
	void setSize(double size);
	void setTargetId(String targetId);
	void setText(String text);
	void hideFooterButton();
	
}
