package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;


public class PoiSearchPage extends AbstractDetailPage {
	
	
	private FlowPanel resultsPanel = new FlowPanel();
	private Grid results = new Grid(2, 2);
	private Label resultsLabel = new Label("POIs");
	private Label currentPage = new Label("Seite 1");
	private Label currentPoi = new Label("Casino");
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	
	@Override
	public void init(){
		super.init();
		
		resultsPanel.setStyleName("poiResultsPanel");
		resultsLabel.setStyleName("poiResultsLabel");
		results.setStyleName("poiResults");
		currentPage.setStyleName("poiPage");
		currentPoi.setStyleName("currentPoiLabel");
		qrBox.setStyleName("poiQrBox");
		
		Image img = new Image("img/PoI.svg");
		
		ResultButton firstResult = new ResultButton(1, "Eins", this, "000", img);
		ResultButton secondResult = new ResultButton(2, "Eins", this, "000", img);
		ResultButton thirdResult = new ResultButton(3, "Eins", this, "000", img);
		ResultButton fourthResult = new ResultButton(4, "Eins", this, "000", img);
		
		results.setWidget(0, 0, firstResult);
		results.setWidget(0, 1, secondResult);
		results.setWidget(1, 0, thirdResult);
		results.setWidget(1, 1, fourthResult);
		
		final String navImg = new String("img/KeineKarte.svg");
		Image navigationImg = new Image(navImg);
		navigationImg.setStyleName("poiNavigationImg");
		
		final String rightNavigation = new String("img/PoI.svg");
		final String leftNavigation = new String("img/PoI.svg");
		
		Image rightNavImg = new Image(rightNavigation);
		Image leftNavImg = new Image(leftNavigation);
		
		rightNavImg.setStyleName("poiRightNav");
		leftNavImg.setStyleName("poiLeftNav");

		resultsPanel.add(resultsLabel);
		resultsPanel.add(results);
		this.add(resultsPanel);
		this.add(currentPage);
		this.add(currentPoi);
		this.add(navigationImg);
		this.add(qrBox);
		this.add(leftNavImg);
		this.add(rightNavImg);
		
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

	
	
}
