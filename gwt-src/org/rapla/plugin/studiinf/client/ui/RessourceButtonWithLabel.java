package org.rapla.plugin.studiinf.client.ui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RessourceButtonWithLabel implements ResultObjectWithLabel{
	
	private RessourceButton resultObject;
	private Label label;

	public RessourceButtonWithLabel(RessourceButton resultObject, Label label) {
		this.resultObject = resultObject;
		this.label = label;	
		label.setStyleName("labelResultTable");
	}
	
	public void hideLabel(){
		label.getElement().getStyle().setDisplay(Display.NONE);
	}

	public Element getElement(){
		return resultObject.getElement();
	}

	public void setSize(double size){
		resultObject.setSize(size);
	}
	
	public void setTargetId(String targetId){
		resultObject.setTargetId(targetId);
	}
	
	public void setText(String text){
		resultObject.setText(text);
	}
	
	@Override
	public List<Widget> getCellObjects() {
		List<Widget> help = resultObject.getCellObjects();
		List<Widget> returnList = new ArrayList<>(help);
		returnList.add(0, label);
		return returnList;
	}

	@Override
	public void setNumber(int number) {
		resultObject.setNumber(number);
		
	}

	@Override
	public NavButton getFooterButton() {
		// TODO Auto-generated method stub
		return resultObject.getFooterButton();
	}

	@Override
	public void setShowFooter(boolean show) {
		resultObject.setShowFooter(show);
		
	}

	@Override
	public boolean getShowFooter() {
		return resultObject.getShowFooter();
	}

}
