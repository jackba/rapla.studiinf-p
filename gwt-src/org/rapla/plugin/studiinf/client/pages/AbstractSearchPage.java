package org.rapla.plugin.studiinf.client.pages;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
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
	
	private final String organigramImg = new String(IconProvider.ORGANIGRAMM);

	private TextBox searchField = new TextBox();
	private Label resultLabel = new Label(Studiinf.i18n.frequentResultsLabel());
	private Grid results = new Grid(3, 2);
	private Image img = new Image(organigramImg);
	private Widget organigramBtn = new IconButton(Studiinf.i18n.organigram(), img);
	private FlowPanel keyboard = new Keyboard(searchField,this);
	protected HorizontalPanel resultBtns = new HorizontalPanel();
	private QRBox qrBox = new QRBox(getHistoryKey());
	private FlowPanel resultPanel = new FlowPanel();
	private FlowPanel searchPanel = new FlowPanel();
	
	private List<ResultButton> resultList = new LinkedList<ResultButton>();
	
	private KeyUpHandler inputChanger;
	
	
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
	

	inputChanger = new KeyUpHandler() {
		
		@Override
		public void onKeyUp(KeyUpEvent event) {
			searchField.setCursorPos(searchField.getText().length());
			searchField.setFocus(true);
			if (searchField.getText().equals("")){
				setSearched(false);
			} else {
				setSearched(true);
				handleSearch(searchField.getText());
			}
		}
	};
	
	searchField.addKeyUpHandler(inputChanger);
	
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
		resultList.add(res);
		refresh();
		
		
	}
	
	

	
	
	@Override
	protected void refresh() {
		super.refresh();
		
		for(int i= 0 ; i < results.getRowCount(); i++){
			for(int j=0; j < results.getCellCount(i);j++){
				if(results.getWidget(i, j) != null){
					results.remove(results.getWidget(i, j));
				}
			}
		}
		resultBtns.clear();
		Iterator<ResultButton> resultButtons = resultList.iterator();
		for(int i= 0 ; i < results.getRowCount() && resultButtons.hasNext(); i++){
			for(int j=0; j < results.getCellCount(i) && resultButtons.hasNext();j++){
				if(results.getWidget(i, j) != null){
					results.remove(results.getWidget(i, j));
				}
					ResultButton res = resultButtons.next();
					results.setWidget(i,j, res);
					resultBtns.add(res.getBottomPictureButton());
			}
		}
	}
	
	
	public void clearResult()
	{
		resultList.clear();
		refresh();
	}
	
	public void fakeKeyUp(){
		inputChanger.onKeyUp(null);
	}
	
	abstract protected void handleSearch(String searchTerm);
	
		
	
	abstract public boolean hasOrganigramm();


	abstract public void updateResults(List<ResourceDescriptor> ressourcesMatched);
	
}
