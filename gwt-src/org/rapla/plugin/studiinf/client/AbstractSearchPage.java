package org.rapla.plugin.studiinf.client;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractSearchPage extends AbstractPage{

	private TextBox searchField = new TextBox();
	private Label resultLabel = new Label(i18n.frequentResultsLabel());
	private Grid results = new Grid(3, 2);
	private Widget organigramBtn = new Button(i18n.organigram());
	private AbsolutePanel keyboard = new AbsolutePanel();
	private HorizontalPanel resultBtns = new HorizontalPanel();
	private boolean searched = false;
	
	
public boolean isSearched() {
	return searched;
}


public void setSearched(boolean searched) {
	this.searched = searched;
	if (searched == true){
		resultLabel.setText(i18n.resultLabel());
	} else {
		resultLabel.setText(i18n.frequentResultsLabel());
	}
	
}


public AbstractSearchPage(){
	super();
	
	searchField.setStyleName("searchField");
	resultLabel.setStyleName("resultLabel");
	results.setStyleName("results");
	organigramBtn.setStyleName("organigramBtn");
	keyboard.setStyleName("keyboard");
	resultBtns.setStyleName("resultBtns");
	
	resultBtns.add(new Button("Text 1"));
	resultBtns.add(new Button("Text 2"));
	resultBtns.add(new Button("Text 3"));
	resultBtns.add(new Button("Text 4"));
	resultBtns.add(new Button("Text 5"));
	resultBtns.add(new Button("Text 6"));
	
	searchField.addKeyUpHandler(new KeyUpHandler() {
		
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (searchField.getText().equals("")){
				setSearched(false);
			} else {
				setSearched(true);
			}
		}
	});

	this.add(searchField);
	this.add(resultLabel);
	this.add(results);
	this.add(organigramBtn);
	this.add(keyboard);
	this.add(resultBtns);
}

}
