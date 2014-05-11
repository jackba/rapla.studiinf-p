package org.rapla.plugin.studiinf.client.pages;


import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseOrganigramSearch;
import org.rapla.plugin.studiinf.client.ui.AccessibilityRow;
import org.rapla.plugin.studiinf.client.ui.OrganigramButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

//import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;

public abstract class OrganisationChart extends AbstractDetailPage  implements SearchPageInterface {
	
	protected AccessibilityRow access = new AccessibilityRow();
	
	protected ResultTable organigram = new ResultTable(access, 1, 8);
	protected List <CategoryDescription> testCategoryList;
	public String helpId;
	
	private String categoryId;
		
	@Override
	public void init(){
		super.init();
		FlowPanel infoPanel = new FlowPanel();
		infoPanel.setStyleName("infoPanel");
		
		organigram.addStyleName("infos");			
		
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
		categoryId = newId;
		ServiceProvider.getOrganigram(newId, new AsyncCallback<List<CategoryDescription>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
//				Window.alert("fail :("+ caught.toString());
			}

			@Override
			public void onSuccess(List<CategoryDescription> result) {
				// TODO Auto-generated method stub
				handleRessource(id, result);
				
			}
			
		});
	}
	
	public void showOrganigramLevels(List <CategoryDescription> categories){
		if(categories.size() <= 0){
			new CourseOrganigramSearch(categoryId, this);
		}else{
			organigram.clearResults();	
			for (CategoryDescription category : categories){
				OrganigramButton orgButton = new OrganigramButton(category.getName(), this, category.getId());
				organigram.addResult(orgButton);
			}
			organigram.refresh();
		}
		
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
		return false;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleClickCount(String targetId) {
		
	}

}
