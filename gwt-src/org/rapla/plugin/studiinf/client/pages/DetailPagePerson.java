package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.PopUpImagePanel;
import org.rapla.plugin.studiinf.client.ui.RessourceButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * 
 * @author Team StudiInf
 * 
 * Page for displaying person details
 */
public class DetailPagePerson extends AbstractDetailPage implements ErrorHandler, SearchPageInterface {
	
	private String courseOfStudy;
	private String id;

	private FlowPanel personInfoPanel = new FlowPanel();
	private AccessibilityRow bottomPanel = new AccessibilityRow();
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
	private Image personImg = new Image(FontIcon.MISSING_PERSON_PNG);
	private String personPictureURL = FontIcon.MISSING_PERSON_PNG;
	

	
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
		
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseOfStudyLabel.setStyleName("personCourseOfStudyLabel");
		courseOfStudyInfo.setStyleName("personCourseOfStudyInfo");
		
		eventPanel.setStyleName("lecturesTable");
		
		roomButton = new RessourceButtonWithLabel(new ResultButton(FontIcon.Rooms,roomButtonText, Navigation.roomDetail,null,true), new Label(Studiinf.i18n.roomLabel()));
		mailButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Email, mailButtonText, null, null, false), new Label(Studiinf.i18n.mail()));
		telephoneButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Phone, telephoneButtonText, null, null, false), new Label(Studiinf.i18n.telephone()));
		extraInfosButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Additional_Information,Studiinf.i18n.extraInfos(), Navigation.extraInfo, id, true), new Label(""));
		raplaButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Calendar,Studiinf.i18n.linkRapla(), Navigation.raplaPersonLink, id, true), new Label(""));		
		
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
		
		personImg = new Image(personPictureURL);
		personImg.setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(personImg);
		personInfoPanel.add(infos);
		
		middlePanel.add(courseOfStudyLabel);
		middlePanel.add(courseOfStudyInfo);
		middlePanel.add(appointmentLabel);
		middlePanel.add(eventPanel);
		
		this.add(personInfoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
		
		if(DisplayMode.isMobile()){
			infos.setPaginationPlaceholder(false);
			personInfoPanel.addStyleName("mobile");
			middlePanel.addStyleName("mobile");

			appointmentLabel.addStyleName("mobile");
			courseOfStudyLabel.addStyleName("mobile");
			courseOfStudyInfo.addStyleName("mobile");
		}
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
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			raplaButton.hideLabelAndFooterButton();
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
		
		personInfoPanel.remove(personImg);
		
		personImg = new Image(personPictureURL);
		personImg.addErrorHandler(this);
		personImg.setVisible(true);
		personImg.setStyleName("personDetailPicture");	
		
		if(DisplayMode.isMobile()){
			personImg.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					new PopUpImagePanel(personImg.getUrl()).show();
				}
			});	
		}
		
		personInfoPanel.add(personImg);
		
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
			mailButton.hideLabelAndFooterButton();
		}
		
		if(!person.getPhoneNr().equals("")){
			telephoneButtonText = person.getPhoneNr();
			telephoneButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				telephoneButton.getElement().getStyle().setDisplay(Display.NONE);
				telephoneButton.hideLabelAndFooterButton();
			}
		courseOfStudyInfo.setText(person.getDepartment());
		loadEvents();
		if(!person.getPicture().equals(""))
			{
				personPictureURL = person.getPicture();
			}
			else{
				personPictureURL = FontIcon.MISSING_PERSON_PNG;
			}
		refresh();
	}


	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		//nothing to do
		
	}


	@Override
	public String getResourceType() {
		return SearchPageRoom.ResourceType ;
	}


	@Override
	public void handleClickCount(String targetId) {
		//Do nothing
	}

	@Override
	public void onError(ErrorEvent event) {
		if(!personImg.getUrl().endsWith(FontIcon.MISSING_PERSON_PNG)){
			personImg.setUrl(FontIcon.MISSING_PERSON_PNG);
		}
		
	}
	
	
}
