package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.studiinf.client.i18n.I18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.server.GwtLocaleFactoryImpl;
import com.google.gwt.i18n.shared.GwtLocale;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

public class PageFooter extends VerticalPanel {
	private final AbstractPage parent;
	
	public PageFooter(AbstractPage parent) {
		this.parent = parent;
		
		
		/* DOM.setStyleAttribute(getElement(), "position", "absolute");
		    DOM.setStyleAttribute(getElement(), "overflow", "hidden");
		    DOM.setStyleAttribute(getElement(), "bottom", "0px");
		    DOM.setStyleAttribute(getElement(), "left", "0px");
		    DOM.setStyleAttribute(getElement(), "right", "0px");
		    DOM.setStyleAttribute(getElement(), "height", "6.25vh");
		    DOM.setStyleAttribute(getElement(), "width", "100vw");
		    DOM.setStyleAttribute(getElement(), "background", "#5c6971");
		    DOM.setStyleAttribute(getElement(), "color", "#ffffff");*/
		    this.setStyleName("footer");
		    Button languageButton = new Button("<i class='fa fa-globe'></i> "+parent.i18n.otherLanguage());
		    languageButton.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					I18n i18n = GWT.create(I18n.class);
					UrlBuilder newUrl = Window.Location.createUrlBuilder();
					newUrl.setParameter("locale", i18n.otherLanguageURL());
					Window.Location.assign(newUrl.buildString());
					
				}
			});
		    this.add(languageButton);
		    
	}
	
}
