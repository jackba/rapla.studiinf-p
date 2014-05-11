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
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.FreeRoomTable;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class DetailPagePerson extends AbstractDetailPage {
	
	private String courseOfStudy;
	private String id;

	private FlowPanel personInfoPanel = new FlowPanel();
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label(Studiinf.i18n.information());
	private Label courseOfStudyLabel = new Label(Studiinf.i18n.courseOfStudy());
	private Label courseOfStudyInfo = new Label(courseOfStudy);
	private ResultTable infos;
	private ResultTable lectures = new FreeRoomTable(new  AccessibilityRow(), 2, 3);
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private String name;
	List<Event> events;
	
	private ResultButton mailButton;
	private ResultButton telephoneButton;
	private RessourceButton roomButton;
	private ResultButton raplaButton;
	private ResultButton extraInfosButton;
	
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
		personInfoLabel.setStyleName("infoLabel");
		middlePanel.setStyleName("personMiddlePanel");
		infos = new ResultTable(bottomPanel, 1, 4);
		infos.setWidth("75%");
		
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		lectures.setStyleName("lecturesTable");
		
		roomButton = new RessourceButton(roomButtonText,IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		mailButton = new ResultButton(IconProvider.Email, mailButtonText, null, null, false);
		telephoneButton = new ResultButton(IconProvider.Phone, telephoneButtonText, null, null, false);
		extraInfosButton = new ResultButton(IconProvider.Additional_Information,Studiinf.i18n.extraInfos(), Navigation.extraInfo, id, true);
		raplaButton = new ResultButton(IconProvider.Additional_Information,Studiinf.i18n.linkRapla(), Navigation.raplaPersonLink, id, true);		
		
		roomButton.setSize(0.8);
		raplaButton.setSize(0.8);
		extraInfosButton.setSize(0.8);
		mailButton.setSize(0.8);
		telephoneButton.setSize(0.8);
		
		infos.clearResults();
		infos.addResult(roomButton);
		infos.addResult(mailButton);
		infos.addResult(telephoneButton);
		infos.addResult(extraInfosButton);
		infos.addResult(raplaButton);
		infos.refresh();
		
		FontIcon icon = new FontIcon(IconProvider.Persons.getUrl());
		icon.setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(icon);
		personInfoPanel.add(infos);
		
		middlePanel.add(appointmentLabel);
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(lectures);
		
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
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			appointmentLabel.setText(Studiinf.i18n.noAppointments());
		}
	}
	
	

	@Override
	protected void refresh() {
		super.refresh();
		telephoneButton.setText(telephoneButtonText);
		mailButton.setText(mailButtonText); 
		roomButton.setText(roomButtonText);
		raplaButton.setTargetId(id);
		extraInfosButton.setTargetId(id);
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
