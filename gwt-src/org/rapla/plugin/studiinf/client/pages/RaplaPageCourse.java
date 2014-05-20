package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

/**
 * 
 * @author Team StudiInf
 * Page that shows rapla timetable for a course
 *
 */
public class RaplaPageCourse extends RaplaPage {

	public RaplaPageCourse() {
		super(Navigation.courseDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "courseRapla";
	}


}
