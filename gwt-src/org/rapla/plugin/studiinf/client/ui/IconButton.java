package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class IconButton extends Button {
	public IconButton(String title) {
		super(title);
		
		Image icon = new Image("img/Kurse.svg");
		
		
		//this.setElement(DOM.createElement("button"));
		icon.getElement().getStyle().setPosition(Position.ABSOLUTE);
		icon.getElement().getStyle().setProperty("top", "0px");
		icon.getElement().getStyle().setProperty("left", "0px");
		icon.getElement().getStyle().setProperty("width", "3vh");
		icon.getElement().getStyle().setProperty("height", "3vh");
		icon.getElement().getStyle().setProperty("background", "#e2001a");
		icon.getElement().getStyle().setProperty("border", "none");
		icon.getElement().getStyle().setProperty("padding", "1vh");
		this.getElement().getStyle().setProperty("paddingLeft", "6vh");
		this.getElement().getStyle().setProperty("background", "#aeb4b8");
		
		this.add(icon);
		
		
		
	}
	
	public void add(Widget w){
		this.getElement().appendChild(w.getElement());
	}
}
