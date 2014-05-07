package org.rapla.plugin.studiinf.client.ui;


import org.rapla.plugin.studiinf.client.IconProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AccessibilityRow extends Composite {
	private static AccessibilityRowUiBinder uiBinder = GWT.create(AccessibilityRowUiBinder.class);
	
	private double size = 0.6;
	
	interface AccessibilityRowUiBinder  extends UiBinder<Widget, AccessibilityRow> {
	}
	interface AccessibilityRowStyle extends CssResource{
		String accessibilityRow();	
		String cell();	
	}
	
	
	public AccessibilityRow(){
		initWidget(uiBinder.createAndBindUi(this));
		backButton.setIcon(IconProvider.Previous);
		backButton.setSize(size);
		nextButton.setIcon(IconProvider.Next);
		nextButton.setSize(size);
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
	
	@UiFactory
	FontIcon createBarrierFreeIcon(){
		FontIcon bfIcon = new FontIcon(IconProvider.Barrier_Free.getUrl());
		bfIcon.getElement().getStyle().setFontSize(3, Unit.EM);
		return bfIcon;
	}
	
	
	
	public void add(NavButton but){
		but.setSize(size);
		SimplePanel justified = new SimplePanel(but);
		justified.setStyleName(style.cell());
		accessibilityRow.add(justified);
	}
	
	public void clear(){
		accessibilityRow.clear();
	}
	
	public NavButton getBackButton(){
		return backButton;
	}
	
	public NavButton getNextButton(){
		return nextButton;
	}
	

}
