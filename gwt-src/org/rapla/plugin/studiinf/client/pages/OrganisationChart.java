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

/**
 * 
 * @author Team StudiInf
 * 
 * Page to display organigramm
 */
public abstract class OrganisationChart extends AbstractDetailPage  implements SearchPageInterface {
	
	protected AccessibilityRow access = new AccessibilityRow();
	
	protected ResultTable organigram = new ResultTable(access, 1, 14);
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
		
		
		footer.setTargetPage(this);
		
	}

	protected void handleRessource(String id, List <CategoryDescription> resources) {
		showOrganigramLevels(resources);
	}
	
	@Override
	protected void handleId(final String id){
		this.id = id;
		footer.setTargetId(backInHistory());
		String newId = resolveHistory();
		
		if (newId.equals("null")){
			newId = null;
		}
		categoryId = newId;
		ServiceProvider.getOrganigram(newId, new AsyncCallback<List<CategoryDescription>>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<CategoryDescription> result) {
				// TODO Auto-generated method stub
				handleRessource(id, result);
				
			}
			
		});
	}
	
	public String resolveHistory(){
		String last = this.id +"";
		return last.substring(last.lastIndexOf(">")+1);
	}
	public String backInHistory(){
		String last = this.id +"";
		last = last.substring(0, last.lastIndexOf(">"));
		if(last.length() <= 0 || this.id.equals("null")){
			last = null;
		}
		return last;
	}
	public String addHistory(String addon){
		return this.id + ">" + addon;
	}
	
	/**
	 * Shows categories as an organigram consisting of buttons
	 * @param categories List of resource categories
	 */
	public void showOrganigramLevels(List <CategoryDescription> categories){
		if(categories.size() <= 0){
			new CourseOrganigramSearch(categoryId, this);
		}else{
			organigram.clearResults();	
			for (CategoryDescription category : categories){
				OrganigramButton orgButton = new OrganigramButton(category.getName(), this,  addHistory(category.getId()));
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
		// TODO Auto-generated method stub
	}
	@Override
	protected void showRaplaLinks(boolean b) {
		// do nothing always true;
	}

}
