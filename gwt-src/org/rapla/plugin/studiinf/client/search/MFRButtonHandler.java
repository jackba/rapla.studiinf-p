package org.rapla.plugin.studiinf.client.search;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;
import org.rapla.plugin.studiinf.client.ui.ResultTable;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

public class MFRButtonHandler implements AsyncCallback<ResourceDescription>{

	private ResultTable resultTable;
	private FontIcon icon;
	private AbstractPage targetPage;
	private AbstractSearchPage searchPage;
	
	public MFRButtonHandler(ResultTable resultTable, FontIcon icon, AbstractPage targetPage, AbstractSearchPage searchPage){
		this.resultTable = resultTable;
		this.icon = icon;
		this.targetPage = targetPage;
		this.searchPage = searchPage;
	}
	
	@Override
	public void onFailure(Throwable arg0) {
		//do nothing
	}

	@Override
	public void onSuccess(ResourceDescription resourceDescriptor) {
		this.resultTable.addResult(new ResultButton(resourceDescriptor.getName(), targetPage, resourceDescriptor.getId(), icon, searchPage));
		this.resultTable.refresh();
	}

	
	
}
