package org.rapla.plugin.studiinf.client.pages;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.search.CourseDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;

import com.google.gwt.dom.client.Style.Display;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DetailPageCourse extends AbstractDetailPage {
	
	private String courseName;
	private String studyName;
	private String profName;
	private String roomName;
	
	private FlowPanel infoPanel;
	private FlowPanel bottomPanel;
	private Label infoLabel;
	private Grid infos;
	
	private IconButton name;
	private IconButton study;
	private NavigationIconButton prof;
	private NavigationIconButton room;
	private NavigationIconButton events;
	private NavigationIconButton prof2;
	private NavigationIconButton room2;
	private NavigationIconButton events2;
	
	@Override
	public void init(){
		super.init();
		
		infoPanel = new FlowPanel();
		bottomPanel = new FlowPanel();
		infoLabel = new Label("Information");
		infos = new Grid(5, 1);
		
		name = new IconButton(courseName, new Image(IconProvider.COURSE));
		study = new IconButton(studyName, new Image(IconProvider.COURSE));
		prof = new NavigationIconButton(profName, new Image(IconProvider.PERSONS), this);
		room = new NavigationIconButton(roomName, new Image(IconProvider.PERSONS), this);
		events = new NavigationIconButton("Link Rapla", new Image(IconProvider.PERSONS), this);
		
		prof2 = new NavigationIconButton(profName, new Image(IconProvider.PERSONS), this);
		room2 = new NavigationIconButton(roomName, new Image(IconProvider.PERSONS), this);
		events2 = new NavigationIconButton("Link Rapla", new Image(IconProvider.PERSONS), this);
		
		prof2.setStyleName("courseProf");
		room2.setStyleName("courseRoom");
		events2.setStyleName("courseEvents");
		
		infoPanel.setStyleName("courseInfoPanel");
		bottomPanel.setStyleName("courseBottomPanel");
		infoLabel.setStyleName("courseInfoLabel");
		infos.setStyleName("courseInfos");
		
		infos.setWidget(0, 0, name);
		infos.setWidget(1, 0, study);
		infos.setWidget(2, 0, prof);
		infos.setWidget(3, 0, room);
		infos.setWidget(4, 0, events);
		
		infoPanel.add(infoLabel);
		infoPanel.add(infoLabel);
		infoPanel.add(infos);
		
		bottomPanel.add(prof2);
		bottomPanel.add(room2);
		bottomPanel.add(events2);
		
		this.add(infoPanel);
		this.add(bottomPanel);
	}

	@Override
	public String getHistoryKey() {
		return "courseDetail";
	}

	@Override
	public String getTitle() {
		if(courseName == null){
			courseName = "";
		}
		return courseName;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
		name.setText(courseName);
		study.setText(studyName);
		prof.setText(profName);
		room.setText(roomName);

	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		CourseDescriptor cd = new CourseDescriptor(resource);
		
		courseName = cd.getName();
		cd.getPicture();
		
		cd.getYear();
		
		if(!cd.getDepartment().equals("")){
			studyName = cd.getDepartment();
			study.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				study.getElement().getStyle().setDisplay(Display.NONE);
			}
		if(!cd.getRoomNr().equals("")){
			roomName = cd.getRoomNr();
			room.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			room2.getElement().getStyle().setDisplay(Display.INLINE_BLOCK);
			}else{
				room.getElement().getStyle().setDisplay(Display.NONE);
				room2.getElement().getStyle().setDisplay(Display.NONE);
			}
		
		refresh();
		
	}

}
