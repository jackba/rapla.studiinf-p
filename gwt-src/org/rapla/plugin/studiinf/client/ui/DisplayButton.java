package org.rapla.plugin.studiinf.client.ui;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

public class DisplayButton implements ResultObject{

	private NavButton bottomButton;
	


	@Override
	public List<Widget> getCellObjects() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNumber(int number) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NavButton getFooterButton() {
		// TODO Auto-generated method stub
		return bottomButton;
	}

	@Override
	public void setShowFooter(boolean show) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getShowFooter() {
		// TODO Auto-generated method stub
		return false;
	}

}
