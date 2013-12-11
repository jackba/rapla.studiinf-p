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

public class PageFooter extends HorizontalPanel {
	private final AbstractPage parent;
	
	public PageFooter(AbstractPage parent) {
		this.parent = parent;
		
		    this.setStyleName("footer");
		    
		    Button homeBtn = new Button("Home");
		    homeBtn.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Navigation.goToPage(Navigation.homePage);
					
				}
			});
		    
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
		    this.add(homeBtn);
		    
	}
	
}
