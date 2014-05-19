package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;

/**
 * 
 * @author Team StudiInf
 * Page that shows rapla timetable for a course
 *
 */
public class CourseRaplaPage extends RaplaPage {

	public CourseRaplaPage() {
		super(Navigation.courseDetail);
	}
	
	@Override
	public String getHistoryKey() {
		
		return "courseRapla";
	}


}
