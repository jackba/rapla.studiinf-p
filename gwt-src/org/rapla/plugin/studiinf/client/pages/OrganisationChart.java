package org.rapla.plugin.studiinf.client.pages;


import java.util.ArrayList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CategoryDescriptor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;

public class OrganisationChart extends AbstractPage  {
	
	Grid organigram = new Grid(4, 1);
	List <CategoryDescriptor> testCategoryList;
	public String helpId;
		
	@Override
	public void init(){
		super.init();
		FlowPanel infoPanel = new FlowPanel();
		infoPanel.setStyleName("infoPanel");
		
		organigram.setStyleName("infos");		
		generateTestData();		
		showOrganigramLevels(testCategoryList);
		
		infoPanel.add(organigram);
		this.add(infoPanel);
		
	}
	
	public void generateTestData(){
		testCategoryList = new ArrayList<CategoryDescriptor>();
		CategoryDescriptor one = new CategoryDescriptor("Erster", "11");
		CategoryDescriptor two = new CategoryDescriptor("Zweiter", "22");
		
		testCategoryList.add(one);
		testCategoryList.add(two);
	}

	//TODO will not be called
	protected void handleRessource(String id, List <ResourceDetail> resources) {
		List <CategoryDescriptor> cdList = new ArrayList<CategoryDescriptor>();
		
		for (ResourceDetail resource : resources)
		{
//		ResourceDetail resource = resources.get(0);
		CategoryDescriptor cd = new CategoryDescriptor(resource);
		cdList.add(cd);		
		}		
	}
	
	public void showOrganigramLevels(List <CategoryDescriptor> categories){
		int count = 0;
		for (CategoryDescriptor category : categories){
			Button org = new Button(category.getName());
			helpId = category.getId();
			org.addClickHandler(new ClickHandler() {
				
				@Override
				public void onClick(ClickEvent event) {
					Window.alert("has been clicked");		
					// TODO: get next level by ID
				}
			});
			organigram.setWidget(count, 0, org);
			count++;
		}
	}

	@Override
	public String getHistoryKey() {
		return "org";
	}

	@Override
	public String getTitle() {
		return Studiinf.i18n.organigram();
	}
	
	@Override
	protected void refresh() {
		super.refresh();
	}

}
