package org.rapla.plugin.studiinf.client.ui;


import org.rapla.plugin.studiinf.client.DisplayMode;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Team StudiInf
 *
 * Row of buttons for the page footer which duplicates the functions of all the buttons of the page on the lower part of the page.
 */
public class AccessibilityRow extends Composite {
	private static AccessibilityRowUiBinder uiBinder = GWT.create(AccessibilityRowUiBinder.class);
	
	private double size = 0.4;
	private int count = 0;
	
	interface AccessibilityRowUiBinder  extends UiBinder<Widget, AccessibilityRow> {
	}
	interface AccessibilityRowStyle extends CssResource{
		String accessibilityRow();	
		String cell();	
	}
	
	
	public AccessibilityRow(){
		initWidget(uiBinder.createAndBindUi(this));
		backButton.setIcon(FontIcon.Previous);
		backButton.setSize(size);
		backButton.setShowWhenDisabled(false);
		nextButton.setIcon(FontIcon.Next);
		nextButton.setSize(size);
		nextButton.setShowWhenDisabled(false);
		if(DisplayMode.isMobile()){
			this.setVisible(false);
		}
	}
	

	

	@UiField
	HTMLPanel accessibilityRow;
	
	@UiField
	AccessibilityRowStyle style;
	
	@UiField
	NavButton backButton;
	
	@UiField
	NavButton nextButton;
	
	@UiFactory
	NavButton createNavButton(){
		return new NavButton("", null, null);
	}
	
	/**
	 * Create a barrierfree icon for the footer
	 * @return created icon
	 */
	@UiFactory
	FontIcon createBarrierFreeIcon(){
		FontIcon bfIcon = new FontIcon(FontIcon.Barrier_Free.getUrl());
		bfIcon.getElement().getStyle().setFontSize(size*5, Unit.EM);
		return bfIcon;
	}
	
	
	/**
	 * Adds a NavButton to the row
	 * @param but NavButton that should be added
	 */
	public void add(NavButton but){
		but.setSize(size);
		count++;
		SimplePanel justified = new SimplePanel(but);
		justified.setStyleName(style.cell());
		accessibilityRow.add(justified);
	}
	
	public void clear(){
		accessibilityRow.clear();
		count = 0;
	}
	
	public NavButton getBackButton(){
		return backButton;
	}
	
	public NavButton getNextButton(){
		return nextButton;
	}
	
	public int getNextNumber(){
		return getNextNumber(0);
	}
	public int getNextNumber(int offset) {
		return offset+count;
	}
	

}
