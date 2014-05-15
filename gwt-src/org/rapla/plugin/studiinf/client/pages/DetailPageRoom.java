package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Picture;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.RoomDescriptor;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultObject;
import org.rapla.plugin.studiinf.client.ui.ResultButtonWithLabel;
import org.rapla.plugin.studiinf.client.ui.ResultTable;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DetailPageRoom extends AbstractDetailPage {

	private FlowPanel infoPanel = new FlowPanel();
	private AccessibilityRow bottomPanel = new AccessibilityRow();
	private Label infoLabel = new Label(Studiinf.i18n.information());
	private ResultTable infos;
	
	private String nameButtonText;
	private String typeButtonText;
	private String courseOfStudyButtonText;
	
//	private ResultButton nameButton;
	private ResultButtonWithLabel typeButton;
	private ResultButtonWithLabel courseOfStudyButton;
	private ResultButtonWithLabel raplaButton;
	
	private Image noNavigationImg = new Image(IconProvider.MISSING_MAP);
	private Image wayDescriptionImg = new Image(IconProvider.MISSING_MAP);
	private String id;
	
	@Override
	public void init(){
		super.init();
		infoPanel.setStyleName("infoPanel");
		infoLabel.setStyleName("infoLabel");
		infos = new ResultTable(bottomPanel, 2, 4);
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

//		nameButton = new ResultButton(IconProvider.Rooms, nameButtonText, null, null, false);
		typeButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Rooms, typeButtonText, null, null, false), new Label(Studiinf.i18n.type())); 
		courseOfStudyButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Courses, courseOfStudyButtonText, null, null, false), new Label(Studiinf.i18n.courseOfStudy()));
		raplaButton = new ResultButtonWithLabel(new ResultButton(IconProvider.Calendar,Studiinf.i18n.linkRapla(), Navigation.raplaRoomLink, id, true), new Label(Studiinf.i18n.linkRapla()));
		
		raplaButton.setSize(0.8);
//		nameButton.setSize(0.8);
		typeButton.setSize(0.8);
		courseOfStudyButton.setSize(0.8);
		
		infos.clearResults();
//		infos.addResult(nameButton);
		infos.addResult(typeButton);
		infos.addResult(courseOfStudyButton);
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
		NavButton f = raplaButton.getFooterButton();
		if (show == true){
			raplaButton.getElement().getStyle().setDisplay(Display.INLINE);
//			raplaButton.setShowFooter(true);
			f.getElement().getStyle().setDisplay(Display.INLINE);
		} else {
			raplaButton.getElement().getStyle().setDisplay(Display.NONE);
//			raplaButton.setShowFooter(false);
			f.getElement().getStyle().setDisplay(Display.NONE);
		}
	}	
	

	@Override
	protected void refresh() {
		super.refresh();
//		nameButton.setText(nameButtonText);
		typeButton.setText(typeButtonText);
		courseOfStudyButton.setText(courseOfStudyButtonText);
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
//			nameButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
//				nameButton.getElement().getStyle().setDisplay(Display.NONE);
			}
		if (!rd.getRoomType().equals("")){
			typeButtonText = rd.getRoomType();
			typeButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				typeButton.getElement().getStyle().setDisplay(Display.NONE);
				typeButton.hideLabel();
			}
		if (!rd.getDepartment().equals("")){
			courseOfStudyButtonText = rd.getDepartment();
			courseOfStudyButton.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				courseOfStudyButton.getElement().getStyle().setDisplay(Display.NONE);
				courseOfStudyButton.hideLabel();
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
}
