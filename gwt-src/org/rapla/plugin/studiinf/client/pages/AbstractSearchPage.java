package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.Keyboard;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractSearchPage extends AbstractPage{

	private TextBox searchField = new TextBox();
	private Label resultLabel = new Label(Studiinf.i18n.frequentResultsLabel());
	private Grid results = new Grid(3, 2);
	private Image img = new Image("img/Kurse.svg");
	private Widget organigramBtn = new IconButton(Studiinf.i18n.organigram(), img);
	private FlowPanel keyboard = new Keyboard(searchField);
	protected HorizontalPanel resultBtns = new HorizontalPanel();
	private QRBox qrBox = new QRBox(getHistoryKey());
	private FlowPanel resultPanel = new FlowPanel();
	private FlowPanel searchPanel = new FlowPanel();
	
	private boolean searched = false;
	
	
public boolean isSearched() {
	return searched;
}


public void setSearched(boolean searched) {
	this.searched = searched;
	if (searched == true){
		resultLabel.setText(Studiinf.i18n.resultLabel());
	} else {
		resultLabel.setText(Studiinf.i18n.frequentResultsLabel());
	}
	
}



@Override
public void init() {
	super.init();
	
	searchField.setStyleName("searchField");
	resultLabel.setStyleName("resultLabel");
	results.setStyleName("results");
	organigramBtn.setStyleName("organigramBtn");
	keyboard.setStyleName("keyboard");
	resultBtns.setStyleName("resultBtns");
	resultPanel.setStyleName("resultPanel");
	searchPanel.setStyleName("searchPanel");
	

	
	
	searchField.addKeyUpHandler(new KeyUpHandler() {
		
		@Override
		public void onKeyUp(KeyUpEvent event) {
			searchField.setCursorPos(searchField.getText().length());
			searchField.setFocus(true);
			if (searchField.getText().equals("")){
				setSearched(false);
			} else {
				setSearched(true);
			}
		}
	});
	
	resultPanel.add(resultLabel);
	resultPanel.add(results);
	searchPanel.add(keyboard);
	searchPanel.add(searchField);
	
	this.add(searchPanel);
	this.add(resultPanel);
	if(this.hasOrganigramm()){
		this.add(organigramBtn);
	}
	this.add(resultBtns);
	this.add(qrBox);
	}
	
	public void addResult(ResultButton res){
		for(int i= 0 ; i < results.getRowCount(); i++){
			for(int j=0; j < results.getCellCount(i);j++){
				if(results.getWidget(i, j) == null){
					results.setWidget(i,j, res);
					resultBtns.add(res.getBottomPictureButton());
					return;
				}
			}
		}
		
		
	}
	
	abstract public boolean hasOrganigramm();
}
