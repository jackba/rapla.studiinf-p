package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;

import com.google.gwt.user.client.Window;

public class extraInfoPage extends AbstractDetailPage {

	@Override
	public void init(){
		super.init();
		
	}
	
	
	
	@Override
	public String getHistoryKey() {
		// TODO Auto-generated method stub
		return "extraInfos";
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Extra Infos";
	}

	@Override
	public boolean hasDefaultQrBox() {
		return false;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		// TODO Auto-generated method stub
		PersonDescriptor person = new PersonDescriptor(resource);
		Window.alert(person.getName() + ", "+ person.getMail()+ ", "+ person.getPhoneNr());
		
	}

}
