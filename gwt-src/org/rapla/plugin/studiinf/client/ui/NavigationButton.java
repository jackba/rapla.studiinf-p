package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

public class NavigationButton extends Button implements ClickHandler {
	private AbstractPage targetPage;
	private String targetId;
	
	public  NavigationButton(String title,AbstractPage targetPage){
		super(title);
		
		this.targetPage = targetPage;
		this.addClickHandler(this);
		
	}

	public  NavigationButton(String title,AbstractPage targetPage,String targetId){
		this(title,targetPage);
		this.targetId = targetId;
	}
	@Override
	public void onClick(ClickEvent event) {
		if(targetId == null){
			Navigation.goToPage(targetPage);
		}else{
			Navigation.goToPage(targetPage, targetId);
		}
		
	}
}
