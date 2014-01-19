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
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class DetailPageCourse extends AbstractDetailPage {
	
	private String id;
	
	private FlowPanel infoPanel;
	private FlowPanel bottomPanel;
	private FlowPanel middlePanel;
	private Label infoLabel;
	private Label appointmentLabel;
	private Grid infos;
	
	private IconButton name;
	private IconButton study;
	private NavButton prof;
	private NavButton room;
	private NavButton eventsBtn;
	private NavButton prof2;
	private NavButton room2;
	private NavButton events2;
	private List<Event> events;
	private ResultTable lectures = new ResultTable(new FlowPanel(), 2, 3);

	private IconButton nameButton;
	private IconButton courseOfStudyButton;
	private RessourceButton roomButton;
	private NavButton raplaButton;
	private RessourceButton roomButton2;
	private NavButton raplaButton2;
	
	private String nameButtonText;
	private String courseOfStudyButtonText;
	private String roomButtonText;

	private String courseName;

	private String studyName;

	private String profName;

	private String roomName;
	
	@Override
	public void init(){
		super.init();
		
		infoPanel = new FlowPanel();
		middlePanel = new FlowPanel();
		bottomPanel = new FlowPanel();
		infoLabel = new Label("Information");
		appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
		infos = new Grid(4, 1);
		
		name = new IconButton(courseName, new Image(IconProvider.COURSE));
		study = new IconButton(studyName, new Image(IconProvider.COURSES));
		prof = new NavButton(IconProvider.Persons ,profName, Navigation.personDetail,null);
		room = new NavButton(IconProvider.Rooms,roomName, Navigation.roomDetail,null);
		eventsBtn = new NavButton(IconProvider.Calendar,"Link Rapla",  Navigation.raplaCourseLink, id);
		nameButton = new IconButton(nameButtonText, new Image(IconProvider.COURSE));
		courseOfStudyButton = new IconButton(courseOfStudyButtonText, new Image(IconProvider.COURSES));
		roomButton = new RessourceButton(roomButtonText,  IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		roomButton2 = new RessourceButton(roomButtonText,  IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		raplaButton = new NavButton(IconProvider.Calendar,"Link Rapla", Navigation.raplaCourseLink, id);
		raplaButton2 = new NavButton(IconProvider.Calendar,"Link Rapla",Navigation.raplaCourseLink, id);
		
		prof2 = new NavButton(IconProvider.Persons,profName, Navigation.personDetail,null);
		room2 = new NavButton(IconProvider.Rooms,roomName, Navigation.roomDetail,null);
		events2 = new NavButton(IconProvider.Calendar,"Link Rapla", Navigation.raplaCourseLink, id);
		
		prof2.setStyleName("courseProf");
		room2.setStyleName("courseRoom");
		events2.setStyleName("courseEvents");
		roomButton2.setStyleName("bottomButton");
		raplaButton2.setStyleName("bottomButton");
		
		infoPanel.setStyleName("courseInfoPanel");
		middlePanel.setStyleName("courseMiddlePanel");
		bottomPanel.setStyleName("courseBottomPanel");
		infoLabel.setStyleName("courseInfoLabel");
		appointmentLabel.setStyleName("courseAppointmentLabel");
		infos.setStyleName("courseInfos");
		lectures.setStyleName("courseLecturesTable");
		
		infos.setWidget(0, 0, nameButton);
		infos.setWidget(1, 0, courseOfStudyButton);
		infos.setWidget(2, 0, roomButton);
		infos.setWidget(3, 0, raplaButton);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		middlePanel.add(lectures);
		middlePanel.add(appointmentLabel);

		bottomPanel.add(roomButton2);
		bottomPanel.add(raplaButton2);
		
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
		roomButton.setText(roomButtonText);
		roomButton2.setText(roomButtonText);
		raplaButton.setTargetId(id);
		raplaButton2.setTargetId(id);
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}

	public void showRaplaLinks(boolean show){
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
			raplaButton2.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			raplaButton2.getElement().getStyle().setDisplay(Display.NONE);
			appointmentLabel.setText(Studiinf.i18n.noAppointments());
		}

	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		CourseDescriptor cd = new CourseDescriptor(resource);
		
		this.id = id;
		nameButtonText = cd.getName();
		cd.getPicture();
		
		cd.getYear();
		
		if(!cd.getDepartment().equals("")){
			courseOfStudyButtonText = cd.getDepartment();
			courseOfStudyButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				courseOfStudyButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		if(!cd.getRoomNr().equals("")){
			roomButtonText = cd.getRoomNr();
			roomButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			roomButton2.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				roomButton.getElement().getStyle().setDisplay(Display.NONE);
				roomButton2.getElement().getStyle().setDisplay(Display.NONE);
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

				Image lectureRoomImg = new Image(IconProvider.ROOMS);
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
