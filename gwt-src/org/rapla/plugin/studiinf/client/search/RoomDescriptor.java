package org.rapla.plugin.studiinf.client.search;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.DisplayMode;

/**
 * Descriptor, which contains all the attributes to a specific room.
 *
 */
public class RoomDescriptor extends AbstractDescriptor {
	
	private String roomNr;	
	private String roomType;
	private String department;
	private String picture;
	private String raplaLink;
	private String location;
	
	public RoomDescriptor(ResourceDetail room) {
		super(room);
	}
	
	@Override
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

	public String getRaplaLink() {
		if(raplaLink == null)
		{
			raplaLink = getCell("resourceURL");
			}
		return raplaLink;
		}
	
	public String getLocation()
	{
		if(location == null)
		{
			location = DisplayMode.enhanceImageURL(getCell("location"));
		}
		return location;
	}
	
	
	
}
