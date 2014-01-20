package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;
import java.util.LinkedList;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;

import com.google.gwt.user.client.Window;

public class PersonDescriptor{
	private String name;
	private String mail;
	private String roomNr;
	private String department;
	private String phoneNr;
	private String raplaLink;

	private ResourceDetail person;
	private Collection<String> keys;
	
	public LinkedList<ResourceDetailRow> getDetails(){
		LinkedList<ResourceDetailRow> results= new LinkedList<ResourceDetailRow>();
		for (String key: keys){
			if (key.equals("bild") || key.equals("resourceURL")){

			} else {
				results.add(person.getRow(key));
			}
		}
		return results;
	}
	
	public String getName() {
		if(name == null){
			name = getCell("name");
		}
		return name;
	}

	public String getMail() {
		if(mail == null){
			mail = getCell("email");
		}
		return mail;
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
	public String getPhoneNr(){
		if(phoneNr == null){
			phoneNr = getCell("telefon");
		}
		return phoneNr;
	}

	public PersonDescriptor(ResourceDetail person) {
		this.person = person;
		keys = person.getKeys();		
		
	}
	
	
	public String getRaplaLink() {
		if(raplaLink == null)
		{
			raplaLink = getCell("resourceURL");
			}
		return raplaLink;
		}
	
	private String getCell(String cellName){
		if(keys.contains(cellName)){
			return person.getRow(cellName).getValue();
		}else{
			return "";
		}
	}
	
}
