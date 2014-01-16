package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ResultButton extends NavigationButton implements ResultObject {
	
	private PictureButton topPictureButton;
	private NavButton bottomPictureButton;
	private List<Widget> cellList;
	private int number;
	private AbstractPage targetPage;
	private String targetId;
	private Image img;
	

	public int getNumber() {
		return number;
	}
	
	@Override
	public void setNumber(int number) {
		this.number = number;
		this.remove(topPictureButton);
		Image img1 = new Image(img.getUrl());
		Image img2 = new Image(img.getUrl());
		topPictureButton = new PictureButton(number, img1);
		bottomPictureButton = new NavButton(number, new FontIcon("icon-Studiengang2"),null,targetPage,targetId);
		this.add(topPictureButton);
	}

	public ResultButton(String title, AbstractPage targetPage, String targetId, Image img) {
		super("<span>"+title+"</span>", targetPage, targetId);
		this.number = 0;
		this.targetPage = targetPage;
		this.targetId = targetId;
		this.img = img;
		
		Image img1 = new Image(img.getUrl());
		Image img2 = new Image(img.getUrl());
		
		
		topPictureButton = new PictureButton(number, img1);
		bottomPictureButton = new NavButton(number, new FontIcon("icon-Studiengang2"),null,targetPage,targetId);
		
		
		
		this.addStyleName("resultButton");
		
		this.add(topPictureButton);
		
		
		
	}
	
	private void add(Widget w){
		this.getElement().appendChild(w.getElement());
	}
	private void remove(Widget w){
		this.getElement().removeChild(w.getElement());
	}
	

	@Override
	public List<Widget> getCellObjects() {
		if(cellList == null){
			cellList = new LinkedList<Widget>();
			cellList.add(this);
		}
		return cellList;
	}

	@Override
	public NavButton getFooterButton() {
		return bottomPictureButton;
	}

	
}
