package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PoiSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class PoiSearchPage extends AbstractSearchPage {
	
	
	public PoiSearchPage() {
		super(false, false,true,8,2);
	}

	@Override
	public void init(){
		super.init();
		
		setSearched(true);
		handleSearch("*");
				
	}
	
	@Override
	public String getTitle() {
		return Studiinf.i18n.poiSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "poi";
	}

	@Override
	protected void handleSearch(String searchTerm) {
		new PoiSearch("*",this);
		
	}

	@Override
	public void updateResults(List<ResourceDescriptor> ressourcesMatched) {
		clearResult();
		int counter = 1;
		for(ResourceDescriptor poi : ressourcesMatched)
		{
			addResult(new ResultButton(counter, poi.getName(), Navigation.roomDetail, poi.getId(), new Image(IconProvider.POI)));
			counter++;
		}
		
	}

	@Override
	public String getResourceType() {
		return "sonstiges";
	}

	
	
}
