package org.rapla.plugin.studiinf.client.ui;

import com.gargoylesoftware.htmlunit.javascript.host.Console;
import com.google.gwt.aria.client.AlertdialogRole;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.TextAlign;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class PopUpImagePanel extends PopupPanel implements ClickHandler  {

	public PopUpImagePanel(String url) {
	      // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
	      // If this is set, the panel closes itself automatically when the user
	      // clicks outside of it.
	      super(true);
	      // PopupPanel is a SimplePanel, so you have to set it's widget property to
	      // whatever you want its contents to be.
	      
	      FlowPanel container = new FlowPanel();
	      
	      container.addDomHandler(this, ClickEvent.getType());
	      
	      FlowPanel lower = new FlowPanel(); 
	      Image img = new Image(url);
	      container.add(lower);
	      container.add(img);
	      this.setWidget(container);
	      
	      this.getElement().getStyle().setProperty("width", "100vw");
	      this.getElement().getStyle().setProperty("height", "100vh");
	      container.getElement().getStyle().setWidth(100, Unit.PCT);
	      container.getElement().getStyle().setHeight(100, Unit.PCT);
	      container.getElement().getStyle().setPosition(Position.ABSOLUTE);
	      container.getElement().getStyle().setTop(0, Unit.PCT);
	      container.getElement().getStyle().setLeft(0, Unit.PCT);
	      container.getElement().getStyle().setTextAlign(TextAlign.CENTER);
	      container.getElement().getStyle().setVerticalAlign(VerticalAlign.MIDDLE);
	      container.getElement().getStyle().setProperty("lineHeight", "100vh");
	      lower.getElement().getStyle().setWidth(100, Unit.PCT);
	      lower.getElement().getStyle().setHeight(100, Unit.PCT);
	      lower.getElement().getStyle().setBackgroundColor("#5c6971");
	      lower.getElement().getStyle().setOpacity(0.75);
	      lower.getElement().getStyle().setPosition(Position.ABSOLUTE);
	      lower.getElement().getStyle().setZIndex(10);
//	      img.getElement().getStyle().setPosition(Position.ABSOLUTE);
//	      img.getElement().getStyle().setTop(50, Unit.PCT);
//	      img.getElement().getStyle().setLeft(50, Unit.PCT);
//	      img.getElement().getStyle().setWidth(915, Unit.PX);
//	      img.getElement().getStyle().setHeight(642, Unit.PX);
	      img.getElement().getStyle().setProperty("maxWidth", "100%");
	      img.getElement().getStyle().setProperty("maxHeight", "100%");
	      img.getElement().getStyle().setPosition(Position.RELATIVE);
	      img.getElement().getStyle().setZIndex(15);
//	      img.getElement().getStyle().setMarginTop(-321, Unit.PX);
//	      img.getElement().getStyle().setMarginLeft(-457, Unit.PX);
	      
	      img.addClickHandler(this);
	     
	      
	    }

	@Override
	public void onClick(ClickEvent event) {
		this.hide();
	}
	
}
