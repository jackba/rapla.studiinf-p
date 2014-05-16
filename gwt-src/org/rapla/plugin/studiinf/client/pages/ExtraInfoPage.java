package org.rapla.plugin.studiinf.client.pages;

import java.util.LinkedList;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.DetailEntry;
import org.rapla.plugin.studiinf.client.ui.NavButton;

import com.google.gwt.user.client.ui.FlexTable;

/**
 * Special Detail page, which contains additional information to a person.
 *
 */
public class ExtraInfoPage extends AbstractDetailPage {
FlexTable detailsTable;
//private NavButton backBtn;
private String id;
private AbstractPage target;
	
	public ExtraInfoPage(AbstractPage target)
	{
		this.target = target;
	}
	
	@Override
	public void init(){
		super.init();
		detailsTable = new FlexTable();
		detailsTable.setStyleName("detailsTable");
		//backBtn = new NavButton(Studiinf.i18n.back(),target,id);
		footer.setTargetPage(target);
		//backBtn = new NavButton(Studiinf.i18n.back(), target, id);
		//backBtn.setStyleName("raplaBackButton");
		//this.add(backBtn);
		this.add(detailsTable);
		
	}
	
	@Override
	public String getHistoryKey() {
		return "extraInfos";
	}

	@Override
	public String getTitle() {
		return "Extra Infos";
	}

	@Override
	public boolean hasDefaultQrBox() {
		return false;
	}

	@Override
	protected void handleRessource(String id, ResourceDetail resource) {

		this.id = id;
		//backBtn.setTargetId(id);
		footer.setTargetId(id);
		PersonDescriptor person = new PersonDescriptor(resource);
		LinkedList<ResourceDetailRow> details = person.getDetails();
		detailsTable.removeAllRows();
		for (ResourceDetailRow detail:details){
			addDetails(detail.getLabel(), detail.getValue());
		}
	}
	
	/**
	 * Adds one information to the table.
	 * @param label Label of the Information, which should be added.
	 * @param value Content to the corresponding Label.
	 */
	public void addDetails(String label, String value){
		DetailEntry detail = new DetailEntry(label, value);
		detail.setStyleName("detailEntry");
		detailsTable.setWidget(detailsTable.getRowCount(), 0, detail);
	}

	@Override
	protected void showRaplaLinks(boolean b) {
		// do nothing always true;
	}
	
	

}
