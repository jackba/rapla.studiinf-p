package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.search.PersonDescribtor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class DetailPagePerson extends AbstractDetailPage {

	private FlowPanel personInfoPanel = new FlowPanel();
	private FlowPanel bottomPanel = new FlowPanel();
	private FlowPanel middlePanel = new FlowPanel();
	private Label personInfoLabel = new Label("Information");
	private Grid infos = new Grid(4, 1);
	private Grid courses = new Grid(2, 3);
	private Label appointmentLabel = new Label("Anstehende Termine");
	private Label courseLabel = new Label("Kurse");
	private String name;
	
	private NavigationIconButton roomNrBtn;
	private IconButton mailBtn;
	private IconButton telephoneBtn;
	
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	
	@Override
	public boolean hasDefaultQrBox(){
		return false;
	}
	
	
	@Override
	public void init(){
		super.init();
		
		//QRBox qrBox = getQrBox();
		//this.remove(qrBox);
		qrBox.setStyleName("personQRBox");
		
		personInfoPanel.setStyleName("personInfoPanel");
		personInfoLabel.setStyleName("personInfoLabel");
		bottomPanel.setStyleName("personBottomPanel");
		middlePanel.setStyleName("personMiddlePanel");
		infos.setStyleName("personInfos");
		courses.setStyleName("personCourses");
		appointmentLabel.setStyleName("personAppointmentLabel");
		courseLabel.setStyleName("personCourseLabel");
		
		final String roomNrImgString = new String("img/Raum.svg");
		final String mailImgString = new String("img/Telefon.svg");
		final String telephonImgString = new String("img/Telefon.svg");
		final String extraInfoImgString = new String("img/ZusätzlicheInformationen.svg");
		final String noPersonImgString = new String("img/Personen.svg");
		
		Image roomNrImg = new Image(roomNrImgString);
		Image mailImg = new Image(mailImgString);
		Image telephonImg = new Image(telephonImgString);
		Image extraInfoImg = new Image(extraInfoImgString);
		Image noPersonImg = new Image(noPersonImgString);
		
		roomNrBtn = new NavigationIconButton("D 935", roomNrImg,Navigation.roomDetail,"935");
		mailBtn = new IconButton("test@mail.de", mailImg);
		telephoneBtn = new IconButton("0122- 5675765", telephonImg);
		Widget extraInfosBtn = new IconButton("Zusätzliche Infos", extraInfoImg);
		
		infos.setWidget(0, 0, roomNrBtn);
		infos.setWidget(1, 0, mailBtn);
		infos.setWidget(2, 0, telephoneBtn);
		infos.setWidget(3, 0, extraInfosBtn);
		
		final String courseImgString = new String("img/Kurse.svg");
		Image firstCourseImg = new Image(courseImgString);
		Image secondCourseImg = new Image(courseImgString);
		Image thirdCourseImg = new Image(courseImgString);
		Image fourthCourseImg = new Image(courseImgString);
		Image fifthCourseImg = new Image(courseImgString);
		Image sixthCourseImg = new Image(courseImgString);
		
		IconButton firstCourse = new IconButton("Name 1", firstCourseImg);
		IconButton secondCourse = new IconButton("Name 2", secondCourseImg);
		IconButton thirdCourse = new IconButton("Name 3", thirdCourseImg);
		IconButton fourthCourse = new IconButton("Name 4", fourthCourseImg);
		IconButton fifthCourse = new IconButton("Name 5", fifthCourseImg);
		IconButton sixthCourse = new IconButton("Name 6", sixthCourseImg);
		
		courses.setWidget(0, 0, firstCourse);
		courses.setWidget(0, 1, secondCourse);
		courses.setWidget(0, 2, thirdCourse);
		courses.setWidget(1, 0, fourthCourse);
		courses.setWidget(1, 1, fifthCourse);
		courses.setWidget(1, 2, sixthCourse); 
		
		noPersonImg.setStyleName("personDetailPicture");
		
		personInfoPanel.add(personInfoLabel);
		personInfoPanel.add(noPersonImg);
		personInfoPanel.add(infos);
		

		middlePanel.add(appointmentLabel);
		middlePanel.add(courseLabel);
		middlePanel.add(courses);
		
		Image roomNrImg2 = new Image(roomNrImgString);
		Image extraInfoImg2 = new Image(extraInfoImgString);
		
		Widget showRoomBtn = new NavigationIconButton("D 935", roomNrImg2,Navigation.roomDetail,"935");
		Widget showextraInfosBtn = new IconButton("Extrainfos anzeigen", extraInfoImg2);
		
		bottomPanel.add(showRoomBtn);
		bottomPanel.add(showextraInfosBtn);
		

		this.add(personInfoPanel);
		this.add(bottomPanel);
		this.add(middlePanel);
		this.add(qrBox);
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

	@Override
	protected void handleId(String id) {
		ServiceProvider.getResource(id, new AsyncCallback<ResourceDetail>() {
			
			@Override
			public void onSuccess(ResourceDetail arg0) {
				//Window.alert(arg0.getKeys().toString());
				PersonDescribtor person = new PersonDescribtor(arg0);
				
				name = person.getName();
				roomNrBtn.setText(person.getRoomNr());
				if(!person.getMail().equals("")){
				mailBtn.setText(person.getMail());
				mailBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
				}else{
					mailBtn.getElement().getStyle().setDisplay(Display.NONE);
				}
				
				if(!person.getPhoneNr().equals("")){
					telephoneBtn.setText(person.getPhoneNr());
					telephoneBtn.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
					}else{
						telephoneBtn.getElement().getStyle().setDisplay(Display.NONE);
					}
				
				refresh();
			}
			
			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	@Override
	protected void refresh() {
		super.refresh();
		
	}

}
