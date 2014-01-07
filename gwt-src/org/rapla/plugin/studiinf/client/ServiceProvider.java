package org.rapla.plugin.studiinf.client;

import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.search.SearchUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwtjsonrpc.common.AsyncCallback;

public class ServiceProvider {

	private static RaplaJsonService service = null;

	private static RaplaJsonService getService() {
		if(service == null){
			service = GWT.create(RaplaJsonService.class);
			String address = GWT.getModuleBaseURL() + "../rapla/json/org.rapla.plugin.freiraum.RaplaJsonService";
		 	((ServiceDefTarget) service).setServiceEntryPoint(address);
		}
		return service;
	}

	public static void getResources(String resourceType, AsyncCallback<List<ResourceDescriptor>> callback) {
		getService().getResources(resourceType, null, SearchUtils.getServiceLocale(), callback);
		
	}

	public static void getResource(String resourceId, AsyncCallback<ResourceDetail> callback) {
		getService().getResource(resourceId, SearchUtils.getServiceLocale(), callback);
	}

	public static void getOrganigram(String categoryId,	AsyncCallback<List<CategoryDescription>> callback) {
		getService().getOrganigram(categoryId, SearchUtils.getServiceLocale(), callback);
		
	}

	public static void getFreeResources(String start, String end, String resourceType, AsyncCallback<List<Event>> callback) {
		getService().getFreeResources(start, end, resourceType, SearchUtils.getServiceLocale(), callback);
		
	}

	public static void getEvents(String start, String end, String resourceId, AsyncCallback<List<Event>> callback) {
		getService().getEvents(start, end, resourceId, SearchUtils.getServiceLocale(), callback);
		
	}

}
