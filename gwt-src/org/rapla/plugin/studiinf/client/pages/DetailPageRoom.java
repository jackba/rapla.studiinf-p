package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.PopUpImagePanel;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * 
 * @author Team StudiInf
 * 
 * Page for displaying room details
 */
public class DetailPageRoom extends AbstractDetailPage implements ErrorHandler, SearchPageInterface {

	private FlowPanel infoPanel = new FlowPanel();
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private Label infoLabel = new Label(Studiinf.i18n.information());
	private ResultTable infos;
	
	private String nameButtonText;
	private String typeButtonText;
	private String courseOfStudyButtonText;
	
	private ResultButtonWithLabel typeButton;
	private ResultButtonWithLabel courseOfStudyButton;
	private ResultButtonWithLabel raplaButton;
	
	private Image wayDescriptionImg = new Image(FontIcon.MISSING_MAP);
	private String id;
	private String locationPictureURL = FontIcon.MISSING_MAP;
	
	@Override
	public void init(){
		super.init();
		infoPanel.setStyleName("infoPanel");
		infoLabel.setStyleName("infoLabel");
		infos = new ResultTable(bottomPanel, 2, 4);
		infos.getColumnFormatter().setWidth(0, "25%");
		infos.getColumnFormatter().setWidth(1, "75%");
		infos.setWidth("100%");	
		
		wayDescriptionImg = new Image(locationPictureURL);
		wayDescriptionImg.setStyleName("navigationPicture");
		this.add(wayDescriptionImg);
		
		typeButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Rooms, typeButtonText, null, null, false), new Label(Studiinf.i18n.type())); 
		courseOfStudyButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Courses, courseOfStudyButtonText, null, null, false), new Label(Studiinf.i18n.courseOfStudy()));
		raplaButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Calendar,Studiinf.i18n.linkRapla(), Navigation.raplaRoomLink, id, true), new Label(Studiinf.i18n.linkRapla()));
		
		raplaButton.setSize(0.8);
		typeButton.setSize(0.8);
		courseOfStudyButton.setSize(0.8);
		
		infos.clearResults();
		infos.addResult(typeButton);
		infos.addResult(courseOfStudyButton);
		infos.addResult(raplaButton);
		infos.refresh();
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);

	//	qrBox.getElement().getStyle().setProperty("top", "41vh");
		this.add(qrBox);
		this.add(infoPanel);
		this.add(bottomPanel);
	}
	
	@Override
	public String getHistoryKey() {
		return "roomDetail";
	}

	@Override
	public String getTitle() {
		if(nameButtonText == null){
			nameButtonText = "";
		}
		return Studiinf.i18n.room() + " " + nameButtonText;
	}
	
	public void showRaplaLinks(boolean show){
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			raplaButton.hideLabelAndFooterButton();
		}
	}	
	

	@Override
	protected void refresh() {
		super.refresh();
		typeButton.setText(typeButtonText);
		courseOfStudyButton.setText(courseOfStudyButtonText);
		raplaButton.setTargetId(id);
		this.remove(wayDescriptionImg);
		
		wayDescriptionImg = new Image(locationPictureURL);
		wayDescriptionImg.addErrorHandler(this);
		wayDescriptionImg.setVisible(true);
		wayDescriptionImg.setStyleName("navigationPicture");
		
		if(DisplayMode.isMobile()){
			wayDescriptionImg.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					new PopUpImagePanel(wayDescriptionImg.getUrl()).show();
				}
			});	
		}
		
		this.add(wayDescriptionImg);

	}

	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		RoomDescriptor rd = new RoomDescriptor(resource);
		this.id = id;
		
		if (!rd.getRoomNr().equals("")){
			nameButtonText = rd.getRoomNr();
		}
		if (!rd.getRoomType().equals("")){
			typeButtonText = rd.getRoomType();
			typeButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				typeButton.getElement().getStyle().setDisplay(Display.NONE);
				typeButton.hideLabelAndFooterButton();
			}
		if (!rd.getDepartment().equals("")){
			courseOfStudyButtonText = rd.getDepartment();
			courseOfStudyButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				courseOfStudyButton.getElement().getStyle().setDisplay(Display.NONE);
				courseOfStudyButton.hideLabelAndFooterButton();
			}
		if(!rd.getLocation().equals(""))
		{
			locationPictureURL = rd.getLocation();
		}
		else{
			locationPictureURL = FontIcon.MISSING_MAP;
		}
		
		if(rd.getRoomType().equals("Extern"))
		{
			showRaplaLinks(false);
		}
		else
		{
			showRaplaLinks(true);
		}
		refresh();
	}

	@Override
	public void onError(ErrorEvent event) {
		if(!wayDescriptionImg.getUrl().endsWith(FontIcon.MISSING_MAP)){
		wayDescriptionImg.setUrl(FontIcon.MISSING_MAP);
		}
		
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
