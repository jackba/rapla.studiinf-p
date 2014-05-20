package org.rapla.plugin.studiinf.client.pages;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.RessourceButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

/**
 * 
 * @author Team StudiInf
 * 
 * Page for displaying course details
 */
public class DetailPageCourse extends AbstractDetailPage implements SearchPageInterface{
	
	private String id;
	
	private FlowPanel infoPanel;
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private Label infoLabel;
	private Label appointmentLabel;
	private ResultTable infos;
	
	private ResultButtonWithLabel courseOfStudyButton;
	private RessourceButtonWithLabel roomButton;
	private ResultButtonWithLabel raplaButton;
	
	private String nameButtonText;
	private String courseOfStudyButtonText;
	private String roomButtonText;

	@Override
	public void init(){
		super.init();
		
		infoPanel = new FlowPanel();
		middlePanel = new FlowPanel();
		infoLabel = new Label(Studiinf.i18n.information());
		appointmentLabel = new Label(Studiinf.i18n.nextAppointments());
		infos = new ResultTable(bottomPanel, 2, 4);
		infos.getColumnFormatter().setWidth(0, "25%");
		infos.getColumnFormatter().setWidth(1, "75%");
		infos.setWidth("100%");
		
		courseOfStudyButton = new ResultButtonWithLabel(new ResultButton(courseOfStudyButtonText, null, null, FontIcon.Courses, false), new Label(Studiinf.i18n.courseOfStudy()));
		roomButton = new RessourceButtonWithLabel(new ResultButton( FontIcon.Rooms,roomButtonText, Navigation.roomDetail,  null, true), new Label(Studiinf.i18n.room()));
		raplaButton = new ResultButtonWithLabel(new ResultButton(Studiinf.i18n.linkRapla(), Navigation.raplaCourseLink, id, FontIcon.Calendar, true), new Label(Studiinf.i18n.linkRapla()));

		infoPanel.setStyleName("infoPanel");
		middlePanel.setStyleName("courseMiddlePanel");
		infoLabel.setStyleName("infoLabel");
		appointmentLabel.setStyleName("courseAppointmentLabel");
		eventPanel.setStyleName("courseLecturesTable");
		
		infos.clearResults();
		infos.addResult(courseOfStudyButton);
		infos.addResult(roomButton);
		infos.addResult(raplaButton);
		infos.refresh();
		
		infoPanel.add(infoLabel);
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		middlePanel.add(eventPanel);
		middlePanel.add(appointmentLabel);
		
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
		return Studiinf.i18n.course() + "" + nameButtonText;
	}

	@Override
	protected void refresh() {
		super.refresh();
		courseOfStudyButton.setText(courseOfStudyButtonText);
		raplaButton.setTargetId(id);
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}

	public void showRaplaLinks(boolean show){
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			appointmentLabel.setText(Studiinf.i18n.noAppointments());
			raplaButton.hideLabelAndFooterButton();
		}
	}

	
	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		CourseDescriptor cd = new CourseDescriptor(resource);
		
		this.id = id;
		nameButtonText = cd.getName();
		cd.getPicture();
		cd.getYear();
		courseOfStudyButtonText = cd.getDepartment();
		courseOfStudyButton.setText(cd.getDepartment());
		roomButton.setTargetId(cd.getRoomId());
		roomButton.setText(cd.getRoomNr());
		
		if(!cd.getDepartment().equals("")){
			courseOfStudyButtonText = cd.getDepartment();
			courseOfStudyButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				courseOfStudyButton.getElement().getStyle().setDisplay(Display.NONE);
				courseOfStudyButton.hideLabelAndFooterButton();
			}
		if(cd.getRoomNr()!=null && !cd.getRoomNr().equals("")){
			roomButtonText = cd.getRoomNr();
			roomButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				roomButton.getElement().getStyle().setDisplay(Display.NONE);
				roomButton.hideLabelAndFooterButton();	
			}
		
		refresh();	
		loadEvents();	
	}

	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getResourceType() {
		return SearchPageRoom.ResourceType ;
	}


	@Override
	public void handleClickCount(String targetId) {
		//Do nothing
	}
	
}
