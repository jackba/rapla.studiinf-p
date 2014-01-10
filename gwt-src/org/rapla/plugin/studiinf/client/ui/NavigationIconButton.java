package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;

public class NavigationIconButton extends IconButton implements ClickHandler {
	
	protected AbstractPage targetPage;
	protected String targetId;
	
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public NavigationIconButton(String title, Image icon, AbstractPage targetPage) {
		super(title, icon);

		this.targetPage = targetPage;
		this.addClickHandler(this);
	}
	public NavigationIconButton(String title, Image icon, AbstractPage targetPage,String targetId) {
		this(title,icon,targetPage);
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
