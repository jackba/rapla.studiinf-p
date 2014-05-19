package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.LocalStorage;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.Keyboard;
import org.rapla.plugin.studiinf.client.ui.OrganigramButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Template for the Search Pages. All Search Pages have to implement this class.
 * Template includes all the necessary widgets on the search pages.
 *
 */
public abstract class AbstractSearchPage extends AbstractPage implements SearchPageInterface{
	
	private TextBox searchField = new TextBox();
	private Label resultLabel = new Label(Studiinf.i18n.frequentResultsLabel());
	private ResultTable results ;
	private OrganigramButton organigramBtn;
	private FlowPanel keyboard = new Keyboard(searchField,this);
	protected AccessibilityRow resultBtns = new AccessibilityRow();
	protected QRBox qrBox = new QRBox(getHistoryKey());
	protected FlowPanel resultPanel = new FlowPanel();
	private FlowPanel searchPanel = new FlowPanel();
	public LocalStorage ls;
	private KeyUpHandler inputChanger;
	private boolean searched = false;
	private final boolean hasOrganigramm;
	private final boolean showInput;
	private final boolean showQRBox;
	//private final boolean hasNavigationButtons;
	private final int resultRows;
	private final int resultColumns;
	private final static int defaultRowsMobil = 4;
	private final static int defaultColumnsMobil = 1;
	private final static int defaultRowsStele = 6;
	private final static int defaultColumnsStele = 2;
	
	
	/**
	 * With the constructor you can customize your Search Pages.
	 * 
	 * @param hasOrganigramm Boolean, which determines if the organigram Button should be shown.
	 * @param showInput Boolean, 
	 * @param showQRBox Boolean, which determines if the default QR Code should be displayed.
	 * @param resultRows Number of rows in the result Table.
	 * @param resultColumns Number of columns of the result Table.
	 * @param hasNavigationButtons
	 * @param icon Icon of the entries in the result Table.
	 * @param targetPage The Target Page of the Search Page (Corresponding Detail Page).
	 */
	public AbstractSearchPage(boolean hasOrganigramm, boolean showInput, boolean showQRBox, int resultRows, int resultColumns, boolean hasNavigationButtons, FontIcon icon, AbstractPage targetPage) {
		if(DisplayMode.isMobile() == true && resultColumns == defaultColumnsStele && resultRows == defaultRowsStele){
			this.resultColumns = defaultColumnsMobil;
			this.resultRows = defaultRowsMobil;
		}else {
			this.resultRows = resultRows;
			this.resultColumns = resultColumns;			
		}
		this.hasOrganigramm = hasOrganigramm;
		this.showInput = showInput;
		this.showQRBox = showQRBox;
		this.results = new ResultTable(this.resultBtns,this.resultColumns,this.resultRows);
		//this.hasNavigationButtons = hasNavigationButtons;
		this.ls = new LocalStorage(getHistoryKey(), results, icon, targetPage, this);
	}


	/**
	 * If the size of the result Table is not set, the default is 6 rows and two columns.
	 */
	public AbstractSearchPage(boolean hasOrganigramm, boolean showInput,boolean showQRBox, FontIcon icon, AbstractPage targetPage) {
		this(hasOrganigramm,showInput,showQRBox,defaultRowsStele, defaultColumnsStele,true, icon, targetPage);
	}
	
	public boolean isSearched() {
		return searched;
	}
	
	/**
	 * Sets the Label of the result Table. Before searching the table shows the most frequent results.
	 * @param searched Boolean, if the user has types sth. into the search.
	 */
	public void setSearched(boolean searched) {
		this.searched = searched;
		if (searched == true){
			resultLabel.setText(Studiinf.i18n.resultLabel());
		} else {
			resultLabel.setText(Studiinf.i18n.frequentResultsLabel());
		}
		
	}
	
	/**
	 * Initialises all the elements of the page and displays them.
	 */
	@Override
	public void init() {
		super.init();
		organigramBtn = new OrganigramButton(Studiinf.i18n.organigram(), getOrganisationType(),"null");
		searchField.setStyleName("searchField");
		resultLabel.setStyleName("infoLabel");
		results.setStyleName("results");
		organigramBtn.addStyleName("organigramBtn");
		keyboard.setStyleName("keyboard");
		resultPanel.setStyleName("resultPanel");
		searchPanel.setStyleName("searchPanel");
		if(DisplayMode.isMobile()){
			searchPanel.addStyleName("mobile");
			organigramBtn.addStyleName("mobile");
			resultLabel.addStyleName("mobile");
		}else {
		}
		
	//	results.getBackButton().addStyleName("backButton");
	//	results.getNextButton().addStyleName("nextButton");
		qrBox.getElement().getStyle().setProperty("top", "43vh");
		inputChanger = new KeyUpHandler() {
			
			/**
			 * Handler, which checks if the user has typed sth. into the search field.
			 */
			@Override
			public void onKeyUp(KeyUpEvent event) {
				handleKeyEvent();
			}
		};
		searchField.addKeyUpHandler(inputChanger);
		resultPanel.add(resultLabel);
		resultPanel.add(results);
		

		
		if(showInput){
			searchPanel.add(keyboard);
			searchPanel.add(searchField);
			this.add(searchPanel);
		}
		
		this.add(resultPanel);
		if(this.hasOrganigramm){
			this.add(organigramBtn);
		}
		
	//	if (this.hasNavigationButtons){
	//		this.add(results.getBackButton());
	//		this.add(results.getNextButton());
	//	}
		this.add(resultBtns);
		if(this.showQRBox){
			this.add(qrBox);
		}
	}
	
	/**
	 * Checks if the user has typed sth. into the search field.
	 */
	private void handleKeyEvent(){
		searchField.setCursorPos(searchField.getText().length());
		searchField.setFocus(true);
		if (searchField.getText().equals("")){
			setSearched(false);
			handleMostFrequent();
		} else {
			setSearched(true);
			handleSearch(searchField.getText());
		}
	}

	/**
	 * Adds entries to the result Table.
	 * @param res ResultButton, which should be added to the result table.
	 */
	public void addResult(ResultButton res){
		results.addResult( res);	
	}
	
	@Override
	public void onShow() {
		super.onShow();
		searchField.setText("");
		searchField.setFocus(true);
		searchField.setCursorPos(searchField.getText().length());
		handleKeyEvent();
	}

	
	/**
	 * Refreshs the page and updates the entries.
	 */
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
	
	public void handleClickCount(String targetId){
		ls.writeStorage(targetId);
	}
	
	abstract protected void handleSearch(String searchTerm);
	/* (non-Javadoc)
	 * @see org.rapla.plugin.studiinf.client.pages.SearchPageInterface#updateResults(java.util.List)
	 */
	@Override
	abstract public void updateResults(List<ResourceDescription> ressourcesMatched);
	/* (non-Javadoc)
	 * @see org.rapla.plugin.studiinf.client.pages.SearchPageInterface#getResourceType()
	 */
	@Override
	abstract public String getResourceType();
	
	/**
	 * Updated the calculation of the most frequent results, after every search.
	 */
	protected void handleMostFrequent(){
		ls.fillMap();
		
	}	
	
	abstract AbstractPage getOrganisationType();
	

}

