package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

public class PersonRaplaPage extends RaplaPage {

	public PersonRaplaPage() {
		super(Navigation.personDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "personRapla";
	}

}
