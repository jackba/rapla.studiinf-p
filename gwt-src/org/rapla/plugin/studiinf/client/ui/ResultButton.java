package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ResultButton extends Button {
	
	private PictureButton topPictureButton;
	private PictureButton bottomPictureButton;
	

	public ResultButton(int number, String title, Image img) {
		super("<span>"+title+"</span>");
		
		Image img1 = new Image(img.getUrl());
		Image img2 = new Image(img.getUrl());
		
		/*img1.getElement().getStyle().setProperty("width", "3vh");
		img1.getElement().getStyle().setProperty("height", "3vh");
		
		img2.getElement().getStyle().setProperty("width", "3vh");
		img2.getElement().getStyle().setProperty("height", "3vh");*/
		
		topPictureButton = new PictureButton(number, img1);
		bottomPictureButton = new PictureButton(number, img2);
		
		this.addStyleName("resultButton");
		
		this.add(topPictureButton);
		
		
		
	}
	
	public void add(Widget w){
		this.getElement().appendChild(w.getElement());
	}
	
	public PictureButton getBottomPictureButton(){
		return bottomPictureButton;
	}
}
