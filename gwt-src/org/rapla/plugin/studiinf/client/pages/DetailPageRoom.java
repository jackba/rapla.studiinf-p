package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.LocalStorage;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Picture;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DetailPageRoom extends AbstractDetailPage {

	private FlowPanel infoPanel = new FlowPanel();
	private FlowPanel roomPanel = new FlowPanel();
	private Label infoLabel = new Label("Information");
	
	private Grid infos = new Grid(4, 1);
	
	private String nameButtonText;
	private String typeButtonText;
	private String courseOfStudyButtonText;
	
	private IconButton nameButton;
	private IconButton typeButton;
	private IconButton courseOfStudyButton;
	private NavigationIconButton raplaButton;
	private NavigationIconButton raplaButton2;
	
	private Image noNavigationImg = new Image(IconProvider.MISSING_MAP);
	private Image wayDescriptionImg = new Image(IconProvider.MISSING_MAP);
	
	private String id;
	
	public LocalStorage roomLS = new LocalStorage();
	
	

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		roomPanel.setStyleName("roomPanel");
		infoLabel.setStyleName("infoLabel");
		infos.setStyleName("infos");
		
		Image roomNameImg = new Image(IconProvider.ROOMS);
		Image roomTypeImg = new Image(IconProvider.ROOM_TYPE);
		Image studyImg = new Image(IconProvider.COURSE);
		Image roomImg = new Image(IconProvider.CALENDAR);
		
		
		if (nameButtonText.equals("A051") || nameButtonText.equals("A052")  || nameButtonText.equals("LA051") || nameButtonText.equals("LA052")  || nameButtonText.equals("RA051") || nameButtonText.equals("RA052")){
			wayDescriptionImg = new Image(Picture.getImageURL(nameButtonText));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
		
		nameButton = new IconButton(nameButtonText, roomNameImg);
		typeButton = new IconButton(typeButtonText, roomTypeImg);
		courseOfStudyButton = new IconButton(courseOfStudyButtonText, studyImg);
		raplaButton = new NavigationIconButton(Studiinf.i18n.linkRapla(), roomImg, Navigation.raplaRoomLink, id);
		
		infos.setWidget(0, 0, nameButton);
		infos.setWidget(1, 0, typeButton);
		infos.setWidget(2, 0, courseOfStudyButton);
		infos.setWidget(3, 0, raplaButton);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
			
		Image occupancyImg = new Image(IconProvider.CALENDAR);
		
		raplaButton2 = new NavigationIconButton(Studiinf.i18n.linkRapla(), occupancyImg, Navigation.raplaRoomLink, id);
		roomPanel.add(raplaButton2);
		
		this.add(infoPanel);
		this.add(roomPanel);
		
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
		return nameButtonText;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
		nameButton.setText(nameButtonText);
		typeButton.setText(typeButtonText);
		courseOfStudyButton.setText(courseOfStudyButtonText);
		raplaButton.setTargetId(id);
		raplaButton2.setTargetId(id);
		
		this.remove(wayDescriptionImg);
		this.remove(noNavigationImg);
		
		if (nameButtonText.equals("A051") || nameButtonText.equals("A052")  || nameButtonText.equals("LA051") || nameButtonText.equals("LA052")  || nameButtonText.equals("RA051") || nameButtonText.equals("RA052")){
			wayDescriptionImg = new Image(Picture.getImageURL(nameButtonText));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
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
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!rd.getRoomType().equals("")){
			typeButtonText = rd.getRoomType();
			typeButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				typeButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!rd.getDepartment().equals("")){
			courseOfStudyButtonText = rd.getDepartment();
			courseOfStudyButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				courseOfStudyButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		
		roomLS.writeStorage(id);
		roomLS.fillMap();
//		roomLS.readStorage(id);
		//System.out.println("Rooms: "+roomNumber+" "+roomLS.readStorage(roomNumber)+" "+id);
//		"localStorage: " + targetID + " " + count
		Window.alert(roomLS.readStorage(id));
//		Window.alert(rd.getPicture());
		
//		Window.alert(rd.getPicture());
		refresh();
		
	}

}
