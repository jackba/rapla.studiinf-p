package org.rapla.plugin.studiinf.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Picture;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PoiDescriptor;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.RessourceButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class DetailPagePoi extends AbstractDetailPage {

	private FlowPanel infoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private Label infoLabel = new Label(Studiinf.i18n.information());
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	
	private Grid infos = new Grid(4, 1);
	
	private String nameButtonText;
	private String rowOneButtonText;
	private String rowTwoButtonText;
	private String roomButtonText;
	
	private RessourceButton roomButton;
	private RessourceButton roomButton2;
	private NavButton rowOneButton;
	private NavButton rowTwoButton;
	private NavButton raplaButton;
	private NavButton raplaButton2;
	
	private Image noNavigationImg = new Image(IconProvider.MISSING_MAP);
	private Image wayDescriptionImg = new Image(IconProvider.MISSING_MAP);
	
	private String id;
	
	
	

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		bottomPanel.setStyleName("bottomPanel");
		infoLabel.setStyleName("infoLabel");
		infos.setStyleName("infos");
		
		if (nameButtonText.equals("A051") || nameButtonText.equals("A052")  || nameButtonText.equals("LA051") || nameButtonText.equals("LA052")  || nameButtonText.equals("RA051") || nameButtonText.equals("RA052")){
			wayDescriptionImg = new Image(Picture.getImageURL(nameButtonText));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
		
		roomButton = new RessourceButton(roomButtonText, IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		roomButton2 = new RessourceButton(roomButtonText,  IconProvider.Rooms, Navigation.roomDetail,(AbstractSearchPage) Navigation.room);
		rowOneButton = new NavButton(IconProvider.Additional_Information, rowOneButtonText,  null, null);
		rowTwoButton = new NavButton(IconProvider.Additional_Information, rowTwoButtonText, null, null);
		raplaButton = new NavButton(IconProvider.Calendar,Studiinf.i18n.linkRapla(),  Navigation.raplaRoomLink, id);
		raplaButton2 = new NavButton(IconProvider.Calendar,Studiinf.i18n.linkRapla(), Navigation.raplaRoomLink, id);
		
		roomButton2.setStyleName("bottomButton");
		raplaButton2.setStyleName("bottomButton");
		
		raplaButton.setStyleName("resultPanelButton");
		rowOneButton.setStyleName("resultPanelButton");
		rowTwoButton.setStyleName("resultPanelButton");
		roomButton.setStyleName("resultPanelButton");
		
		roomButton.setSize(0.8);
		raplaButton.setSize(0.8);
		roomButton2.setSize(0.8);
		raplaButton2.setSize(0.8);
		rowOneButton.setSize(0.8);
		rowTwoButton.setSize(0.8);
		
		infos.setWidget(0, 0, roomButton);
		infos.setWidget(1, 0, rowOneButton);
		infos.setWidget(2, 0, rowTwoButton);
		infos.setWidget(3, 0, raplaButton);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		bottomPanel.add(raplaButton2);
		bottomPanel.add(roomButton2);
		
		qrBox.getElement().getStyle().setProperty("top", "41vh");
		this.add(qrBox);
		this.add(infoPanel);
		this.add(bottomPanel);
		
	}
	
	
	@Override
	public String getHistoryKey() {
		return "poiDetail";
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
		roomButton.setText(roomButtonText);
		roomButton2.setText(roomButtonText);
		rowOneButton.setText(rowOneButtonText);
		rowTwoButton.setText(rowTwoButtonText);
		raplaButton.setTargetId(id);
		raplaButton2.setTargetId(id);
		
		this.remove(wayDescriptionImg);
		this.remove(noNavigationImg);
		
		if (nameButtonText.equals("A051") || nameButtonText.equals("A052")  || nameButtonText.equals("LA051") || nameButtonText.equals("LA052")  || nameButtonText.equals("RA051") || nameButtonText.equals("RA052") || nameButtonText.equals("Bibliothek")){
			wayDescriptionImg = new Image(Picture.getImageURL(nameButtonText));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
	}
	
	public void showRaplaLinks(boolean show){
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
			raplaButton2.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
			raplaButton2.getElement().getStyle().setDisplay(Display.NONE);
		}

	}


	@Override
	public boolean hasDefaultQrBox() {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		PoiDescriptor pd = new PoiDescriptor(resource);		
		this.id = id;
		
		if (!pd.getName().equals("")){
			nameButtonText = pd.getName();
			}
		if (!pd.getRoomNr().equals("")){
			roomButtonText = pd.getRoomNr();
			roomButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			roomButton2.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				roomButton.getElement().getStyle().setDisplay(Display.NONE);
				roomButton2.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!pd.getRowOne().equals("")){
			rowOneButtonText = pd.getRowOne();
			rowOneButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				rowOneButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!pd.getRowTwo().equals("")){
			rowTwoButtonText = pd.getRowTwo();
			rowTwoButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				rowTwoButton.getElement().getStyle().setDisplay(Display.NONE);
			}
//		
//		roomLS.writeStorage(id);
//		roomLS.fillMap();
//		roomLS.readStorage(id);
		//System.out.println("Rooms: "+roomNumber+" "+roomLS.readStorage(roomNumber)+" "+id);
//		"localStorage: " + targetID + " " + count
//		Window.alert(roomLS.readStorage(id));
//		Window.alert(rd.getPicture());
		
//		Window.alert(rd.getPicture());
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
				List<Event> events = new ArrayList<Event>(arg0);
				if (events.size()>=1) {
					showRaplaLinks(true);
				} else {
					showRaplaLinks(false);
				}
			}
			
		});
		
	}

}
