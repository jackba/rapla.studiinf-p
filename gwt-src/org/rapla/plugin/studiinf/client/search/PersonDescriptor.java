package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDetail;

public class PersonDescriptor{
	private String name;
	private String mail;
	private String roomNr;
	private String department;
	private String phoneNr;
	private String raplaLink;

	private ResourceDetail person;
	private Collection<String> keys;
	
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
