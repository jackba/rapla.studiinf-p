package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Studiinf;


public class CourseSearch extends AbstractSearchPage {

	@Override
	public String getTitle() {
		return Studiinf.i18n.courseSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "course";
	}

	@Override
	public boolean hasOrganigramm() {
		return true;
	}

	@Override
	protected void handleSearch(String searchTerm) {
		// TODO Auto-generated method stub
		
	}

}
