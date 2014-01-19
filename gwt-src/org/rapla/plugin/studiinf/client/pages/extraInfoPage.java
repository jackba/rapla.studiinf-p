package org.rapla.plugin.studiinf.client.pages;

import java.util.LinkedList;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.DetailEntry;
import org.rapla.plugin.studiinf.client.ui.NavButton;

import com.google.gwt.user.client.ui.FlexTable;

public class extraInfoPage extends AbstractDetailPage {
FlexTable detailsTable;
private NavButton backBtn;
	
	@Override
	public void init(){
		super.init();
		detailsTable = new FlexTable();
		detailsTable.setStyleName("detailsTable");
		backBtn = new NavButton(Studiinf.i18n.back(),null,null);
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
		PersonDescriptor person = new PersonDescriptor(resource);
		LinkedList<ResourceDetailRow> details = person.getDetails();
		detailsTable.removeAllRows();
		for (ResourceDetailRow detail:details){
			addDetails(detail.getLabel(), detail.getValue());
		}
	}
	
	
	public void addDetails(String label, String value){
		DetailEntry detail = new DetailEntry(label, value);
		detail.setStyleName("detailEntry");
		detailsTable.setWidget(detailsTable.getRowCount(), 0, detail);
	}

}
