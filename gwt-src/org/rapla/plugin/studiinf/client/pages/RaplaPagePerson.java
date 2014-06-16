package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

/**
 * 
 * @author Team StudiInf
 * Page that shows rapla timetable for a person
 *
 */
public class RaplaPagePerson extends RaplaPage {

	public RaplaPagePerson() {
		super(Navigation.personDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "personRapla";
	}

}
