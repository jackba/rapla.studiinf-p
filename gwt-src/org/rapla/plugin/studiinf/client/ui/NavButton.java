package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class NavButton extends Composite implements NavigationButtonSpec {

	private static NavButtonUiBinder uiBinder = GWT
			.create(NavButtonUiBinder.class);

	interface NavButtonUiBinder extends UiBinder<Widget, NavButton> {
	}
	AbstractPage targetPage;
	String targetId;
	int numberValue = 0;
	FontIcon iconValue;

	public NavButton(AbstractPage targetPage,String targetId) {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	Button button;
	
	@UiField
	SpanElement number;
	
	@UiField
	SpanElement icon;
	
	@UiHandler("button")
	void onClick( ClickEvent e){
		if(targetId == null){
			Navigation.goToPage(targetPage);
		}else{
			Navigation.goToPage(targetPage, targetId);
		}
	}
	
	@UiHandler("button")
	void onTouchStart( TouchStartEvent e){
		if(targetId == null){
			Navigation.goToPage(targetPage);
		}else{
			Navigation.goToPage(targetPage, targetId);
		}
	}

	@Override
	public String getTargetId() {
		return targetId;
	}

	@Override
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	@Override
	public int getNumber() {
		return numberValue;
	}

	@Override
	public void setNumber(int number) {
		this.numberValue = number;
	}

	@Override
	public FontIcon getIcon() {
		return iconValue;
	}

	@Override
	public void setIcon(FontIcon icon) {
		this.iconValue = icon;
	}
	


}
