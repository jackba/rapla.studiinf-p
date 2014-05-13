package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Footer with a home and a language button.
 *
 */
public class PageFooter extends FlowPanel implements ClickHandler {
	
	double buttonSize = 0.7;
	
	AbstractPage parent;
	PopupPanel languages;
	NavButton languageButton;
	NavButton languageChangeButton;
	NavButton backBtn;
	AbstractPage backTarget;
	String backId;
	
	public PageFooter(AbstractPage parent) {
		this.parent = parent;
	}
	
	public void init(){
		this.setStyleName("footer");
	    NavButton homeBtn = new NavButton(IconProvider.Home,Studiinf.i18n.homeButtonText(),Navigation.homePage,null);	
	    
	    homeBtn.getElement().getStyle().setPosition(Position.ABSOLUTE);
	    homeBtn.getElement().getStyle().setRight(5, Unit.PCT);
	    homeBtn.getElement().getStyle().setTop(1.25, Unit.EM);
	    homeBtn.setSize(buttonSize);
	    
	    
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
	    	languageButton.setWidth("100%");
	    	languageButton.setSize(buttonSize);
	    	languageButton.setClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					UrlBuilder newUrl = Window.Location.createUrlBuilder();
					newUrl.setParameter("locale", languageUrl);
					Window.Location.assign(newUrl.buildString());
					languages.hide();
					languages.setVisible(false);
				}
			});
	    			
	    	}
	    }
	   
	    languages.add(languagesPanel);
	    
	    	    
	    languageChangeButton = new NavButton(IconProvider.World, Studiinf.i18n.languageButtonText(), null, null);/*todo always load english file*/
	    languageChangeButton.setClickHandler(this);
	    languageChangeButton.getElement().getStyle().setPosition(Position.ABSOLUTE);
	    languageChangeButton.getElement().getStyle().setLeft(5, Unit.PCT);
	    languageChangeButton.getElement().getStyle().setTop(1.25, Unit.EM);
	    languageChangeButton.setSize(buttonSize);
	    
	    this.add(languageChangeButton);
	    	    
	    
	    backBtn = new NavButton(IconProvider.Previous,Studiinf.i18n.back(), null, null);
	    
	    backBtn.setSize(buttonSize);
	    backBtn.getElement().getStyle().setPosition(Position.ABSOLUTE);
	    backBtn.getElement().getStyle().setLeft(50, Unit.PCT);
	    backBtn.getElement().getStyle().setMarginLeft(-10, Unit.PCT);
	    backBtn.getElement().getStyle().setWidth(20, Unit.PCT);
	    backBtn.getElement().getStyle().setTop(1.25, Unit.EM);
	    
	    this.add(backBtn);
	    if(this.parent != Navigation.homePage){
	    	this.add(homeBtn);
	    }
	    updateBackButton();
	}
	
	public void setTargetPage(AbstractPage page){
		backBtn.setTargetPage(page);
		updateBackButton();
	}
	public void setTargetId(String id){
		backBtn.setTargetId(id);
		updateBackButton();
	}
	
	private void updateBackButton(){
		backBtn.setVisible(backBtn.getEnabled());
	}
	
	public void setBackClickHandler(ClickHandler clickHandler){
		backBtn.setClickHandler(clickHandler);
		updateBackButton();
	}
	
	

	@Override
	public void onClick(ClickEvent event) {
		if(languages.isVisible() != true)
		{
			languages.show();
			languages.setVisible(true);
			languages.setWidth(languageChangeButton.getElement().getClientWidth()+"px");
			languages.showRelativeTo(languageChangeButton);
		}
		else
		{
			languages.hide();
			languages.setVisible(false);		
		}		
	}	
}
