package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDetail;

public class PoiDescriptor {
	
	private String roomNr;	
	private String rowTwo;
	private String name;
	private String rowOne;
	private String picture;
	
	private ResourceDetail poi;
	private Collection<String> keys;

	public PoiDescriptor(ResourceDetail poi) {
		this.poi = poi;
		keys = poi.getKeys();
	}
	
	public Collection<String> getKeys() {
		return keys;
	}
	
	public String getRoomNr() {
		if(roomNr == null){
			roomNr = getCell("raumnr");
		}
		return roomNr;
	}
	
	public String getRowOne() {
		if(rowOne == null){
			rowOne = getCell("zeile1");
		}
		return rowOne;
	}
	
	public String getRowTwo() {
		if(rowTwo == null){
			rowTwo = getCell("zeile2");
		}
		return rowTwo;
	} 
	
	public String getName() {
		if(name == null){
			name = getCell("name");
		}
		return name;
	}	
	
	public String getPicture() {
		if(picture == null){
			picture = getCell("bild");
		}
		return picture;
	}
	

	private String getCell(String cellName){
		if(keys.contains(cellName)){
			return poi.getRow(cellName).getValue();
		}else{
			return "";
		}
	}
	
}
