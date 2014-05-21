package org.rapla.plugin.studiinf.client.pages;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PoiDescriptor;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.RessourceButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

/**
 * 
 * @author Team StudiInf
 * 
 * Page for displaying POI details
 */
public class DetailPagePoi extends AbstractDetailPage implements ErrorHandler,SearchPageInterface{

	private FlowPanel infoPanel = new FlowPanel();
	private Label infoLabel = new Label(Studiinf.i18n.information());
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private ResultTable infos;
	
	private String nameButtonText;
	private String rowOneButtonText;
	private String rowTwoButtonText;
	private String roomButtonText;
	
	private RessourceButtonWithLabel roomButton;
	private ResultButtonWithLabel rowOneButton;
	private ResultButtonWithLabel rowTwoButton;
	private ResultButtonWithLabel raplaButton;
	
	private Image noNavigationImg = new Image(FontIcon.MISSING_MAP);
	private Image wayDescriptionImg = new Image(FontIcon.MISSING_MAP);
	
	private String id;
	private String roomButtonId;
	private String locationPictureURL;

	public String getLocationPictureURL() {
		if(locationPictureURL == null){
			locationPictureURL = FontIcon.MISSING_MAP;
		}
		return locationPictureURL;
	}

	@Override
	public void init(){
		super.init();
		
		infoPanel.setStyleName("infoPanel");
		infoLabel.setStyleName("infoLabel");
		infos = new ResultTable(bottomPanel, 2, 5);
		infos.getColumnFormatter().setWidth(0, "25%");
		infos.getColumnFormatter().setWidth(1, "75%");
		infos.setWidth("100%");
		
		if (nameButtonText.equals("A051") || nameButtonText.equals("A052")  || nameButtonText.equals("LA051") || nameButtonText.equals("LA052")  || nameButtonText.equals("RA051") || nameButtonText.equals("RA052")){
			//wayDescriptionImg = new Image(Picture.getImageURL(nameButtonText));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
		roomButton = new RessourceButtonWithLabel(new ResultButton( FontIcon.Rooms,roomButtonText, Navigation.roomDetail, null,true), new Label(Studiinf.i18n.room()));
		rowOneButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Additional_Information, rowOneButtonText,  null, null, false), new Label(Studiinf.i18n.extraInfosLabel()));
		rowTwoButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Additional_Information, rowTwoButtonText, null, null, false), new Label(""));
		raplaButton = new ResultButtonWithLabel(new ResultButton(FontIcon.Calendar,Studiinf.i18n.linkRapla(),  Navigation.raplaRoomLink, id, true), new Label(Studiinf.i18n.linkRaplaLabel()));
		
		roomButton.setSize(0.8);
		raplaButton.setSize(0.8);
		rowOneButton.setSize(0.8);
		rowTwoButton.setSize(0.8);

		infos.clearResults();
		infos.addResult(roomButton);
		infos.addResult(rowOneButton);
		infos.addResult(rowTwoButton);
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
		roomButton.setTargetId(roomButtonId);
		roomButton.setText(roomButtonText);
		rowOneButton.setText(rowOneButtonText);
		rowTwoButton.setText(rowTwoButtonText);
		raplaButton.setTargetId(id);
		
		this.remove(noNavigationImg);
		this.remove(wayDescriptionImg);
		
		wayDescriptionImg = new Image(getLocationPictureURL());
		wayDescriptionImg.addErrorHandler(this);
		wayDescriptionImg.setVisible(true);
		wayDescriptionImg.setStyleName("navigationPicture");
		this.add(wayDescriptionImg);
		

		
		
		
		
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
		
		roomButtonId = pd.getRoomId();
		roomButtonText = pd.getRoomNr();
		if (!pd.getRoomNr().equals("")){
			
			roomButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				roomButton.getElement().getStyle().setDisplay(Display.NONE);
				roomButton.hideLabelAndFooterButton();
			}
		if (!pd.getRowOne().equals("")){
			rowOneButtonText = pd.getRowOne();
			rowOneButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				rowOneButton.getElement().getStyle().setDisplay(Display.NONE);
				rowOneButton.hideLabelAndFooterButton();
			}
		if (!pd.getRowTwo().equals("")){
			rowTwoButtonText = pd.getRowTwo();
			rowTwoButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				rowTwoButton.getElement().getStyle().setDisplay(Display.NONE);
				rowTwoButton.hideLabelAndFooterButton();
			}
		locationPictureURL = FontIcon.MISSING_MAP;
		ServiceProvider.getResource(pd.getRoomId(), new AsyncCallback<ResourceDetail>() {
				@Override
				public void onSuccess(ResourceDetail arg0) {
					RoomDescriptor rd = new RoomDescriptor(arg0);
					if(!rd.getLocation().equals("")){
						locationPictureURL = rd.getLocation();
					}else{
						locationPictureURL = FontIcon.MISSING_MAP;
					}
					refresh();
				}
	
				@Override
				public void onFailure(Throwable arg0) {
					locationPictureURL = FontIcon.MISSING_MAP;
					refresh();
				}
		});
		
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

	@Override
	public void onError(ErrorEvent event) {
		if(!wayDescriptionImg.getUrl().endsWith(FontIcon.MISSING_MAP)){
			wayDescriptionImg.setUrl(FontIcon.MISSING_MAP);
		}
	}


}
