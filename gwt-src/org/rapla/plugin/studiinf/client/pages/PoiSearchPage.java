package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PoiSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;


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
		new PoiSearch(searchTerm, this);
		
	}

	@Override
	public void updateResults(List<ResourceDescriptor> ressourcesMatched) {
		clearResult();
		int counter = 1;
		for(ResourceDescriptor poi : ressourcesMatched)
		{
			addResult(new ResultButton(counter, poi.getName(), Navigation.roomDetail, poi.getId(), new Image("img/Kurse.svg")));
			counter++;
		//	Window.alert(poi.getName());
		}
		
	}

	
	
}
