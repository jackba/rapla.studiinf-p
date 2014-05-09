package org.rapla.plugin.studiinf.client.ui;

import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.search.RessourceSearch;

import com.google.gwt.user.client.ui.Widget;

public class RessourceButton extends NavButton implements ResultObject {
	
	private AbstractSearchPage page;
	private boolean hideText = false;
	
	private NavButton footerButton;
	
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
	
	public void updateResults(ResourceDescription resourceDescriptor) {
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
		getFooterButton().setTargetId(targetId);
	}

	@Override
	public void setTargetPage(AbstractPage targetPage) {
		super.setTargetPage(targetPage);
		getFooterButton().setTargetPage(targetPage);
	}
	
	@Override
	public List<Widget> getCellObjects() {
		ArrayList<Widget> returnList = new ArrayList<Widget>();
		returnList.add(this);
		return returnList;
	}

	@Override
	public NavButton getFooterButton() {
		if(footerButton == null){
			footerButton = new NavButton(getIcon(), "", getTargetPage(), getTargetId());
		}
		return footerButton;
	}
	
	@Override
	public void setNumber(int numberValue) {
		super.setNumber(numberValue);
		getFooterButton().setNumber(numberValue);
	}
	@Override
	public void setIcon(FontIcon fontIcon) {
		super.setIcon(fontIcon);
		getFooterButton().setIcon(fontIcon);
	}
}
