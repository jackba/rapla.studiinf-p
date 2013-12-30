package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class PersonSearchPage extends AbstractSearchPage {
		
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
		addResult( new ResultButton(1, "Stephan Bürger", Navigation.personDetail,"001", new Image("img/Kurse.svg")));
		addResult(new ResultButton(2, "Sara Dresdner",Navigation.personDetail,"002", new Image("img/Kurse.svg")));
		addResult(new ResultButton(3, "Prof. Dr. R. Küstermann",Navigation.personDetail,"003", new Image("img/Kurse.svg")));
		addResult(new ResultButton(4, "Leon Schmid",Navigation.personDetail,"004", new Image("img/Kurse.svg")));
		addResult(new ResultButton(5, "Franziska Zimmermann",Navigation.personDetail,"005", new Image("img/Kurse.svg")));
		addResult(new ResultButton(6, "Anne Schiffer",Navigation.personDetail,"006", new Image("img/Kurse.svg")));
	}

	@Override
	public boolean hasOrganigramm() {
		return true;
	}
	
	@Override
	public void updateResults(List<ResourceDescriptor> results)
	{
//		Window.alert("Update begonnen");
		clearResult();
//		Window.alert("alte Daten gel�scht");
		int counter = 1;
		for(ResourceDescriptor person : results)
		{
			addResult(new ResultButton(counter, person.getName(), Navigation.personDetail, person.getId(), new Image("img/Kurse.svg")));
			counter++;
		}
//		Window.alert("Neue Daten");
	}

	@Override
	protected void handleSearch(String searchTerm) {
		 new PersonSearch(searchTerm, this);
		}
	
	

}
