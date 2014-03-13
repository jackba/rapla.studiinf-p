package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDetail;

/**
 * Descriptor, which contains all the attributes to a specific room.
 *
 */
public class RoomDescriptor {
	
	private String roomNr;	
	private String roomType;
	private String name;
	private String department;
	private String picture;
	private ResourceDetail room;
	private Collection<String> keys;
	
	public RoomDescriptor(ResourceDetail room) {
		this.room = room;
		keys = room.getKeys();
	}

	public String getRoomNr() {
		if(roomNr == null){
			roomNr = getCell("raumnr");
		}
		return roomNr;
	}
	
	public String getRoomType() {
		if(roomType == null){
			roomType = getCell("raumart");
		}
		return roomType;
	}
	
	public String getName() {
		if(name == null){
			name = getCell("name");
		}
		return name;
	}

	public String getDepartment() {
		if(department == null){
			department = getCell("abteilung");
		}
		return department;
	}
	
	public String getPicture() {
		if(picture == null){
			picture = getCell("bild");
		}
		return picture;
	}

	private String getCell(String cellName){
		if(keys.contains(cellName)){
			return room.getRow(cellName).getValue();
		}else{
			return "";
		}
	}
	
}
