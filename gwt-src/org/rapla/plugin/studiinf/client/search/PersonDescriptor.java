package org.rapla.plugin.studiinf.client.search;

import java.util.Arrays;
import java.util.LinkedList;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;

/**
 * descriptor, which contains all the attributes of a specific person.
 *
 */
public class PersonDescriptor extends AbstractDescriptor{

	private static final String[] hideOnDetails ={"bild","resourceURL"};
	private String mail;
	private String department;
	private String phoneNr;
	private String raplaLink;
	private String picture;


	
	public LinkedList<ResourceDetailRow> getDetails(){
		LinkedList<ResourceDetailRow> results= new LinkedList<ResourceDetailRow>();
		for (String key: keys){
			if (!Arrays.asList(hideOnDetails).contains(key)){
				results.add(resource.getRow(key));
			}
		}
		return results;
	}


	public String getMail() {
		if(mail == null){
			mail = getCell("email");
		}
		return mail;
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
		super(person);	
	}
	
	
	public String getRaplaLink() {
		if(raplaLink == null)
		{
			raplaLink = getCell("resourceURL");
			}
		return raplaLink;
		}
	
	public String getPicture(){
		if(picture == null){
			picture = getCell("bild");
		}
		return picture;
	}
	
}
