package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

public class CourseRaplaPage extends RaplaPage {

	public CourseRaplaPage() {
		super(Navigation.courseDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "courseRapla";
	}


}
