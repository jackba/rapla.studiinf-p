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
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
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

public class DetailPagePerson extends AbstractDetailPage {
	
	private String courseOfStudy = "Wirtschaftsinformatik";
	private String id;

	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Label courseOfStudyLabel = new Label(Studiinf.i18n.courseOfStudy());
	private Label courseOfStudyInfo = new Label(courseOfStudy);
	private Grid infos = new Grid(5, 1);
//	private Grid lectureRooms = new Grid(3, 1);
	private ResultTable lectures = new ResultTable(new FlowPanel(), 2, 3);
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private String name;
	List<Event> events;
	
	private NavButton roomNrBtn;
	private IconButton mailBtn;
	private IconButton telephoneBtn;
	private RessourceButton showRoomBtn;
	private NavButton extraInfosBtn;
	private NavButton raplaBtn;
	private NavButton linkRapla2;
	private NavButton showextraInfosBtn;
	
	private String departmentText;
	private String mailText;
	private String phoneText;
	
	@Override
	public boolean hasDefaultQrBox(){
		return true;
	}
	
	
	@Override
	public void init(){
		super.init();
	
		personInfoPanel.setStyleName("personInfoPanel");
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		lectures.setStyleName("lecturesTable");
		
		roomNrBtn = new RessourceButton(departmentText,IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		
		mailBtn = new IconButton(mailText, new Image(IconProvider.E_MAIL));
		telephoneBtn = new IconButton(phoneText, new Image(IconProvider.PHONE));
		extraInfosBtn = new NavButton(IconProvider.Additional_Information,"Extrainfos anzeigen", Navigation.extraInfo, id);
		raplaBtn = new NavButton(IconProvider.Additional_Information,"Link to Rapla", Navigation.raplaPersonLink, id);
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		infos.setWidget(4, 0, raplaBtn);
		
//		Image lectureRoomImg = new Image(IconProvider.ROOMS);
//		
//		IconButton firstLectureRoom = new IconButton("D195", lectureRoomImg);
//		IconButton secondLectureRoom = new IconButton("D295", lectureRoomImg);
//		IconButton thirdLectureRoom = new IconButton("D395", lectureRoomImg);
		
//		lectureRooms.setWidget(0, 0, firstLectureRoom);
//		lectureRooms.setWidget(1, 0, secondLectureRoom);
//		lectureRooms.setWidget(2, 0, thirdLectureRoom);
		
		new Image(IconProvider.PERSONS).setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(new Image(IconProvider.PERSONS));
		personInfoPanel.add(infos);
		
		middlePanel.add(appointmentLabel);
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(lectures);

		
		showRoomBtn = new RessourceButton(departmentText, IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room,true);
		showRoomBtn.setStyleName("personShowRoomBtn");
		showextraInfosBtn = new NavButton(IconProvider.Additional_Information,"Extrainfos anzeigen",  Navigation.extraInfo, id);
		linkRapla2 = new NavButton(IconProvider.Rooms,"Link to Rapla", Navigation.raplaPersonLink, id);
		showextraInfosBtn.setStyleName("personShowExtraInfosBtn");
		linkRapla2.setStyleName("personLinkRaplabtn");
		showextraInfosBtn.setEnabled(false);
//		bottomPanel.add(showRoomBtn);
//		bottomPanel.add(showextraInfosBtn);
		bottomPanel.add(linkRapla2);
		
		this.add(personInfoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
	}
	
	@Override
	public String getHistoryKey() {
		return "personDetail";
	}

	@Override
	public String getTitle() {
		if(name == null){
			name ="$NAME$";
		}
		return name;
	}

	
	

	@Override
	protected void refresh() {
		super.refresh();
		roomNrBtn.setText(departmentText);
		telephoneBtn.setText(phoneText);
		mailBtn.setText(mailText); 
		showRoomBtn.setText(departmentText);
		raplaBtn.setTargetId(id);
		linkRapla2.setTargetId(id);
		extraInfosBtn.setTargetId(id);
		showextraInfosBtn.setTargetId(id);
		
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		PersonDescriptor person = new PersonDescriptor(resource);
		this.id = id;
		//Window.alert(person.getName() + ", "+ person.getMail()+ ", "+ person.getPhoneNr());
		
		name = person.getName();
		departmentText = person.getRoomNr();
		if(!person.getMail().equals("")){
		mailText = person.getMail();
		mailBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		}else{
			mailBtn.getElement().getStyle().setDisplay(Display.NONE);
		}
		
		if(!person.getPhoneNr().equals("")){
			phoneText = person.getPhoneNr();
			telephoneBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				telephoneBtn.getElement().getStyle().setDisplay(Display.NONE);
			}
		
		courseOfStudyInfo.setText(person.getDepartment());
		
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
				if(events.size()>=1)
				{
				Label firstLecture = new Label(events.get(0).toString());
//				middlePanel.add(firstLecture);
				lectures.setWidget(0, 0, firstLecture);
				if(!events.get(0).getResources().isEmpty())
				{
				NavButton firstLectureRoom = new NavButton(IconProvider.Rooms,events.get(0).getResources().get(0).getName(), Navigation.roomDetail, events.get(0).getResources().get(0).getId() );
	//			lectureRooms.setWidget(0, 0, firstLectureRoom);
				lectures.setWidget(0, 1, firstLectureRoom);
				}
				}
				if(events.size()>=2)
				{
				Label secondLecture = new Label(events.get(1).toString());
				
//				middlePanel.add(secondLecture);
				lectures.setWidget(1, 0, secondLecture);
				if(!events.get(1).getResources().isEmpty())
				{
					NavButton secondLectureRoom = new NavButton(IconProvider.Rooms,events.get(1).getResources().get(0).getName(),  Navigation.roomDetail, events.get(1).getResources().get(0).getId() );
			//	lectureRooms.setWidget(1, 0, secondLectureRoom);
					lectures.setWidget(1, 1, secondLectureRoom);
			//	lectures.add(secondLecture);
				}
				}
				if(events.size()>=3)
				{
				Label thirdLecture = new Label(events.get(2).toString());	
				
//				middlePanel.add(thirdLecture);
				lectures.setWidget(2, 0, thirdLecture);
				if(!events.get(2).getResources().isEmpty())
				{
					NavButton thirdLectureRoom = new NavButton(IconProvider.Rooms,events.get(2).getResources().get(0).getName(), Navigation.roomDetail, events.get(2).getResources().get(0).getId() );
				//lectureRooms.setWidget(2, 0, thirdLectureRoom);
					lectures.setWidget(2, 1, thirdLectureRoom);
				//lectures.add(thirdLecture);
				}
				}
				middlePanel.add(lectures);			
				
				}
		});
		
	}

}
