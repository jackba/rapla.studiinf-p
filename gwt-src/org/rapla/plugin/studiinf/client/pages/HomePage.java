package org.rapla.plugin.studiinf.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Picture;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.FreeRoomButton;
import org.rapla.plugin.studiinf.client.ui.FreeRoomTable;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.plugin.studiinf.client.ui.Tile;
import org.rapla.plugin.studiinf.client.ui.TileContainer;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;


public class HomePage extends AbstractPage {
	
	private ResultTable freeRoomsTable;
	protected  AccessibilityRow resultBtns = new  AccessibilityRow();
	private Label resultLabel = new Label(Studiinf.i18n.freeRooms());
	private FlowPanel resultPanel = new FlowPanel();
	
	
	@Override
	public void init() {
		super.init();
		TileContainer tileContainer = new TileContainer();
		
		Tile courseBtn = new Tile(Studiinf.i18n.courses(),Navigation.course, IconProvider.Courses);
		Tile personBtn = new Tile(Studiinf.i18n.people(),Navigation.person, IconProvider.Persons);
		Tile roomBtn = new Tile(Studiinf.i18n.rooms(),Navigation.room, IconProvider.Rooms);
		Tile poiBtn = new Tile(Studiinf.i18n.pointsOfInterest(),Navigation.poi, IconProvider.PoI);
		freeRoomsTable = new FreeRoomTable(resultBtns, 2, 7);
		freeRoomsTable.setStyleName("freeRooms");
		
		resultLabel.setStyleName("infoLabel");
		resultPanel.setStyleName("resultPanel");
	
		
		QRBox qrBox = new QRBox(getHistoryKey());
		tileContainer.add(courseBtn);
		tileContainer.add(personBtn);
		tileContainer.add(roomBtn);
		tileContainer.add(poiBtn);
		
		qrBox.getElement().getStyle().setProperty("top", "48vh");
		
		
		this.add(tileContainer);
		this.add(qrBox);
		resultPanel.add(resultLabel);
		resultPanel.add(freeRoomsTable);
		this.add(resultPanel);
		this.add(resultBtns);
		this.updateFreeRooms();
	};
	
	@Override
	public String getTitle() {
		return Studiinf.i18n.homeScreenTitle();
		//return "";
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
