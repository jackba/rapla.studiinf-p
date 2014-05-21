package org.rapla.plugin.studiinf.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.studiinf.client.DisplayMode;

import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * ResultButton with additional Label
 *
 */
public class ResultButtonWithLabel implements ResultObjectWithLabel{
	
	private ResultButton resultObject;
	private Label label;
	
	public ResultButtonWithLabel(ResultButton resultObject, Label label) {
		this.resultObject = resultObject;
		this.label = label;	
		label.setStyleName("labelResultTable");
		
		if (DisplayMode.isMobile()){
			label.addStyleName("mobile");	
		}
		
	}
	
	@Override
	public void hideFooterButton(){
		resultObject.hideFooterButton();
	}
	
	@Override
	public void hideLabel(){
		label.getElement().getStyle().setDisplay(Display.NONE);
	}
	@Override
	public Element getElement(){
		return resultObject.getElement();
	}
	
	@Override
	public void setSize(double size){
		resultObject.setSize(size);
	}
	
	@Override
	public void setTargetId(String targetId){
		resultObject.setTargetId(targetId);
	}
	
	@Override
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

	@Override
	public void hideLabelAndFooterButton() {
		hideFooterButton();
		hideLabel();
	}

}
