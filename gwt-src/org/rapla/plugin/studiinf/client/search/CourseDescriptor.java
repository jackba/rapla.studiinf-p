package org.rapla.plugin.studiinf.client.search;

import org.rapla.plugin.freiraum.common.ResourceDetail;

/**
 * Descriptor, which contains all the attributes to a specific course.
 *
 */
public class CourseDescriptor extends AbstractDescriptor{
	
	public CourseDescriptor(ResourceDetail resource) {
		super(resource);
	}

	private String year;
	private String picture;
	private String department;

	
	public String getDepartment() {
		if(department == null){
			department = getCell("abteilung");
		}
		return department;
	}
	public String getPicture(){
		if(picture == null){
			picture = getCell("bild");
		}
		return picture;
	}
	
	public String getYear(){
		if(year == null){
			year = getCell("jahrgang");
		}
		return year;
	}

	
	
}
