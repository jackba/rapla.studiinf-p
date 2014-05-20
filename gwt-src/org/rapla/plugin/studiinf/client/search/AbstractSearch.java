package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.pages.AbstractSearchPage;
import org.rapla.plugin.studiinf.client.pages.SearchPageInterface;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

/**
 * 
 *Template for search
 */
public abstract class AbstractSearch extends AbstractOrganigramSearch implements AsyncCallback<List<ResourceDescription>> {
	
	public AbstractSearch(String searchTerm,SearchPageInterface page,boolean autoinit) {
		super(searchTerm, null, page, autoinit);
	}
	
	public AbstractSearch(String searchTerm, SearchPageInterface page) {
		super(searchTerm, null, page);
	}


}
