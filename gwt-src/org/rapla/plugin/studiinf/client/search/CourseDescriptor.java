package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDetail;

public class CourseDescriptor{
	private String year;
	private String roomNr;
	private String name;
	private String picture;
	private String department;
	
	private ResourceDetail course;
	private Collection<String> keys;
	
	public String getName() {
		if(name == null){
			name = getCell("name");
		}
		return name;
	}

	public String getRoomNr() {
		if(roomNr == null){
			roomNr = getCell("raumnr");
		}
		return roomNr;
	}


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

	public CourseDescriptor(ResourceDetail course) {
		this.course = course;
		keys = course.getKeys();
//		Window.alert(keys.toString());
		
	}
	
	private String getCell(String cellName){
		if(keys.contains(cellName)){
			return course.getRow(cellName).getValue();
		}else{
			return "";
		}
	}
	
}
