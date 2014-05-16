package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.RessourceButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class DetailPagePerson extends AbstractDetailPage implements SearchPageInterface {
	
	private String courseOfStudy;
	private String id;

	private FlowPanel personInfoPanel = new FlowPanel();
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label(Studiinf.i18n.information());
	private Label courseOfStudyLabel = new Label(Studiinf.i18n.courseOfStudy());
	private Label courseOfStudyInfo = new Label(courseOfStudy);
	private ResultTable infos;
	private Label appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
	private String name;
	List<Event> events;
	
	private ResultButtonWithLabel mailButton;
	private ResultButtonWithLabel telephoneButton;
	private RessourceButtonWithLabel roomButton;
	private ResultButtonWithLabel raplaButton;
	private ResultButtonWithLabel extraInfosButton;
	
	private String roomButtonText;
	private String roomButtonId;
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
		infos = new ResultTable(bottomPanel, 2, 5);
		infos.getColumnFormatter().setWidth(0, "25%");
		infos.getColumnFormatter().setWidth(1, "75%");
		infos.setWidth("78%");
		infos.getElement().getStyle().setProperty("left", "19vw");
		infos.getElement().getStyle().setProperty("right", "auto");
		infos.getElement().getStyle().setProperty("position", "relative");	
		
//		lectures.getColumnFormatter().setWidth(0, "75%");
//		lectures.getColumnFormatter().setWidth(1, "25%");
		
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		lectures.setStyleName("lecturesTable");
		
		roomButton = new RessourceButtonWithLabel(new ResultButton(IconProvider.Rooms,roomButtonText, Navigation.roomDetail,null,true), new Label(Studiinf.i18n.room()));
		mailButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Email, mailButtonText, null, null, false), new Label(Studiinf.i18n.mail()));
		telephoneButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Phone, telephoneButtonText, null, null, false), new Label(Studiinf.i18n.telephone()));
		extraInfosButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Additional_Information,Studiinf.i18n.extraInfos(), Navigation.extraInfo, id, true), new Label(Studiinf.i18n.extraInfos()));
		raplaButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Calendar,Studiinf.i18n.linkRapla(), Navigation.raplaPersonLink, id, true), new Label(Studiinf.i18n.linkRapla()));		
		
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
		
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(appointmentLabel);
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
			name ="";
		}
		return name;
	}

	public void showRaplaLinks(boolean show){
//		NavButton f = raplaButton.getFooterButton();
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
//			f.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
//			f.getElement().getStyle().setDisplay(Display.NONE);
			raplaButton.hideFooterButton();
			raplaButton.hideLabel();
			appointmentLabel.setText(Studiinf.i18n.noAppointments());
		}
	}	

	@Override
	protected void refresh() {
		super.refresh();
		telephoneButton.setText(telephoneButtonText);
		mailButton.setText(mailButtonText); 
		roomButton.setTargetId(roomButtonId);
		roomButton.setText(roomButtonText);
		raplaButton.setTargetId(id);
		extraInfosButton.setTargetId(id);
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		PersonDescriptor person = new PersonDescriptor(resource);
		this.id = id;
		
		name = person.getName();
		roomButtonText = person.getRoomNr();
		roomButtonId = person.getRoomId();
		
		if(!person.getMail().equals("")){
		mailButtonText = person.getMail();
		mailButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
		}else{
			mailButton.getElement().getStyle().setDisplay(Display.NONE);
			mailButton.hideLabel();
		}
		
		if(!person.getPhoneNr().equals("")){
			telephoneButtonText = person.getPhoneNr();
			telephoneButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				telephoneButton.getElement().getStyle().setDisplay(Display.NONE);
				telephoneButton.hideLabel();
			}
		courseOfStudyInfo.setText(person.getDepartment());
		refresh();
		loadEvents();
	}


	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getResourceType() {
		return RoomSearchPage.ResourceType ;
	}


	@Override
	public void handleClickCount(String targetId) {
		//Do nothing
	}


}
