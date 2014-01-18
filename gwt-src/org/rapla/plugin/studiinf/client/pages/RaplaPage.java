package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.NavigationButton;

import com.google.gwt.user.client.ui.Frame;

abstract public class RaplaPage extends AbstractDetailPage {
	
	private Frame frame;
	private String id;
	private AbstractPage target;
	private NavigationButton backBtn;

	public RaplaPage(AbstractPage target)
	{
		this.target = target;
	}
	
	@Override
	public void init(){
		super.init();
		
		frame = new Frame("#");
		frame.setStyleName("raplaFrame");
		
		backBtn = new NavigationButton("Zurück", target, id);
		backBtn.setStyleName("raplaBackButton");
		this.add(backBtn);
			
	}
	
	@Override
	public String getHistoryKey() {
		// TODO Auto-generated method stub
		return "raplaLink";
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "rapla";
	}

	@Override
	public boolean hasDefaultQrBox() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		this.id = id;
		backBtn.setTargetId(id);
		PersonDescriptor person = new PersonDescriptor(resource);
		
		
		frame.setUrl(person.getRaplaLink());
		this.add(frame);
		
	}
	
	


}
