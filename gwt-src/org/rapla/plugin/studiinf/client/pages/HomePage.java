package org.rapla.plugin.studiinf.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.FreeRoomButton;
import org.rapla.plugin.studiinf.client.ui.FreeRoomTable;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.plugin.studiinf.client.ui.Tile;
import org.rapla.plugin.studiinf.client.ui.TileContainer;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwtjsonrpc.common.AsyncCallback;


public class HomePage extends AbstractPage {
	
	private ResultTable freeRoomsTable;
	protected FlowPanel resultBtns = new FlowPanel();
	private Label resultLabel = new Label(Studiinf.i18n.freeRooms());
	private FlowPanel resultPanel = new FlowPanel();
	
	
	@Override
	public void init() {
		super.init();
		TileContainer tileContainer = new TileContainer();
		
		Tile courseBtn = new Tile(Studiinf.i18n.courses(),Navigation.course);
		Tile personBtn = new Tile(Studiinf.i18n.people(),Navigation.person);
		Tile roomBtn = new Tile(Studiinf.i18n.rooms(),Navigation.room);
		Tile poiBtn = new Tile(Studiinf.i18n.pointsOfInterest(),Navigation.poi);
		freeRoomsTable = new FreeRoomTable(resultBtns, 2, 3);
		freeRoomsTable.setStyleName("freeRooms");
		
		resultBtns.setStyleName("resultBtns");
		resultLabel.setStyleName("resultLabel");
		resultPanel.setStyleName("resultPanel");
		
		for(NavButton b : freeRoomsTable.getButtonsBottom()){
			this.add(b);
		}
		
		QRBox qrBox = new QRBox(getHistoryKey());
		tileContainer.add(courseBtn);
		tileContainer.add(personBtn);
		tileContainer.add(roomBtn);
		tileContainer.add(poiBtn);
		
		qrBox.getElement().getStyle().setProperty("top", "45vh");
		
//		freeRoomsTable.getBackButton().addStyleName("backButton");
//		freeRoomsTable.getNextButton().addStyleName("nextButton");
		
		this.add(tileContainer);
		this.add(qrBox);
//		this.add(logo);
		resultPanel.add(resultLabel);
		resultPanel.add(freeRoomsTable);
		this.add(resultPanel);
		this.add(resultBtns);
//		this.add(freeRoomsTable.getBackButton());
//		this.add(freeRoomsTable.getNextButton());
		this.updateFreeRooms();
//		this.add(new NavButton(Navigation.homePage,null));
	};
	
	@Override
	public String getTitle() {
		//return Studiinf.i18n.homeScreenTitle();
		return "";
	}

	@Override
	public String getHistoryKey() {
		return "";
	}
	
	public void setFreeRooms(List<Event> freeResources)
	{
		if(freeResources.size()< 1){
			resultLabel.setText(Studiinf.i18n.nofreeRooms());
		}else{
			resultLabel.setText(Studiinf.i18n.freeRooms());
			
		}
		for(Event e : freeResources)
			{
				freeRoomsTable.addResult(new FreeRoomButton(e));
				
			}
		freeRoomsTable.refresh();
	}
	
	@SuppressWarnings("deprecation")
	public void updateFreeRooms()
	{
	Date dateBegin = new Date();
	DateTimeFormat f = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm");
	String begin = f.format(dateBegin);
	Date dateEnd = new Date();
	dateEnd.setHours(18);
	dateEnd.setMinutes(00);

	String end = f.format(dateEnd);
	
	ServiceProvider.getFreeResources(begin, end, "raum", new AsyncCallback<List<Event>>(){
		@Override
		public void onFailure(Throwable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSuccess(List<Event> arg0) {
			List<Event> freeResources = new ArrayList<Event>(arg0);
			
			setFreeRooms(freeResources);
		
		}
	
	});
	
	}
	
	@Override
	public void onShow() {
		freeRoomsTable.setPage(0);
		super.onShow();
	}
}
