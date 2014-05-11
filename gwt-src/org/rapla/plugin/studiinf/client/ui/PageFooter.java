package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.i18n.I18n;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.thirdparty.guava.common.collect.Table;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Footer with a home and a language button.
 *
 */
public class PageFooter extends FlowPanel implements ClickHandler {
	
	AbstractPage parent;
	PopupPanel languages;
	NavButton languageButton;
	NavButton languageChangeButton;
	
	public PageFooter(AbstractPage parent) {
		this.parent = parent;
	}
	
	public void init(){
		this.setStyleName("footer");
	    NavButton homeBtn = new NavButton(IconProvider.Home,Studiinf.i18n.homeButtonText(),Navigation.homePage,null);	   
	    languages = new PopupPanel();
	    languages.setVisible(false);
	    VerticalPanel languagesPanel = new VerticalPanel();
	    
	    for (String localeName : LocaleInfo.getAvailableLocaleNames())
	    {	
	    	String displayName = LocaleInfo.getLocaleNativeDisplayName(localeName);
	    	final String languageUrl = localeName;
	    			
	    	if(!localeName.equals("default"))
	    	{
	    	NavButton languageButton = new NavButton(IconProvider.World ,displayName,null,null);
	    	languagesPanel.add(languageButton);
	    	
	    	languageButton.setClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					UrlBuilder newUrl = Window.Location.createUrlBuilder();
					newUrl.setParameter("locale", languageUrl);
					Window.Location.assign(newUrl.buildString());
				}
			});
	    			
	    	}
	    }
	   
	    languages.add(languagesPanel);
	    
	    	    
	    languageChangeButton = new NavButton(IconProvider.World, "Languages", null, null);
	    languageChangeButton.setClickHandler(this);
	    
	    this.add(languageChangeButton);
	    	    
	    if(this.parent != Navigation.homePage){
	    	this.add(homeBtn);
	    }
	}

	@Override
	public void onClick(ClickEvent event) {
		if(languages.isVisible() != true)
		{
			languages.show();
			languages.setVisible(true);
			languages.showRelativeTo(languageChangeButton);
		}
		else
		{
			languages.hide();
			languages.setVisible(false);		
		}		
	}	
}
