package org.rapla.plugin.studiinf.client.search;

import org.rapla.plugin.freiraum.common.ResourceDetail;

/**
 * Descriptor, which contains all the attributes to a specific point of interest.
 *
 */
public class PoiDescriptor extends AbstractDescriptor {
	

	private String rowTwo;
	private String name;
	private String rowOne;
	private String picture;
	

	public PoiDescriptor(ResourceDetail poi) {
		super(poi);
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
	


	
}
