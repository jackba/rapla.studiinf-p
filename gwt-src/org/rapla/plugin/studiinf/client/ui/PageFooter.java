package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.i18n.I18n;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class PageFooter extends HorizontalPanel {
	
	public PageFooter(AbstractPage parent) {
		
		    this.setStyleName("footer");
		    
		    Button homeBtn = new NavigationButton("<i class='fa fa-home'></i> "+Studiinf.i18n.homeButtonText(),Navigation.homePage);
		
		    
		    Button languageButton = new Button("<i class='fa fa-globe'></i> "+Studiinf.i18n.otherLanguage());
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
		    
		    //only add homeButton if current page is not the homepage // equals Strings should be improved 
		    if(!parent.getTitle().equals(Studiinf.i18n.homeScreenTitle()) ){
		    	this.add(homeBtn);
		    }
		    
	}


	
	
	
	
	
}
