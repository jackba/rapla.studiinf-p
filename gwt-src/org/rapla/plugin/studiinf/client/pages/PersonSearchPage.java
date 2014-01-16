package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class PersonSearchPage extends AbstractSearchPage {
	private static final String PERSONS = "persons";
	
	public PersonSearchPage() {
		super(true, true,true);
	}

	@Override
	public String getTitle() {
		return Studiinf.i18n.personSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "person";
	}
	@Override
	public void init() {
		super.init();
	}

	
	@Override
	public void updateResults(List<ResourceDescriptor> results)
	{
		clearResult();
		for(ResourceDescriptor person : results)
		{
			addResult(new ResultButton(person.getName(), Navigation.personDetail, person.getId(), new Image(IconProvider.PERSONS)));
		}
		refresh();
	}

	@Override
	protected void handleSearch(String searchTerm) {
		 new PersonSearch(searchTerm, this);
		}

	@Override
	public String getResourceType() {
		return PERSONS;
	}

	@Override
	protected void handleMostFrequent() {
		clearResult();
		
	}
	
	

}
