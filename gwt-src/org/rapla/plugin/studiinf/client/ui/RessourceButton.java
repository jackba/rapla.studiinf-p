package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.search.RessourceSearch;

import com.google.gwt.user.client.Window;

public class RessourceButton extends NavButton {
	
	private AbstractSearchPage page;
	private boolean hideText = false;
	
	public boolean isHideText() {
		return hideText;
	}

	public void setHideText(boolean hideText) {
		this.hideText = hideText;
		if(hideText){
			super.setText(null);
		}
	}

	public RessourceButton(String title, FontIcon icon, AbstractPage targetPage, AbstractSearchPage page) {
		this(title, icon, targetPage, page,false);
	}
	
	public RessourceButton(String title, FontIcon icon, AbstractPage targetPage, AbstractSearchPage page,boolean hideText) {
		super(icon, title, targetPage, null);
		this.page = page;
		setHideText(hideText);
	}
	
	public void updateResults(ResourceDescriptor resourceDescriptor) {
//		Window.alert("update: "+resourceDescriptor.toString());
		setTargetId(resourceDescriptor.getId());	
	}
	
	@Override
	public void setText(String text) {
		String oldText = getText();
		this.getElement().setAttribute("data-ressource", text);
		if ((oldText == null && text != null) ||(oldText != null && !oldText.equals(text))){
			if(hideText){
				super.setText(null);
			}else{
				super.setText(text);
			}			
			setTargetId(null);
			new RessourceSearch(text, page, this);
		}
	}
	
	@Override
	public void setTargetId(String targetId) {
		super.setTargetId(targetId);
	}
	

}
