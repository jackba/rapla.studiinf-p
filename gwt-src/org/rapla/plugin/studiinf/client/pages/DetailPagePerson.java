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
import org.rapla.plugin.studiinf.client.ui.FreeRoomTable;
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
	
	private String courseOfStudy;
	private String id;

	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Label courseOfStudyLabel = new Label(Studiinf.i18n.courseOfStudy());
	private Label courseOfStudyInfo = new Label(courseOfStudy);
	private Grid infos = new Grid(5, 1);
	private ResultTable lectures = new FreeRoomTable(new FlowPanel(), 2, 3);
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private String name;
	List<Event> events;
	
	private NavButton mailButton;
	private NavButton telephoneButton;
	private RessourceButton roomButton;
	private RessourceButton roomButton2;
	private NavButton raplaButton;
	private NavButton raplaButton2;
	private NavButton extraInfosButton;
	private NavButton extraInfosButton2;
	
	private String roomButtonText;
	private String mailButtonText;
	private String telephoneButtonText;
	
	@Override
	public boolean hasDefaultQrBox(){
		return true;
	}
	
	
	@Override
	public void init(){
		super.init();
	
		personInfoPanel.setStyleName("personInfoPanel");
		//personInfoLabel.setStyleName("personInfoLabel");
		personInfoLabel.setStyleName("infoLabel");
		bottomPanel.setStyleName("bottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		lectures.setStyleName("lecturesTable");
		
		roomButton = new RessourceButton(roomButtonText,IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		mailButton = new NavButton(IconProvider.Email, mailButtonText, null, null);
		telephoneButton = new NavButton(IconProvider.Phone, telephoneButtonText, null, null);
		extraInfosButton = new NavButton(IconProvider.Additional_Information,Studiinf.i18n.extraInfos(), Navigation.extraInfo, id);
		raplaButton = new NavButton(IconProvider.Additional_Information,Studiinf.i18n.linkRapla(), Navigation.raplaPersonLink, id);		
		roomButton2 = new RessourceButton(roomButtonText,IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		extraInfosButton2 = new NavButton(IconProvider.Additional_Information,Studiinf.i18n.extraInfos(),Navigation.extraInfo, id);
		raplaButton2 = new NavButton(IconProvider.Rooms,Studiinf.i18n.linkRapla(), Navigation.raplaPersonLink, id);
		
		extraInfosButton2.setStyleName("bottomButton");
		raplaButton2.setStyleName("bottomButton");
		roomButton2.setStyleName("bottomButton");
		roomButton.setStyleName("personInfoButtons");
		extraInfosButton.setStyleName("personInfoButtons");
		raplaButton.setStyleName("personInfoButtons");
		mailButton.setStyleName("personInfoButtons");
		telephoneButton.setStyleName("personInfoButtons");
		
		roomButton.setSize(0.8);
		raplaButton.setSize(0.8);
		roomButton2.setSize(0.8);
		raplaButton2.setSize(0.8);
		extraInfosButton.setSize(0.8);
		extraInfosButton2.setSize(0.8);
		mailButton.setSize(0.8);
		telephoneButton.setSize(0.8);
		
		infos.setWidget(0, 0, roomButton);
		infos.setWidget(1, 0, mailButton);
		infos.setWidget(2, 0, telephoneButton);
		infos.setWidget(3, 0, extraInfosButton);
		infos.setWidget(4, 0, raplaButton);
		
		Image img =	new Image(IconProvider.PERSONS);
		img.setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(img);
		personInfoPanel.add(infos);
		
		middlePanel.add(appointmentLabel);
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(lectures);
	
		bottomPanel.add(roomButton2);
		bottomPanel.add(extraInfosButton2);
		bottomPanel.add(raplaButton2);
		
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
	protected void refresh() {
		super.refresh();
		telephoneButton.setText(telephoneButtonText);
		mailButton.setText(mailButtonText); 
		roomButton.setText(roomButtonText);
		roomButton2.setText(roomButtonText);
		raplaButton.setTargetId(id);
		raplaButton2.setTargetId(id);
		extraInfosButton.setTargetId(id);
		extraInfosButton2.setTargetId(id);
		
	}


	@SuppressWarnings("deprecation")
	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		PersonDescriptor person = new PersonDescriptor(resource);
		this.id = id;
		
		name = person.getName();
		roomButtonText = person.getRoomNr();
		if(!person.getMail().equals("")){
		mailButtonText = person.getMail();
		mailButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		}else{
			mailButton.getElement().getStyle().setDisplay(Display.NONE);
		}
		
		if(!person.getPhoneNr().equals("")){
			telephoneButtonText = person.getPhoneNr();
			telephoneButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				telephoneButton.getElement().getStyle().setDisplay(Display.NONE);
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
				NavButton firstLectureRoom = new NavButton(IconProvider.Rooms,events.get(0).getResources().get(0).getName(), Navigation.roomDetail, events.get(0).getResources().get(0).getId() );
				lectures.setWidget(0, 1, firstLectureRoom);
				}
				}
				if(events.size()>=2)
				{
				Label secondLecture = new Label(events.get(1).toString());
				
				lectures.setWidget(1, 0, secondLecture);
				if(!events.get(1).getResources().isEmpty())
				{
					NavButton secondLectureRoom = new NavButton(IconProvider.Rooms,events.get(1).getResources().get(0).getName(),  Navigation.roomDetail, events.get(1).getResources().get(0).getId() );
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
