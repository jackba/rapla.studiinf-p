package org.rapla.plugin.studiinf.client.ui;


import org.rapla.plugin.studiinf.client.IconProvider;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class AccessibilityRow extends Composite {
	private static AccessibilityRowUiBinder uiBinder = GWT.create(AccessibilityRowUiBinder.class);

	interface AccessibilityRowUiBinder  extends UiBinder<Widget, AccessibilityRow> {
	}
	interface AccessibilityRowStyle extends CssResource{
		String accessibilityRow();	
		String cell();	
	}
	
	
	public AccessibilityRow(){
		initWidget(uiBinder.createAndBindUi(this));
		
	}
	
	private FontIcon accIcon;

	@UiField
	HTMLPanel accessibilityRow;
	
	@UiField
	AccessibilityRowStyle style;
	
	
	public void add(NavButton but){
		if(accIcon == null){
			accIcon = new FontIcon(IconProvider.Barrier_Free.getUrl());
			accIcon.getElement().getStyle().setFontSize(2.0, Unit.EM);
			accessibilityRow.add(accIcon);
		}
		
		 SimplePanel justified = new SimplePanel(but);
		 justified.setStyleName(style.cell());
		 accessibilityRow.add(justified);
	}
	
	public void clear(){
		accessibilityRow.clear();
		accIcon = null;
	}
}
