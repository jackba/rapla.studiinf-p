package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.search.RessourceSearch;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Image;

public class RessourceButton extends NavigationIconButton {
	
	private AbstractSearchPage page;
	
	public RessourceButton(String title, Image icon, AbstractPage targetPage, AbstractSearchPage page) {
		super(title, icon, targetPage,null);
		this.page = page;
	}
	
	@Override
	public void onClick(ClickEvent event) {
		if(targetId != null){
			super.onClick(event);
		}
	}
	
	public void updateResults(ResourceDescriptor resourceDescriptor) {
		this.targetId = resourceDescriptor.getId();	
	}
	
	@Override
	public void setText(String text) {
		if(!getText().equals(text)){
			super.setText(text);
			this.targetId = null;
			new RessourceSearch(text, page, this);
		}
	}
	

}
