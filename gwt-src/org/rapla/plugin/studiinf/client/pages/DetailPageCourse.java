package org.rapla.plugin.studiinf.client.pages;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class DetailPageCourse extends AbstractDetailPage {
	
	private String id;
	
	private FlowPanel infoPanel;
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private FlowPanel middlePanel;
	private Label infoLabel;
	private Label appointmentLabel;
	private ResultTable infos;
	
	private List<Event> events;
	private ResultTable lectures = new ResultTable(new AccessibilityRow(), 2, 3);

	private ResultButton nameButton;
	private ResultButton courseOfStudyButton;
	private RessourceButton roomButton;
	private ResultButton raplaButton;
	
	private String nameButtonText;
	private String courseOfStudyButtonText;
	private String roomButtonText;

	@Override
	public void init(){
		super.init();
		
		infoPanel = new FlowPanel();
		middlePanel = new FlowPanel();
		infoLabel = new Label(Studiinf.i18n.information());
		appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
		infos = new ResultTable(bottomPanel, 1, 4);
		infos.setWidth("100%");
		
		nameButton = new ResultButton(nameButtonText, null, null, IconProvider.Courses, false);
		courseOfStudyButton = new ResultButton(courseOfStudyButtonText, null, null, IconProvider.Courses, false);
		roomButton = new RessourceButton(roomButtonText,  IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room,false, true);
		raplaButton = new ResultButton(Studiinf.i18n.linkRapla(), Navigation.raplaCourseLink, id, IconProvider.Calendar, true);

		infoPanel.setStyleName("infoPanel");
		middlePanel.setStyleName("courseMiddlePanel");
		infoLabel.setStyleName("infoLabel");
		appointmentLabel.setStyleName("courseAppointmentLabel");
		lectures.setStyleName("courseLecturesTable");
		
		infos.clearResults();
		infos.addResult(nameButton);
		infos.addResult(courseOfStudyButton);
		infos.addResult(roomButton);
		infos.addResult(raplaButton);
		infos.refresh();
		
		infoPanel.add(infoLabel);
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		middlePanel.add(lectures);
		middlePanel.add(appointmentLabel);
		
		this.add(infoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
	}

	@Override
	public String getHistoryKey() {
		return "courseDetail";
	}

	@Override
	public String getTitle() {
		if(nameButtonText == null){
			nameButtonText = "";
		}
		return nameButtonText;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
		nameButton.setText(nameButtonText);
		courseOfStudyButton.setText(courseOfStudyButtonText);
		raplaButton.setTargetId(id);
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}

	public void showRaplaLinks(boolean show){
//		if (show == true){
//			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
////			raplaButton2.getElement().getStyle().setDisplay(Display.INLINE);
//		} else {
//			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
////			raplaButton2.getElement().getStyle().setDisplay(Display.NONE);
//			appointmentLabel.setText(Studiinf.i18n.noAppointments());
//		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		CourseDescriptor cd = new CourseDescriptor(resource);
		
		this.id = id;
		nameButtonText = cd.getName();
		cd.getPicture();
		cd.getYear();
		courseOfStudyButtonText = cd.getDepartment();
		courseOfStudyButton.setText(cd.getDepartment());
		roomButton.setText(cd.getRoomNr());
		
		if(!cd.getDepartment().equals("")){
			courseOfStudyButtonText = cd.getDepartment();
			courseOfStudyButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				courseOfStudyButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		if(!cd.getRoomNr().equals("")){
			roomButtonText = cd.getRoomNr();
			roomButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				roomButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		
		refresh();
		
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
					
			}

			@Override
			public void onSuccess(List<Event> arg0) {

				events = new ArrayList<Event>(arg0);
				if (events.size()<1) {
					showRaplaLinks(false);
				}
				lectures.clear();

				if(events.size()>=1)
				{
				showRaplaLinks(true);
				Label firstLecture = new Label(events.get(0).toString());
				lectures.setWidget(0, 0, firstLecture);
				if(!events.get(0).getResources().isEmpty())
				{
				NavButton firstLectureRoom = new NavButton(IconProvider.Rooms,events.get(0).getResources().get(0).getName(),  Navigation.roomDetail, events.get(0).getResources().get(0).getId() );
				lectures.setWidget(0, 1, firstLectureRoom);
				}
				}
				if(events.size()>=2)
				{
				Label secondLecture = new Label(events.get(1).toString());
				lectures.setWidget(1, 0, secondLecture);
				if(!events.get(1).getResources().isEmpty())
				{
					NavButton secondLectureRoom = new NavButton(IconProvider.Rooms,events.get(1).getResources().get(0).getName(), Navigation.roomDetail, events.get(1).getResources().get(0).getId() );
					lectures.setWidget(1, 1, secondLectureRoom);
				}
				}
				if(events.size()>=3)
				{
				Label thirdLecture = new Label(events.get(2).toString());	
				lectures.setWidget(2, 0, thirdLecture);
				if(!events.get(2).getResources().isEmpty())
				{
					NavButton thirdLectureRoom = new NavButton(IconProvider.Rooms,events.get(2).getResources().get(0).getName(), Navigation.roomDetail, events.get(2).getResources().get(0).getId() );
					lectures.setWidget(2, 1, thirdLectureRoom);
				
				}
				}
				middlePanel.add(lectures);			
				
				}
		});
		
		
	}

}
