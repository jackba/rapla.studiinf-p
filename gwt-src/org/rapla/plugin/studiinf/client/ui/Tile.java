package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Unit;


public class Tile extends NavButton {

	public Tile(String title, AbstractPage targetPage){
		super(title,targetPage,null);
		Style css = this.getElement().getStyle();
		
		css.setHeight(10.0, Unit.EM);
		css.setWidth(50.0, Unit.PCT);
		
	}

}
