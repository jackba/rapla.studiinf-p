package org.rapla.plugin.studiinf.client.pages;

import java.util.LinkedList;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.DetailEntry;

import com.google.gwt.user.client.ui.FlexTable;

public class extraInfoPage extends AbstractDetailPage {
FlexTable detailsTable;
	
	@Override
	public void init(){
		super.init();
		detailsTable = new FlexTable();
		detailsTable.setStyleName("detailsTable");
		this.add(detailsTable);
		
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
		LinkedList<ResourceDetailRow> details = person.getDetails();
		detailsTable.removeAllRows();
		for (ResourceDetailRow detail:details){
			addDetails(detail.getLabel(), detail.getValue());
		}
	}
	
	public void addDetails(String label, String value){
		DetailEntry detail = new DetailEntry(label, value);
		detailsTable.setWidget(detailsTable.getRowCount(), 0, detail);
	}

}
