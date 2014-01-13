package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.Keyboard;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractSearchPage extends AbstractPage{
	
	private final String organigramImg = new String(IconProvider.ORGANIGRAMM);

	private TextBox searchField = new TextBox();
	private Label resultLabel = new Label(Studiinf.i18n.frequentResultsLabel());
	private ResultTable results ;
	private Image img = new Image(organigramImg);
	private Widget organigramBtn;
	private FlowPanel keyboard = new Keyboard(searchField,this);
	protected FlowPanel resultBtns = new FlowPanel();
	private QRBox qrBox = new QRBox(getHistoryKey());
	private FlowPanel resultPanel = new FlowPanel();
	private FlowPanel searchPanel = new FlowPanel();
	
	
	private KeyUpHandler inputChanger;
	
	
	private boolean searched = false;
	
	private final boolean hasOrganigramm;
	private final boolean showInput;
	private final boolean showQRBox;
	
	private final int resultRows;
	private final int resultColumns;
	
	
	public AbstractSearchPage(boolean hasOrganigramm, boolean showInput, boolean showQRBox, int resultRows, int resultColumns) {
		this.hasOrganigramm = hasOrganigramm;
		this.showInput = showInput;
		this.showQRBox = showQRBox;
		this.resultRows = resultRows;
		this.resultColumns = resultColumns;
		this.results = new ResultTable(this.resultBtns,this.resultColumns,this.resultRows);
	}
	public AbstractSearchPage(boolean hasOrganigramm, boolean showInput,boolean showQRBox) {
		this(hasOrganigramm,showInput,showQRBox,3,2);
	}
	
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
	organigramBtn = new NavigationIconButton(Studiinf.i18n.organigram(), img, Navigation.organisationChart,"1");
	
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
	
	for(Button b : results.getButtonsBottom()){
		this.add(b);
	}
	
	if(showInput){
		searchPanel.add(keyboard);
		searchPanel.add(searchField);
		this.add(searchPanel);
	}
	
	this.add(resultPanel);
	if(this.hasOrganigramm){
		this.add(organigramBtn);
	}
	this.add(resultBtns);
	
	if(this.showQRBox){
		this.add(qrBox);
	}
	}
	
	public void addResult(ResultButton res){
		results.addResult( res);
		
		
	}
	
	

	
	
	@Override
	protected void refresh() {
		super.refresh();

		resultBtns.clear();
		results.refresh();
	}
	
	
	public void clearResult()
	{
		results.clearResults();
	}
	
	public void fakeKeyUp(){
		inputChanger.onKeyUp(null);
	}
	
	abstract protected void handleSearch(String searchTerm);
	
		
	


	abstract public void updateResults(List<ResourceDescriptor> ressourcesMatched);
	abstract public String getResourceType();
	
}
