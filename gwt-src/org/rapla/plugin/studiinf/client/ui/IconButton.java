package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class IconButton extends Button {
	
	protected Image icon;
	
	public IconButton(String title, Image img) {
		super(title);
		
		icon = new Image(img.getUrl());
		
		icon.setStyleName("iconIconButton");
		this.setStyleName("iconButton");
	
		
		this.getElement().getStyle().setProperty("paddingLeft", "6vh");
	
		this.add(icon);
		
		
		
	}
	
	public void add(Widget w){
		this.getElement().appendChild(w.getElement());
	}
	@Override 
	public void setText(String text) {
		super.setText(text);
		this.add(icon);
	}
}
