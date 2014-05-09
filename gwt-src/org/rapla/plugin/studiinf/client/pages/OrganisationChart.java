package org.rapla.plugin.studiinf.client.pages;


import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.NavButton;
import org.rapla.plugin.studiinf.client.ui.OrganigramButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public class OrganisationChart extends AbstractDetailPage  {
	
	AccessibilityRow access = new AccessibilityRow();
	
	ResultTable organigram = new ResultTable(access, 1, 8);
	List <CategoryDescription> testCategoryList;
	public String helpId;
	
		
	@Override
	public void init(){
		super.init();
		FlowPanel infoPanel = new FlowPanel();
		infoPanel.setStyleName("infoPanel");
		
		organigram.setStyleName("infos");			

		
		infoPanel.add(organigram);
		this.add(infoPanel);
		this.add(access);
		
	}

	//TODO will not be called
	protected void handleRessource(String id, List <CategoryDescription> resources) {
		showOrganigramLevels(resources);
	}
	
	@Override
	protected void handleId(final String id){
		String newId = id;
		if (id.equals("null")){
			newId = null;
		}
		ServiceProvider.getOrganigram(newId, new AsyncCallback<List<CategoryDescription>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("fail :("+ caught.toString());
			}

			@Override
			public void onSuccess(List<CategoryDescription> result) {
				// TODO Auto-generated method stub
				Window.alert(":)");
				handleRessource(id, result);
				
			}
			
		});
	}
	
	public void showOrganigramLevels(List <CategoryDescription> categories){
		int count = 0;
		for (CategoryDescription category : categories){
			NavButton org = new OrganigramButton(category.getName(), Navigation.organisationChart, category.getId());
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

	@Override
	public boolean hasDefaultQrBox() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		// TODO Auto-generated method stub
		
	}

}
