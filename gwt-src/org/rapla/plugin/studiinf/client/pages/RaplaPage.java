package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.NavButton;

import com.google.gwt.user.client.ui.Frame;
import com.sun.org.apache.xml.internal.security.utils.I18n;

/**
 * Special detail page, which displays the rapla link for one ressource
 *
 */
abstract public class RaplaPage extends AbstractDetailPage {
	
	private Frame frame;
	private String id;
	private AbstractPage target;
	private NavButton backBtn;
	
	/**
	 * 
	 * @param target Corresponding detail page.
	 */
	public RaplaPage(AbstractPage target)
	{
		this.target = target;
	}

	/**
	 * Initialises and displays all the elemtents of the page.
	 */
	@Override
	public void init(){
		super.init();
		
		frame = new Frame("#");
		frame.setStyleName("raplaFrame");
		backBtn = new NavButton(Studiinf.i18n.back(), target, id);
		backBtn.setStyleName("raplaBackButton");
		this.add(backBtn);
			
	}
	
	@Override
	public String getHistoryKey() {
		return "raplaLink";
	}

	@Override
	public String getTitle() {
		return "rapla";
	}

	@Override
	public boolean hasDefaultQrBox() {
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
