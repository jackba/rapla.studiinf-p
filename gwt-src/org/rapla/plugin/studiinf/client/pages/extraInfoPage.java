package org.rapla.plugin.studiinf.client.pages;

import java.util.LinkedList;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.DetailEntry;
import org.rapla.plugin.studiinf.client.ui.NavigationButton;

import com.google.gwt.user.client.ui.FlexTable;

public class extraInfoPage extends AbstractDetailPage {
	
private FlexTable detailsTable;
private NavigationButton backBtn;
private String id;
private LinkedList<ResourceDetailRow> details;
	
	@Override
	public void init(){
		super.init();
		detailsTable = new FlexTable();
		backBtn = new NavigationButton(Studiinf.i18n.back(), Navigation.personDetail, id);
		
		detailsTable.setStyleName("detailsTable");
		backBtn.setStyleName("raplaBackButton");
		
		this.add(backBtn); 
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
		details = person.getDetails();
		refresh();

	}
	
	@Override
	public void refresh(){
		super.refresh();
		detailsTable.removeAllRows();
		for (ResourceDetailRow detail:details){
			addDetails(detail.getLabel(), detail.getValue());
		}
		backBtn.setTargetId(id);
	}
	
	public void addDetails(String label, String value){
		DetailEntry detail = new DetailEntry(label, value);
		detail.setStyleName("detailEntry");
		detailsTable.setWidget(detailsTable.getRowCount(), 0, detail);
	}

}
