package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;

/**
 * 
 * @author Team StudiInf
 * 
 * Template for the descriptors. All descriptors have to implement this class.
 * Template contains the basic methods and attributes for all descriptors.
 */
public class AbstractDescriptor {

	protected ResourceDetail resource;
	protected Collection<String> keys;
	
	private String name;
	private ResourceDescription room;
	private String roomNr;
	private String roomId;

	/**
	 * Creates a Descriptor with details for the given resource
	 * @param resource resourceDetail for usage in studiInf application
	 */
	public AbstractDescriptor(ResourceDetail resource) {
			this.resource = resource;
			if(resource != null){
				keys = resource.getKeys();		
			}
	}

	public String getName() {
		if(name == null){
			name = getCell("name");
		}
		return name;
	}

	public String getRoomNr() {
		if(roomNr == null && getRoom() != null){
			roomNr = getRoom().getName();
		}
		return roomNr;
	}
	
	public String getRoomId() {
		if(roomId == null && getRoom() != null){
			roomId = getRoom().getId();
		}
		return roomId;
	}

	/**
	 * Returns the value of a given cell
	 * @param cellName
	 * @return value of cell
	 */
	protected String getCell(String cellName) {
		if(resource != null && keys.contains(cellName)){
			return resource.getRow(cellName).getValue();
		}else{
			return "";
		}
	}
	
	private ResourceDescription getRoom(){
		if(room == null && resource != null &&  resource.getResourceLinks() != null && resource.getResourceLinks().containsKey("raum")){
			room = resource.getResourceLinks().get("raum");
		}
		return room;
	}

}