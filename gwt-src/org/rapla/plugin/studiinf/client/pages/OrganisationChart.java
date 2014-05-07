package org.rapla.plugin.studiinf.client.pages;


import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CategoryDescriptor;
import org.rapla.plugin.studiinf.client.search.Organigram;

public class OrganisationChart extends AbstractPage  {
	
//	private Grid bottomOrganigram = new Grid(2, 4);
	
	@Override
	public void init(){
		super.init();
		
		Organigram org = new Organigram();
		org.getOrganigram(null);
		org.getOrganigram("2");
		org.getOrganigram("Allgemein");
		
//		Button b = new Button();
	}

	//TODO will not be called
	// TODO not a single resource --> list of resources
	protected void handleRessource(String id, List <ResourceDetail> resources) {
		
		// über Liste iterieren
		ResourceDetail resource = resources.get(0);
		CategoryDescriptor cd = new CategoryDescriptor(resource);

		
		// add button labels
		
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
