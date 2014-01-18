package org.rapla.plugin.studiinf.client.pages;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.search.CourseDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class DetailPageCourse extends AbstractDetailPage {
	
	private String courseName;
	private String studyName;
	private String roomName;
	private String id;
	
	private FlowPanel infoPanel;
	private FlowPanel bottomPanel;
	private FlowPanel middlePanel;
	private Label infoLabel;
	private Grid infos;
	
	private List<Event> events;
	private ResultTable lectures = new ResultTable(new FlowPanel(), 2, 3);
	
	
	private IconButton name;
	private IconButton study;
	private NavigationIconButton room;
	private NavigationIconButton raplaButton;
	private NavigationIconButton room2;
	private NavigationIconButton events2;
	
	@Override
	public void init(){
		super.init();
		
		infoPanel = new FlowPanel();
		middlePanel = new FlowPanel();
		bottomPanel = new FlowPanel();
		infoLabel = new Label("Information");
		infos = new Grid(4, 1);
		
		name = new IconButton(courseName, new Image(IconProvider.COURSE));
		study = new IconButton(studyName, new Image(IconProvider.COURSES));
		room = new NavigationIconButton(roomName, new Image(IconProvider.ROOMS), Navigation.roomDetail);
		raplaButton = new NavigationIconButton("Link Rapla", new Image(IconProvider.CALENDAR), Navigation.raplaCourseLink, id);
		
		room2 = new NavigationIconButton(roomName, new Image(IconProvider.ROOMS), Navigation.roomDetail);
		events2 = new NavigationIconButton("Link Rapla", new Image(IconProvider.CALENDAR), Navigation.raplaCourseLink, id);
		
		room2.setStyleName("courseRoom");
		events2.setStyleName("courseEvents");
		
		infoPanel.setStyleName("courseInfoPanel");
		middlePanel.setStyleName("personMiddlePanel");
		bottomPanel.setStyleName("courseBottomPanel");
		infoLabel.setStyleName("courseInfoLabel");
		infos.setStyleName("courseInfos");
		lectures.setStyleName("lecturesTable");
		
		infos.setWidget(0, 0, name);
		infos.setWidget(1, 0, study);
		infos.setWidget(2, 0, room);
		infos.setWidget(3, 0, raplaButton);

		
		infoPanel.add(infoLabel);
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		

		middlePanel.add(lectures);
		

		bottomPanel.add(room2);
		bottomPanel.add(events2);
		
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
		if(courseName == null){
			courseName = "";
		}
		return courseName;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
		name.setText(courseName);
		study.setText(studyName);
		room.setText(roomName);
		room2.setText(roomName);
		raplaButton.setTargetId(id);
		events2.setTargetId(id);
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		CourseDescriptor cd = new CourseDescriptor(resource);
		
		this.id = id;
		courseName = cd.getName();
		cd.getPicture();
		
		cd.getYear();
		
		if(!cd.getDepartment().equals("")){
			studyName = cd.getDepartment();
			study.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				study.getElement().getStyle().setDisplay(Display.NONE);
			}
		if(!cd.getRoomNr().equals("")){
			roomName = cd.getRoomNr();
			room.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			room2.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				room.getElement().getStyle().setDisplay(Display.NONE);
				room2.getElement().getStyle().setDisplay(Display.NONE);
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
				
				lectures.clear();

				Image lectureRoomImg = new Image(IconProvider.ROOMS);
				if(events.size()>=1)
				{

				Label firstLecture = new Label(events.get(0).toString());

				lectures.setWidget(0, 0, firstLecture);
				if(!events.get(0).getResources().isEmpty())
				{

				NavigationIconButton firstLectureRoom = new NavigationIconButton(events.get(0).getResources().get(0).getName(), lectureRoomImg, Navigation.roomDetail, events.get(0).getResources().get(0).getId() );

				lectures.setWidget(0, 1, firstLectureRoom);
				}
				}
				if(events.size()>=2)
				{
				Label secondLecture = new Label(events.get(1).toString());
				

				lectures.setWidget(1, 0, secondLecture);
				if(!events.get(1).getResources().isEmpty())
				{
					NavigationIconButton secondLectureRoom = new NavigationIconButton(events.get(1).getResources().get(0).getName(), lectureRoomImg, Navigation.roomDetail, events.get(1).getResources().get(0).getId() );

					lectures.setWidget(1, 1, secondLectureRoom);

				}
				}
				if(events.size()>=3)
				{
				Label thirdLecture = new Label(events.get(2).toString());	
				
//				
				lectures.setWidget(2, 0, thirdLecture);
				if(!events.get(2).getResources().isEmpty())
				{
					NavigationIconButton thirdLectureRoom = new NavigationIconButton(events.get(2).getResources().get(0).getName(), lectureRoomImg, Navigation.roomDetail, events.get(2).getResources().get(0).getId() );
	
					lectures.setWidget(2, 1, thirdLectureRoom);
				
				}
				}
				middlePanel.add(lectures);			
				
				}
		});
		
		
	}

}
