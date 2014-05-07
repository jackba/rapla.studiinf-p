package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDetail;

public class CategoryDescriptor {

	private String name;
	private String id;
	
	private ResourceDetail category;
	private Collection<String> keys;
	
	public CategoryDescriptor(ResourceDetail category) {
		this.category = category;
		keys = category.getKeys();		
	}
	
	public String getName() {
		if(name == null){
			name = getCell("name");
		}
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		if(id == null){
			id = getCell("id");
		}
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String getCell(String cellName){
		if(keys.contains(cellName)){
			return category.getRow(cellName).getValue();
		}else{
			return "";
		}
	}
	
}
