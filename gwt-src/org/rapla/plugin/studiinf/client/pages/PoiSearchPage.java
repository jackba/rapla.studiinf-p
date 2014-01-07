package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PoiSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;


public class PoiSearchPage extends AbstractSearchPage {
	
	private int currentPageNr = 1;
	private int firstResultNumber;
	private String currentPoiText = "Casino";
	private String navImg = new String("img/KeineKarte.svg");
	private String currentPageLabelText = Studiinf.i18n.page() + currentPageNr;
	private FlowPanel resultsPanel = new FlowPanel();
	private Grid results = new Grid(2, 2);
	private Label resultsLabel = new Label("POIs");
	private Label currentPage = new Label(currentPageLabelText);
	private Label currentPoi = new Label(currentPoiText);
	private FlowPanel bottomResultPanel = new FlowPanel();
	private Image rightNavImg;
	private Image leftNavImg;
	private Image bottomRightNavImg;
	private Image bottomLeftNavImg;
	private ResultButton firstResult;
	private ResultButton secondResult;
	private ResultButton thirdResult;
	private ResultButton fourthResult;
	private Image navigationImg;
	
	@Override
	public void init(){
		super.init();
		
		currentPageNr = 1;
		firstResultNumber = 1;
		
		resultsPanel.setStyleName("poiResultsPanel");
		resultsLabel.setStyleName("poiResultsLabel");
		results.setStyleName("poiResults");
		currentPage.setStyleName("poiPage");
		currentPoi.setStyleName("currentPoiLabel");
		bottomResultPanel.setStyleName("poiBottomResultPanel");
		
		addResultButtons();
		
		navImg = new String("img/KeineKarte.svg");
		navigationImg = new Image(navImg);
		navigationImg.setStyleName("poiNavigationImg");
		
		addNavigationButtonsAndClickhandler();

//		resultsPanel.add(resultsLabel);
//		resultsPanel.add(results);
//		this.add(resultsPanel);
//		this.add(currentPage);
//		this.add(currentPoi);
//		this.add(navigationImg);
//		this.add(leftNavImg);
//		this.add(rightNavImg);
//		this.add(bottomLeftNavImg);
//		this.add(bottomRightNavImg);
//		this.add(bottomResultPanel);
		
//		showLeftNavigation(false);
//		showRightNavigation(true);
		
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Studiinf.i18n.poiSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "poi";
	}


		
	public void refresh(){
		currentPoi.setText(currentPoiText);	
		this.remove(navigationImg);
		navigationImg.setUrl(navImg);
		this.add(navigationImg);
	}
	
	public void addNavigationButtonsAndClickhandler(){
		final String rightNavigation = new String("img/PoI.svg");
		final String leftNavigation = new String("img/PoI.svg");
		
		rightNavImg = new Image(rightNavigation);
		leftNavImg = new Image(leftNavigation);
		
		bottomRightNavImg = new Image(rightNavigation);
		bottomLeftNavImg = new Image(leftNavigation);
		
		rightNavImg.setStyleName("poiRightNav");
		leftNavImg.setStyleName("poiLeftNav");
		bottomRightNavImg.setStyleName("poiBottomRightNav");
		bottomLeftNavImg.setStyleName("poiBottomLeftNav");
		
		rightNavImg.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showNextPois();
				
			}
		});
		
		leftNavImg.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showPreviousPois();
				
			}
		});
		
		bottomRightNavImg.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showNextPois();
				
			}
		});
		
		bottomLeftNavImg.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				showPreviousPois();
				
			}
		});
	}
	
	public void showRightNavigation(boolean show){
		if (show == true){
			rightNavImg.getElement().getStyle().setDisplay(Display.INLINE);
			bottomRightNavImg.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			rightNavImg.getElement().getStyle().setDisplay(Display.NONE);
			bottomRightNavImg.getElement().getStyle().setDisplay(Display.NONE);
		}

	}
	
	public void showLeftNavigation(boolean show){
		if (show == true){
			leftNavImg.getElement().getStyle().setDisplay(Display.INLINE);
			bottomLeftNavImg.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			leftNavImg.getElement().getStyle().setDisplay(Display.NONE);
			bottomLeftNavImg.getElement().getStyle().setDisplay(Display.NONE);
		}

	}
	
	public void showNextPois(){
		currentPageNr += 1;
		firstResultNumber += 4;
		currentPage.setText(Studiinf.i18n.page()+ currentPageNr);
		refreshResultButtons();
		if (currentPageNr == 3){
			showRightNavigation(false);
		}
		showLeftNavigation(true);
	}
	
	public void showPreviousPois(){
		currentPageNr -= 1;
		firstResultNumber -= 4;
		currentPage.setText("Seite "+ currentPageNr);
		refreshResultButtons();
		if (currentPageNr == 1){
			showLeftNavigation(false);
		}
		showRightNavigation(true);
	}
	
	public void refreshResultButtons(){

		results.remove(firstResult);
		results.remove(secondResult);
		results.remove(thirdResult);
		results.remove(fourthResult);

		bottomResultPanel.remove(firstResult.getBottomPictureButton());
		bottomResultPanel.remove(secondResult.getBottomPictureButton());
		bottomResultPanel.remove(thirdResult.getBottomPictureButton());
		bottomResultPanel.remove(fourthResult.getBottomPictureButton()); 
		
		addResultButtons();
	}
	
	public void addResultButtons(){
		final String poiSvg = new String("img/PoI.svg");
		Image img = new Image(poiSvg); 
		
		firstResult = new ResultButton(firstResultNumber, "Eins", this, "0001", img);
		secondResult = new ResultButton(firstResultNumber + 1, "Eins", this, "0002", img);
		thirdResult = new ResultButton(firstResultNumber + 2, "Eins", this, "0003", img);
		fourthResult = new ResultButton(firstResultNumber + 3, "Eins", this, "0004", img);
		
		results.setWidget(0, 0, firstResult);
		results.setWidget(0, 1, secondResult);
		results.setWidget(1, 0, thirdResult);
		results.setWidget(1, 1, fourthResult);
		
		bottomResultPanel.add(firstResult.getBottomPictureButton());
		bottomResultPanel.add(secondResult.getBottomPictureButton());
		bottomResultPanel.add(thirdResult.getBottomPictureButton());
		bottomResultPanel.add(fourthResult.getBottomPictureButton());
	}

	@Override
	protected void handleSearch(String searchTerm) {
		new PoiSearch(searchTerm,this);
		
	}

	@Override
	public boolean hasOrganigramm() {
		return false;
	}

	@Override
	public void updateResults(List<ResourceDescriptor> ressourcesMatched) {
		clearResult();
		int counter = 1;
		for(ResourceDescriptor room : ressourcesMatched)
		{
			addResult(new ResultButton(counter, room.getName(), Navigation.roomDetail, room.getId(), new Image("img/PoI.svg")));
			counter++;
		}
		
	}

	
	
}
