package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.search.RessourceSearch;

public class RessourceButton extends NavButton {
	
	private AbstractSearchPage page;
	private boolean hideText;
	
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
		super(icon, title, targetPage, null);
		this.page = page;
	}
	
	public RessourceButton(String title, FontIcon icon, AbstractPage targetPage, AbstractSearchPage page,boolean hideText) {
		this(title, icon, targetPage, page);
		setHideText(hideText);
	}
	
	public void updateResults(ResourceDescriptor resourceDescriptor) {
		setTargetId(resourceDescriptor.getId());	
	}
	
	@Override
	public void setText(String text) {
		String oldText = getText();
		if (oldText != null && !oldText.equals(text)){
			if(hideText){
				super.setText(null);
			}else{
				super.setText(text);
			}			
			setTargetId(null);
			new RessourceSearch(text, page, this);
		}
	}
	

}
