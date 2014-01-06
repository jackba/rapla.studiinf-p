package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;


public class PoiSearchPage extends AbstractDetailPage {
	
	private int currentPageNr = 1;
	private String currentPageLabelText = "Seite "+ currentPageNr;
	private FlowPanel resultsPanel = new FlowPanel();
	private Grid results = new Grid(2, 2);
	private Label resultsLabel = new Label("POIs");
	private Label currentPage = new Label(currentPageLabelText);
	private Label currentPoi = new Label("Casino");
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	private FlowPanel bottomResultPanel = new FlowPanel();
	private Image rightNavImg;
	private Image leftNavImg;
	private Image bottomRightNavImg;
	private Image bottomLeftNavImg;
	
	@Override
	public void init(){
		super.init();
		
		resultsPanel.setStyleName("poiResultsPanel");
		resultsLabel.setStyleName("poiResultsLabel");
		results.setStyleName("poiResults");
		currentPage.setStyleName("poiPage");
		currentPoi.setStyleName("currentPoiLabel");
		qrBox.setStyleName("poiQrBox");
		bottomResultPanel.setStyleName("poiBottomResultPanel");
		
		Image img = new Image("img/PoI.svg");
		
		ResultButton firstResult = new ResultButton(1, "Eins", this, "000", img);
		ResultButton secondResult = new ResultButton(2, "Eins", this, "000", img);
		ResultButton thirdResult = new ResultButton(3, "Eins", this, "000", img);
		ResultButton fourthResult = new ResultButton(4, "Eins", this, "000", img);
		
		results.setWidget(0, 0, firstResult);
		results.setWidget(0, 1, secondResult);
		results.setWidget(1, 0, thirdResult);
		results.setWidget(1, 1, fourthResult);
		
		bottomResultPanel.add(firstResult.getBottomPictureButton());
		bottomResultPanel.add(secondResult.getBottomPictureButton());
		bottomResultPanel.add(thirdResult.getBottomPictureButton());
		bottomResultPanel.add(fourthResult.getBottomPictureButton());
		
		final String navImg = new String("img/KeineKarte.svg");
		Image navigationImg = new Image(navImg);
		navigationImg.setStyleName("poiNavigationImg");
		
		final String rightNavigation = new String("img/PoI.svg");
		final String leftNavigation = new String("img/PoI.svg");
		
		rightNavImg = new Image(rightNavigation);
		leftNavImg = new Image(leftNavigation);
		
		rightNavImg.setStyleName("poiRightNav");
		leftNavImg.setStyleName("poiLeftNav");
		
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
		
		bottomRightNavImg = new Image(rightNavigation);
		bottomLeftNavImg = new Image(leftNavigation);
		
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
		
		bottomRightNavImg.setStyleName("poiBottomRightNav");
		bottomLeftNavImg.setStyleName("poiBottomLeftNav");

		resultsPanel.add(resultsLabel);
		resultsPanel.add(results);
		this.add(resultsPanel);
		this.add(currentPage);
		this.add(currentPoi);
		this.add(navigationImg);
		this.add(qrBox);
		this.add(leftNavImg);
		this.add(rightNavImg);
		this.add(bottomLeftNavImg);
		this.add(bottomRightNavImg);
		this.add(bottomResultPanel);
		
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


	@Override
	protected void handleId(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasDefaultQrBox() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void showNextPois(){
		currentPageNr += 1;
		currentPage.setText("Seite "+ currentPageNr);
		if (currentPageNr == 3){
			// hide button
			rightNavImg.getElement().getStyle().setDisplay(Display.NONE);
			bottomRightNavImg.getElement().getStyle().setDisplay(Display.NONE);
		}
		leftNavImg.getElement().getStyle().setDisplay(Display.INLINE);
		bottomLeftNavImg.getElement().getStyle().setDisplay(Display.INLINE);
	}
	
	public void showPreviousPois(){
		currentPageNr -= 1;
		currentPage.setText("Seite "+ currentPageNr);
		if (currentPageNr == 1){
			leftNavImg.getElement().getStyle().setDisplay(Display.NONE);
			bottomLeftNavImg.getElement().getStyle().setDisplay(Display.NONE);
		}
		rightNavImg.getElement().getStyle().setDisplay(Display.INLINE);
		bottomRightNavImg.getElement().getStyle().setDisplay(Display.INLINE);
	}

	
	
}
