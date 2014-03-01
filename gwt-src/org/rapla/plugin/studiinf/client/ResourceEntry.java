package org.rapla.plugin.studiinf.client;

import java.util.Map.Entry;

/**
 * 
 *
 */
public class ResourceEntry implements Entry<String, Integer> {
	private String key;
	private Integer value;
	
	public ResourceEntry(String key, String value){
		this.key = key;
		this.value =  Integer.parseInt(value);
	}
	public ResourceEntry(Entry<String, String> entry){
		this(entry.getKey(),entry.getValue());
	}
	
	@Override
	public String getKey() {
		return key;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	@Override
	public Integer setValue(Integer value) {
		this.value = value;
		return value;
	}
	@Override
	public String toString() {
		return this.getKey()+"="+this.getValue().toString();
	}
	
}
