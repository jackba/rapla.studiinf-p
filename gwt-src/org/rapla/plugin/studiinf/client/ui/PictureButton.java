package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class PictureButton extends Button {
	public PictureButton(int number, Image img) {
		super();
		
		Label numberField = new Label(number+".");
		
		this.setStyleName("pictureButton");
		
		numberField.addStyleName("number");
		img.addStyleName("img");
		
		this.add(numberField);
		this.add(img);
		
		
		
	}
	

	public void add(Widget w){
		this.getElement().appendChild(w.getElement());
	}
}
