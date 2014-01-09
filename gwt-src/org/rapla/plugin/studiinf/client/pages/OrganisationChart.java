package org.rapla.plugin.studiinf.client.pages;


import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;

public class OrganisationChart extends AbstractDetailPage  {
	
	private Grid bottomOrganigram = new Grid(2, 4);
	
	@Override
	public void init(){
		super.init();
		
		
		Button b = new Button();
	}
	
	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		// TODO Auto-generated method stub
		
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
