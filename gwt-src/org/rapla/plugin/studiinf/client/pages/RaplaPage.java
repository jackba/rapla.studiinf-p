package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;

import com.google.gwt.user.client.ui.Frame;

/**
 * Special detail page, which displays the rapla link for one ressource
 *
 */
abstract public class RaplaPage extends AbstractDetailPage {
	
	private Frame frame;
	//private String id;
	private AbstractPage target;
	
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
		footer.setTargetPage(target);
			
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
		footer.setTargetId(id);
		PersonDescriptor person = new PersonDescriptor(resource);
		String raplaLink = person.getRaplaLink();
		if(raplaLink != null){
			frame.setUrl(Studiinf.urlToRapla + raplaLink);
			this.add(frame);
		}
	}
	
	@Override
	protected void showRaplaLinks(boolean b) {
		// do nothing always true;
	}


}
