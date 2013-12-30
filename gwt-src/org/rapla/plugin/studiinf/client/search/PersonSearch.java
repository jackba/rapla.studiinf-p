package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.PersonSearchPage;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwtjsonrpc.common.AsyncCallback;


public class PersonSearch implements AsyncCallback<List<ResourceDescriptor>>{
private static final int FIRST_NAME = 0;
private static final int LAST_NAME = 1;

private String searchTerm;
private PersonSearchPage page;

private static List<ResourceDescriptor> persons;

public PersonSearch(String searchTerm, PersonSearchPage psPage)
{
	
	this.searchTerm = searchTerm.toLowerCase();
	page = psPage;
	if(persons == null)
		{
		SearchUtils.getService().getResources("persons", null, LocaleInfo.getCurrentLocale().getLocaleName(), this);
		}
	else
	{
		this.onSuccess(persons);
	}
//	Window.alert("Suche erstellt");
}

@Override
public void onFailure(Throwable arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onSuccess(List<ResourceDescriptor> arg0) {
	
	if( persons == null)
	{
		persons = arg0;
	}
	List<ResourceDescriptor> personMatched = new NoDuplicatesList<ResourceDescriptor>();
	
personMatched.addAll(SearchUtils.startsWithSearchTerm(LAST_NAME, searchTerm, persons));
personMatched.addAll(SearchUtils.startsWithSearchTerm(FIRST_NAME, searchTerm, persons));
personMatched.addAll(SearchUtils.containsSearchTerm(LAST_NAME, searchTerm, persons));
personMatched.addAll(SearchUtils.containsSearchTerm(FIRST_NAME, searchTerm, persons));
personMatched.addAll(SearchUtils.containsName(searchTerm, persons));
	
	page.updateResults(personMatched);
//	Window.alert("Suche abgeschlossen");
	
}

	
	
}
