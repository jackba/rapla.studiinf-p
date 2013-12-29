package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.pages.RoomSearchPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwtjsonrpc.common.AsyncCallback;


public class RoomSearch implements AsyncCallback<List<ResourceDescriptor>>{
private static final int NAME = 0;
private static final int TYPE = 1;
private static RaplaJsonService service = GWT.create(RaplaJsonService.class);
private String searchTerm;
private RoomSearchPage page;

private static List<ResourceDescriptor> rooms;

public RoomSearch(String searchTerm, RoomSearchPage rsPage)
{
	String address = GWT.getModuleBaseURL() + "../rapla/json/RaplaJsonService";
 	((ServiceDefTarget) service).setServiceEntryPoint(address);
	this.searchTerm = searchTerm.toLowerCase();
	page = rsPage;
	if(rooms == null)
		{
		service.getResources("rooms", null, this);
		}
	else
	{
		this.onSuccess(rooms);
	}
//	Window.alert("Suche erstellt");
}

@Override
public void onFailure(Throwable arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void onSuccess(List<ResourceDescriptor> arg0) {
	
	if( rooms == null)
	{
		rooms = arg0;
	}
	List<ResourceDescriptor> roomMatched = new NoDuplicatesList<ResourceDescriptor>();
	
roomMatched.addAll(SearchUtils.startsWithSearchTerm(NAME, searchTerm, rooms));
roomMatched.addAll(SearchUtils.startsWithSearchTerm(TYPE, searchTerm, rooms));
roomMatched.addAll(SearchUtils.containsSearchTerm(NAME, searchTerm, rooms));
//roomMatched.addAll(SearchUtils.containsSearchTerm(TYPE, searchTerm, rooms));
roomMatched.addAll(SearchUtils.containsName(searchTerm, rooms));
	
	page.updateResults(roomMatched);
	
	
//	for(ResourceDescriptor room : roomMatched)
//	{
//		for(String s : room.getSearchTerms())
//		{
//	Window.alert(s);
//		}
//	}
//	
}

	
	
}
