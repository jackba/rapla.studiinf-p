package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ResultButton extends NavigationButton {
	
	private PictureButton topPictureButton;
	private PictureButton bottomPictureButton;
	

	public ResultButton(int number, String title, AbstractPage targetPage, String targetId, Image img) {
		super("<span>"+title+"</span>", targetPage, targetId);
		
		Image img1 = new Image(img.getUrl());
		Image img2 = new Image(img.getUrl());
		
		
		topPictureButton = new PictureButton(number, img1);
		bottomPictureButton = new NavigationPictureButton(number, img2,targetPage,targetId);
		
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
