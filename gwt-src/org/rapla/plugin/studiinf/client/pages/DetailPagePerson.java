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
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;
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
	private ResultTable lectures = new ResultTable(new FlowPanel(), 2, 3);
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private String name;
	List<Event> events;
	
	private IconButton mailButton;
	private IconButton telephoneButton;
	private NavigationIconButton roomButton;
	private IconButton roomButton2;
	private NavigationIconButton raplaButton;
	private NavigationIconButton raplaButton2;
	private NavigationIconButton extraInfosButton;
	private NavigationIconButton extraInfosButton2;
	
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
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		lectures.setStyleName("lecturesTable");
		
		roomButton = new RessourceButton(roomButtonText,new  Image(IconProvider.ROOMS), Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
				
		mailButton = new IconButton(mailButtonText, new Image(IconProvider.E_MAIL));
		telephoneButton = new IconButton(telephoneButtonText, new Image(IconProvider.PHONE));
		extraInfosButton = new NavigationIconButton(Studiinf.i18n.extraInfos(), new Image(IconProvider.ADDITIONAL_INFORMATION), Navigation.extraInfo, id);
		raplaButton = new NavigationIconButton(Studiinf.i18n.linkRapla(), new Image(IconProvider.ADDITIONAL_INFORMATION), Navigation.raplaPersonLink, id);
		
		infos.setWidget(0, 0, roomButton);
		infos.setWidget(1, 0, mailButton);
		infos.setWidget(2, 0, telephoneButton);
		infos.setWidget(3, 0, extraInfosButton);
		infos.setWidget(4, 0, raplaButton);
		
		new Image(IconProvider.PERSONS).setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(new Image(IconProvider.PERSONS));
		personInfoPanel.add(infos);
		
		middlePanel.add(appointmentLabel);
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(lectures);
		
		roomButton2 = new NavigationIconButton(roomButtonText, new Image(IconProvider.ROOMS),Navigation.roomDetail,roomButtonText);
		extraInfosButton2 = new NavigationIconButton(Studiinf.i18n.extraInfos(), new Image(IconProvider.ADDITIONAL_INFORMATION), Navigation.extraInfo, id);
		raplaButton2 = new NavigationIconButton(Studiinf.i18n.linkRapla(), new Image(IconProvider.ROOMS),Navigation.raplaPersonLink, id);
		
		extraInfosButton2.setStyleName("personShowExtraInfosBtn");
		raplaButton2.setStyleName("personLinkRaplabtn");
		roomButton2.setStyleName("personShowRoomBtn");
		//extraInfosButton2.setEnabled(false);
		
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
		roomButton.setText(roomButtonText);
		telephoneButton.setText(telephoneButtonText);
		mailButton.setText(mailButtonText); 
		roomButton2.setText(roomButtonText);
		raplaButton.setTargetId(id);
		raplaButton2.setTargetId(id);
		extraInfosButton.setTargetId(id);
		extraInfosButton2.setTargetId(id);
		
	}


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
				Image lectureRoomImg = new Image(IconProvider.ROOMS);
				if(events.size()>=1)
				{
				showRaplaLinks(true);
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
