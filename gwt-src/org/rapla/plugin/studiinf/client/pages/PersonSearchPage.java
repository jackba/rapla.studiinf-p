package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonSearch;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

/**
 * 
 * @author Team StudiInf
 * Page for searching persons
 */
public class PersonSearchPage extends AbstractSearchPage {
	public static final String ResourceType = "persons";
	
	public PersonSearchPage() {
		super(true, true,true, FontIcon.Persons,Navigation.personDetail);
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
	public void updateResults(List<ResourceDescription> results)
	{
		clearResult();
		for(ResourceDescription person : results)
		{
			addResult(new ResultButton(person.getName(), Navigation.personDetail, person.getId(), FontIcon.Persons, this));
		}
		refresh();
	}

	@Override
	protected void handleSearch(String searchTerm) {
		 new PersonSearch(searchTerm, this);
		}

	@Override
	public String getResourceType() {
		return ResourceType;
	}

	@Override
	AbstractPage getOrganisationType() {
		return Navigation.organisationChartPerson;
	}

	

}
