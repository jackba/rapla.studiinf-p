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
	NavButton language;
	
	public PageFooter(AbstractPage parent) {
		this.parent = parent;
	}
	
	public void init(){
		this.setStyleName("footer");
	    NavButton homeBtn = new NavButton(IconProvider.Home,Studiinf.i18n.homeButtonText(),Navigation.homePage,null);	    
//	 languageButton = new NavButton(IconProvider.World ,Studiinf.i18n.thisLanguage(),null,null);
//	   final ListBox languageBox = new ListBox();
//	    languageBox.addItem("English");
//	    languageBox.addItem("Deutsch");
//	    languageBox.addItem("Espanol");
//	    languageBox.addItem("Francais");
//	    languageBox.setVisibleItemCount(1);
	    languages = new PopupPanel();
	    
//	    languages.add(languageButton);
	    languages.setVisible(false);
	    VerticalPanel vPanel = new VerticalPanel();
	    
	    for (String localeName : LocaleInfo.getAvailableLocaleNames())
	    {
	    			
	
	    	String displayName = LocaleInfo.getLocaleNativeDisplayName(localeName);
	    	final String url = localeName;
	    			
	    	if(!localeName.equals("default"))
	    	{
	    	NavButton languageButton = new NavButton(IconProvider.World ,displayName,null,null);
	    	vPanel.add(languageButton);
	    	
	    	languageButton.setClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					I18n i18n = GWT.create(I18n.class);
					UrlBuilder newUrl = Window.Location.createUrlBuilder();
					newUrl.setParameter("locale", url);
					Window.Location.assign(newUrl.buildString());
				}
			});
	    			
	    	}
	    }
	   
	    languages.add(vPanel);
	    
	    	    
	    language = new NavButton(IconProvider.World, "Languages", null, null);
	    language.setClickHandler(this);
	    
	    this.add(language);
	    
	    
	    
//	    languageBox.addChangeHandler(new ChangeHandler()
//	    {
//	    	public void onChange(ChangeEvent event)
//	    	{
//	    		I18n i18n = GWT.create(I18n.class);
//				UrlBuilder newUrl = Window.Location.createUrlBuilder();
//				int index = languageBox.getSelectedIndex();
//				String language = languageBox.getValue(index);
//				newUrl.setParameter("locale", language);
//				Window.Location.assign(newUrl.buildString());
//	    	}
//	    });
	    
//	    languageButton.setClickHandler(new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				I18n i18n = GWT.create(I18n.class);
//				UrlBuilder newUrl = Window.Location.createUrlBuilder();
//				newUrl.setParameter("locale", i18n.thisLanguageURL());
//				Window.Location.assign(newUrl.buildString());
//			}
//		});
//	    this.add(languageButton);
//	    this.add(languageBox);
	    if(this.parent != Navigation.homePage){
	    	this.add(homeBtn);
	    }
//	    this.add(languages);
	    
//	    languageButton.getElement().getStyle().setProperty("float", "left");
//	    homeBtn.getElement().getStyle().setProperty("float", "right");
//	    languageButton.addStyleName("left");
	    language.addStyleName("left");
	    homeBtn.addStyleName("right");
//	    languages.setStyleName("languageBox");
	}

	@Override
	public void onClick(ClickEvent event) {
		if(languages.isVisible() != true)
		{
			languages.show();
			languages.setVisible(true);
			languages.showRelativeTo(language);
		}
		else
		{
			languages.hide();
			languages.setVisible(false);
		
		}
		
		
	}	
}
