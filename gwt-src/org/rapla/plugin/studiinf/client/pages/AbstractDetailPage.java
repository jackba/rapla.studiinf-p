package org.rapla.plugin.studiinf.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.FilterRooms;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Template for all detail pages.
 * All detail pages have to implement this class.
 *
 */
public abstract class AbstractDetailPage extends AbstractPage {

	public String id ="";
	protected QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	abstract public boolean hasDefaultQrBox();
	protected List<Event> events;
	protected ResultTable eventPanel = new ResultTable(new AccessibilityRow(), 2, 3);
	protected FlowPanel middlePanel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		if (this.hasDefaultQrBox() == true){
			qrBox.setHash(getHistoryKey()+"/"+getId());
		}
		handleId(id);
	}

	@Override
	public void init() {
		super.init();
		if (this.hasDefaultQrBox() == true){
			qrBox.getElement().getStyle().setProperty("bottom", "12vh");
			this.add(qrBox);
		}
		
	}
	
	@Override
	protected void refresh() {
		super.refresh();
		
	}

	/**
	   * Loads and displays resource details from the rapla server based on the given navigation ID
	   * 
	   * @param id ID of the resource to display information about
	   */
	protected void handleId(final String id){
		ServiceProvider.getResource(id, new AsyncCallback<ResourceDetail>() {
				@Override
				public void onSuccess(ResourceDetail arg0) {
					handleRessource(id, arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
					handleRessource(id, null);
				}
		});
	};
	/**
	 * Sets Texts and information for the detailpage based on the given resource
	 * @param id resource id
	 * @param resource ResourceDetail of the resource that should be displayed on DetailPage
	 */
	abstract protected void handleRessource(String id, ResourceDetail resource);
	
/**
 * Loads all events for the current resource on the current day
 */
	protected void loadEvents(){

		Date dateBegin = new Date();
		DateTimeFormat f = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm");
		String begin = f.format(dateBegin);
		Date dateEnd = new Date();
		dateEnd.setHours(23);
		dateEnd.setMinutes(59);

		String end = f.format(dateEnd);
		
		ServiceProvider.getEvents(begin, end, id, new AsyncCallback<List<Event>>() {

			@Override
			public void onFailure(Throwable arg0) {
				events = new ArrayList<Event>();
				showRaplaLinks(false);
				eventPanel.clear();
				middlePanel.add(eventPanel);
			}

			@Override
			public void onSuccess(List<Event> arg0) {

				events = new ArrayList<Event>(arg0);
				if (events.size()<1) {
					showRaplaLinks(false);
				}
				eventPanel.clear();

				if(events.size()>=1)
				{
				showRaplaLinks(true);
				
				for(int i = 0;i<3 && i < events.size(); i++){
					addEvent(events.get(i));
				}
				
				middlePanel.add(eventPanel);			
				
				}
			}
		});
	}
/**
 * Shows or hides Rapla links
 * @param b Set true or false, depending on the visibility the rapla links should have
 */
	abstract protected void showRaplaLinks(boolean b) ;

	/**
	 * Adds an event as a lecture to the lecturePanel and sets {@link showRaplaLinks()} true.
	 * For every event a {@link NavButton} with the title and time of the event is created which links to the rapla timetable
	 * and a {@link NavButton} which refers to the room and links to the {@link RoomDetailPage}.
	 * @param event Event you want to add to the lecture panel
	 */
	protected void addEvent(Event event) {
		showRaplaLinks(true);
		int row = events.indexOf(event);
		NavButton firstLecture = new NavButton(event2niceString(event), Navigation.raplaCourseLink, id);
		firstLecture.setWidth("100%");
		firstLecture.setSize(0.87);
		eventPanel.setWidget(row, 0, firstLecture);
		
		NavButton roomsShowButton = new NavButton(FontIcon.Rooms, Studiinf.i18n.rooms(), null, null);
		roomsShowButton.setSize(0.87);
		eventPanel.setWidget(row, 1, roomsShowButton);
		
		List<ResourceDescription> resources = event.getResources();
		FlowPanel panel = new FlowPanel();
		PopupPanel rooms = new PopupPanel();
		rooms.add(panel);

		rooms.setVisible(false);
		roomsShowButton.setWidth("100%");
		roomsShowButton.setClickHandler(new DetailPagePersonClickHandler(roomsShowButton,rooms));
		new FilterRooms(panel,resources,roomsShowButton,true);
		
	}
	
	/**
	 * Formats the event.toString to support different localizations
	 * @param event Event that should be formatted
	 * @return Formatted String for display containing a title and localized time
	 */
	private String event2niceString(Event event){
		DateTimeFormat parser = DateTimeFormat.getFormat("HH:mm");
		DateTimeFormat formatter = DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT);
		Date start = parser.parse(event.getStart());
		Date end = parser.parse(event.getEnd());
		String res = event.toString();
		res = res.substring(0,res.lastIndexOf(" "));
		return res + " " +formatter.format(start)+ "-" +formatter.format(end);
		
	}

}
