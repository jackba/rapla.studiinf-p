package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.IconButton;
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
	private FlowPanel keyboard = new FlowPanel();
	private HorizontalPanel resultBtns = new HorizontalPanel();
	private QRBox qrBox = new QRBox();
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
	

	ResultButton res = new ResultButton(1, "Test", new Image("img/Kurse.svg"));
	results.setWidget(0,0, res);
	ResultButton res2 = new ResultButton(2, "Test", new Image("img/Kurse.svg"));
	results.setWidget(0,1, res2);
	ResultButton res3 = new ResultButton(3, "Prof. Dr. R. Küstermann", new Image("img/Kurse.svg"));
	results.setWidget(1,0, res3);
	ResultButton res4 = new ResultButton(4, "Test", new Image("img/Kurse.svg"));
	results.setWidget(1,1, res4);
	ResultButton res5 = new ResultButton(5, "Test", new Image("img/Kurse.svg"));
	results.setWidget(2,0, res5);
	ResultButton res6 = new ResultButton(6, "Test", new Image("img/Kurse.svg"));
	results.setWidget(2,1, res6);
	
	resultBtns.add(res.getBottomPictureButton());
	resultBtns.add(res2.getBottomPictureButton());
	resultBtns.add(res3.getBottomPictureButton());
	resultBtns.add(res4.getBottomPictureButton());
	resultBtns.add(res5.getBottomPictureButton());
	resultBtns.add(res6.getBottomPictureButton());
	
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
	
	resultPanel.add(resultLabel);
	resultPanel.add(results);
	searchPanel.add(keyboard);
	searchPanel.add(searchField);
	
	this.add(searchPanel);
	this.add(resultPanel);
	this.add(organigramBtn);
	this.add(resultBtns);
	this.add(qrBox);
}

}
