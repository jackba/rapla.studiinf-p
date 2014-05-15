package org.rapla.plugin.studiinf.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public interface ResultObjectWithLabel extends ResultObject {

	List<Widget> getCellObjects();
	public void setNumber(int number);
	public NavButton getFooterButton();
	public void setShowFooter(boolean show);
	public boolean getShowFooter();
	public void hideLabel();
}
