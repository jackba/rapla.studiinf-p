package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.Studiinf;


public class PoiSearchPage extends AbstractSearchPage {
	

	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return Studiinf.i18n.poiSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "poi";
	}

	@Override
	public boolean hasOrganigramm() {
		return false;
	}

	@Override
	protected void handleSearch(String searchTerm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateResults(List<ResourceDescriptor> ressourcesMatched) {
		// TODO Auto-generated method stub
		
	}

}
