package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;

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
	private Grid infos = new Grid(5, 1);
	private Grid lectureRooms = new Grid(3, 1);
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private String name;
	List<Event> events;
	
	private NavigationIconButton roomNrBtn;
	private IconButton mailBtn;
	private IconButton telephoneBtn;
	private IconButton showRoomBtn;
	private NavigationIconButton raplaBtn;
	
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
		lectureRooms.setStyleName("personLectureRooms");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		
		roomNrBtn = new RessourceButton(departmentText,new  Image(IconProvider.ROOMS), Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
				
		mailBtn = new IconButton(mailText, new Image(IconProvider.E_MAIL));
		telephoneBtn = new IconButton(phoneText, new Image(IconProvider.PHONE));
		Widget extraInfosBtn = new IconButton("Zusätzliche Infos", new Image(IconProvider.ADDITIONAL_INFORMATION));
		raplaBtn = new NavigationIconButton("Link to Rapla", new Image(IconProvider.ADDITIONAL_INFORMATION), this);
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		infos.setWidget(4, 0, raplaBtn);
		
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
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(lectureRooms);
		
		showRoomBtn = new NavigationIconButton(departmentText, new Image(IconProvider.ROOMS),Navigation.roomDetail,departmentText);
		showRoomBtn.setStyleName("personShowRoomBtn");
		Widget showextraInfosBtn = new IconButton("Extrainfos anzeigen", new Image(IconProvider.ADDITIONAL_INFORMATION));
		NavigationIconButton linkRapla2 = new NavigationIconButton("Link to Rapla", new Image(IconProvider.ROOMS),this,"");
		showextraInfosBtn.setStyleName("personShowExtraInfosBtn");
		linkRapla2.setStyleName("personLinkRaplabtn");
		
		bottomPanel.add(showRoomBtn);
		bottomPanel.add(showextraInfosBtn);
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
		
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		PersonDescriptor person = new PersonDescriptor(resource);
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
