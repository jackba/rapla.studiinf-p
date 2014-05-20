package org.rapla.plugin.studiinf.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
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

/**
 * 
 * @author Team StudiInf
 * 
 * Homepage of StudiInf application
 *
 */
public class HomePage extends AbstractPage {
	
	private ResultTable freeRoomsTable;
	protected  AccessibilityRow resultBtns = new  AccessibilityRow();
	private Label resultLabel = new Label(Studiinf.i18n.freeRooms());
	private FlowPanel resultPanel = new FlowPanel();
	
	
	@Override
	public void init() {
		super.init();
		TileContainer tileContainer = new TileContainer();
		
		Tile courseBtn = new Tile(Studiinf.i18n.courses(),Navigation.course, FontIcon.Courses);
		Tile personBtn = new Tile(Studiinf.i18n.people(),Navigation.person, FontIcon.Persons);
		Tile roomBtn = new Tile(Studiinf.i18n.rooms(),Navigation.room, FontIcon.Rooms);
		Tile poiBtn = new Tile(Studiinf.i18n.pointsOfInterest(),Navigation.poi, FontIcon.PoI);
		
		if(DisplayMode.isMobile()){
			freeRoomsTable = new FreeRoomTable(resultBtns, 2, 4);
		} else {
			freeRoomsTable = new FreeRoomTable(resultBtns, 2, 7);
		}
		
		freeRoomsTable.setStyleName("freeRooms");
		resultLabel.setStyleName("infoLabel");
		resultPanel.setStyleName("resultPanel");
		
		QRBox qrBox = new QRBox(getHistoryKey());
		tileContainer.add(courseBtn);
		tileContainer.add(personBtn);
		tileContainer.add(roomBtn);
		tileContainer.add(poiBtn);
		
//		qrBox.getElement().getStyle().setProperty("bottom", "12vh");
		
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
	
	/**
	 * Creates a table of free rooms, containing name and time and refreshes the table
	 * @param freeResources List of free rooms
	 */
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
	
	/**
	 * Gets freeRooms of today from rapla server and fires {@link setFreeRooms()}
	 */
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
