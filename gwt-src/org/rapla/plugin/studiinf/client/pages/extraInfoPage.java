package org.rapla.plugin.studiinf.client.pages;

import java.util.LinkedList;
import java.util.Map;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.freiraum.common.ResourceDetailRow;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.search.PersonDescriptor;
import org.rapla.plugin.studiinf.client.ui.DetailEntry;
import org.rapla.plugin.studiinf.client.ui.NavigationButton;
import org.rapla.plugin.studiinf.client.ui.NavigationIconButton;

import com.google.gwt.thirdparty.guava.common.collect.Table;
import com.google.gwt.user.client.Window;
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
		detailsTable.setStyleName("detailsTable");
		
		backBtn = new NavigationButton("Zurück", Navigation.personDetail, id);
		backBtn.setStyleName("raplaBackButton");
		this.add(backBtn); 
		
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
		detailsTable.setWidget(detailsTable.getRowCount(), 0, detail);
	}

}
