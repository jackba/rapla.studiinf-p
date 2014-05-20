package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PoiSearch;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

/**
 * 
 * @author Team StudiInf
 * Page for searching POIs
 */
public class SearchPagePoI extends AbstractSearchPage {
	
	
	public SearchPagePoI() {
		super(false, false,true,8,2, false, FontIcon.PoI,Navigation.roomDetail);
	}

	@Override
	public void init(){
		super.init();
		
		qrBox.getElement().getStyle().setProperty("top", "65vh");
		resultPanel.getElement().getStyle().setProperty("height", "35vh");
		
		setSearched(true);
		handleSearch("*");
				
	}
	
	@Override
	public void onShow() {
		super.onShow();
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
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		clearResult();
		for(ResourceDescription poi : ressourcesMatched)
		{
			addResult(new ResultButton(poi.getName(), Navigation.poiDetail, poi.getId(), FontIcon.PoI));
		}
		refresh();
		
	}

	@Override
	public String getResourceType() {
		return "sonstiges";
	}

	@Override
	protected void handleMostFrequent() {
		clearResult();
		
	}

	@Override
	AbstractPage getOrganisationType() {
		return null;
	}

	
	
}
