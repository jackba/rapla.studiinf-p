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
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;
import org.rapla.plugin.studiinf.client.ui.RessourceButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DetailPagePoi extends AbstractDetailPage {

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
	
	private Image noNavigationImg = new Image(IconProvider.MISSING_MAP);
	private Image wayDescriptionImg = new Image(IconProvider.MISSING_MAP);
	
	private String id;
	private String roomButtonId;

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
			wayDescriptionImg = new Image(Picture.getImageURL(nameButtonText));
			wayDescriptionImg.setStyleName("navigationPicture");
			this.add(wayDescriptionImg);
		}
		else{
			noNavigationImg.setStyleName("navigationPicture");
			this.add(noNavigationImg);
		}
		
		roomButton = new RessourceButtonWithLabel(new ResultButton( IconProvider.Rooms,roomButtonText, Navigation.roomDetail, null,true), new Label("hj"));
		rowOneButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Additional_Information, rowOneButtonText,  null, null, false), new Label("hj"));
		rowTwoButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Additional_Information, rowTwoButtonText, null, null, false), new Label("hj"));
		raplaButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Calendar,Studiinf.i18n.linkRapla(),  Navigation.raplaRoomLink, id, true), new Label("hj"));
		
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
		roomButton.setTargetId(roomButtonId);
		roomButton.setText(roomButtonText);
		rowOneButton.setText(rowOneButtonText);
		rowTwoButton.setText(rowTwoButtonText);
		raplaButton.setTargetId(id);
		
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
//		NavButton f = raplaButton.getFooterButton();
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
//			f.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
//			f.getElement().getStyle().setDisplay(Display.NONE);
			raplaButton.hideFooterButton();
			raplaButton.hideLabel();
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
				roomButton.hideLabel();
			}
		if (!pd.getRowOne().equals("")){
			rowOneButtonText = pd.getRowOne();
			rowOneButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				rowOneButton.getElement().getStyle().setDisplay(Display.NONE);
				rowOneButton.hideLabel();
			}
		if (!pd.getRowTwo().equals("")){
			rowTwoButtonText = pd.getRowTwo();
			rowTwoButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				rowTwoButton.getElement().getStyle().setDisplay(Display.NONE);
				rowTwoButton.hideLabel();
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
