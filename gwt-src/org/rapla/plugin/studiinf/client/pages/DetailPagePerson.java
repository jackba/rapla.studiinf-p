package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescribtor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class DetailPagePerson extends AbstractDetailPage {
	
	private String courseOfStudy = "Wirtschaftsinformatik";

	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Label courseOfStudyLabel = new Label(Studiinf.i18n.courseOfStudy());
	private Label courseOfStudyInfo = new Label(courseOfStudy);
	private Grid infos = new Grid(4, 1);
	private Grid lectureRooms = new Grid(3, 1);
	private Grid courses = new Grid(2, 3);
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private Label courseLabel = new Label(Studiinf.i18n.courses());
	private String name;
	List<Event> events;
	
	private NavigationIconButton roomNrBtn;
	private IconButton mailBtn;
	private IconButton telephoneBtn;
	private IconButton showRoomBtn;
	
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	
	private String departmentText;
	private String mailText;
	private String phoneText;
	
	@Override
	public boolean hasDefaultQrBox(){
		return false;
	}
	
	
	@Override
	public void init(){
		super.init();
	
		qrBox.setStyleName("personQRBox");
		personInfoPanel.setStyleName("personInfoPanel");
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		courses.setStyleName("personCourses");
		lectureRooms.setStyleName("personLectureRooms");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseLabel.setStyleName("personCourseLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		
		roomNrBtn = new NavigationIconButton(departmentText, new Image(IconProvider.ROOMS),Navigation.roomDetail,departmentText);
		mailBtn = new IconButton(mailText, new Image(IconProvider.E_MAIL));
		telephoneBtn = new IconButton(phoneText, new Image(IconProvider.PHONE));
		Widget extraInfosBtn = new IconButton("Zusätzliche Infos", new Image(IconProvider.ADDITIONAL_INFORMATION));
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		
		Image courseImg = new Image(IconProvider.COURSES);
		
		IconButton firstCourse = new IconButton("Name 1", courseImg);
		IconButton secondCourse = new IconButton("Name 2", courseImg);
		IconButton thirdCourse = new IconButton("Name 3", courseImg);
		IconButton fourthCourse = new IconButton("Name 4", courseImg);
		IconButton fifthCourse = new IconButton("Name 5", courseImg);
		IconButton sixthCourse = new IconButton("Name 6", courseImg);
		
		courses.setWidget(0, 0, firstCourse);
		courses.setWidget(0, 1, secondCourse);
		courses.setWidget(0, 2, thirdCourse);
		courses.setWidget(1, 0, fourthCourse);
		courses.setWidget(1, 1, fifthCourse);
		courses.setWidget(1, 2, sixthCourse); 
		
		Image lectureRoomImg = new Image(IconProvider.ROOMS);
		
		IconButton firstLectureRoom = new IconButton("D195", lectureRoomImg);
		IconButton secondLectureRoom = new IconButton("D295", lectureRoomImg);
		IconButton thirdLectureRoom = new IconButton("D395", lectureRoomImg);
		
		lectureRooms.setWidget(0, 0, firstLectureRoom);
		lectureRooms.setWidget(1, 0, secondLectureRoom);
		lectureRooms.setWidget(2, 0, thirdLectureRoom);
		
		
		new Image(IconProvider.PERSONS).setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(new Image(IconProvider.PERSONS));
		personInfoPanel.add(infos);
		

		middlePanel.add(appointmentLabel);
		middlePanel.add(courseLabel);
		middlePanel.add(courses);
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(lectureRooms);
		
		showRoomBtn = new NavigationIconButton(departmentText, new Image(IconProvider.ROOMS),Navigation.roomDetail,departmentText);
		Widget showextraInfosBtn = new IconButton("Extrainfos anzeigen", new Image(IconProvider.ADDITIONAL_INFORMATION));
		showextraInfosBtn.setStyleName("personShowExtraInfosBtn");
		
		bottomPanel.add(showRoomBtn);
		bottomPanel.add(showextraInfosBtn);
		

		this.add(personInfoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
		this.add(qrBox);
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
		
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		PersonDescribtor person = new PersonDescribtor(resource);
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
		
		ServiceProvider.getEvents("2000-01-07 09:00", "2014-01-07 18:00", id, new AsyncCallback<List<Event>>() {

			@Override
			public void onFailure(Throwable arg0) {
				
				
			}

			@Override
			public void onSuccess(List<Event> arg0) {
				//events = new ArrayList<Event>(arg0);
//				Window.alert(arg0.toString());
				
				/* TODO
				 * if(events.size>=1)
				 * {
				 * event01 = events.get(0).toString();
				 * if(events.size>=2)
				 * {
				 * event02 = events.get(1).toString();
				 * if(events.size>=3)
				 * {
				 * event03 = events.get(2).toString();
				 * }}}
				 * 
				 */
				
				}
		});
		
	}

}
