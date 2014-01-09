package org.rapla.plugin.studiinf.client.pages;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.search.CourseDescriptor;
import org.rapla.plugin.studiinf.client.ui.IconButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class DetailPageCourse extends AbstractDetailPage {
	
	private String courseName;
	private String studyName = "WI";
	private String profName  = "PROF";
	private String roomName  = "23";
	
	private FlowPanel infoPanel;
	private Label infoLabel;
	private Grid infos;
	
	private IconButton name;
	private IconButton study;
	private NavigationIconButton prof;
	private NavigationIconButton room;
	private NavigationIconButton events;
	
	@Override
	public void init(){
		super.init();
		
		infoPanel = new FlowPanel();
		infoLabel = new Label("Information");
		infos = new Grid(5, 1);
		
		name = new IconButton(courseName, new Image(IconProvider.COURSE));
		study = new IconButton(studyName, new Image(IconProvider.COURSE));
		prof = new NavigationIconButton(profName, new Image(IconProvider.PERSONS), this);
		room = new NavigationIconButton(roomName, new Image(IconProvider.PERSONS), this);
		events = new NavigationIconButton("Link Rapla", new Image(IconProvider.PERSONS), this);
		
		infoPanel.setStyleName("courseInfoPanel");
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
		
		
		this.add(infoPanel);
		
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
		courseName = "Kurs "+id;
		CourseDescriptor cd = new CourseDescriptor(resource);
		refresh();
		
	}

}
