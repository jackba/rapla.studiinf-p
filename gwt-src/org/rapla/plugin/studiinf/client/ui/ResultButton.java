package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * Button for use in the ResultTable
 *
 */
public class ResultButton extends NavButton implements ResultObject {
	
	private NavButton bottomPictureButton;
	private List<Widget> cellList;
	private SearchPageInterface searchPage;
	private boolean showFooterButton;
	

	public ResultButton(String title, AbstractPage targetPage, String targetId, FontIcon icon, SearchPageInterface searchPage){
		this(title, targetPage, targetId, icon, searchPage, true);
	}
	
	public ResultButton(String title, AbstractPage targetPage, String targetId, FontIcon icon){
		this(title, targetPage, targetId, icon, true);
	}
	
	public ResultButton(String title, AbstractPage targetPage, String targetId, FontIcon icon, SearchPageInterface searchPage, boolean showFooterButton){
		super(0, icon, title, targetPage, targetId);
		this.showFooterButton = showFooterButton;
		this.searchPage = searchPage;
		setNumber(0);
		this.setWidth("100%");
	}
	public ResultButton(String title, AbstractPage targetPage, String targetId, FontIcon icon, boolean showFooterButton) {
		this(title, targetPage, targetId, icon, null, showFooterButton);
	}
	
	public ResultButton(FontIcon icon, String title, AbstractPage targetPaget, String targetId, boolean showFooterButton) {
		this(title, targetPaget, targetId, icon, null, showFooterButton);
	}
	
	/**
	 * force footerButton to be hidden
	 */
	public void hideFooterButton(){
		bottomPictureButton.getElement().getStyle().setDisplay(Display.NONE);
	}
	
	@Override
	public void setNumber(int number) {
		super.setNumber(number);
		getFooterButton().setNumber(number);
	}


	@Override
	public void setIcon(FontIcon fontIcon) {
		super.setIcon(fontIcon);
		bottomPictureButton.setIcon(fontIcon);
	}

	@Override
	public List<Widget> getCellObjects() {
		if(cellList == null){
			cellList = new LinkedList<Widget>();
			cellList.add(this);
		}
		return cellList;
	}

	@Override
	public NavButton getFooterButton() {
		if(bottomPictureButton == null){
			bottomPictureButton = new NavButton(0, getIcon(),getText(),targetPage,targetId);
		}
		return bottomPictureButton;
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
	protected void handlePressEvent() {
		super.handlePressEvent();
		if(searchPage != null){
			searchPage.handleClickCount(this.getTargetId());
		}
	}

	@Override
	public void setShowFooter(boolean show) {
		showFooterButton = show;		
	}

	@Override
	public boolean getShowFooter() {
		return showFooterButton;
	}
	
}
