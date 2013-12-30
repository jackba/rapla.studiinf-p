package org.rapla.plugin.studiinf.client.search;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.freiraum.common.ResourceDetail;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwtjsonrpc.common.AsyncCallback;


public class SearchUtils {	
	
	private static RaplaJsonService service = null;
	
	private static RaplaJsonService getService() {
		if(service == null){
			service = GWT.create(RaplaJsonService.class);
			String address = GWT.getModuleBaseURL() + "../rapla/json/org.rapla.plugin.freiraum.RaplaJsonService";
		 	((ServiceDefTarget) service).setServiceEntryPoint(address);
		}
		return service;
	}
	
	private static String getServiceLocale(){
		return  LocaleInfo.getCurrentLocale().getLocaleName().substring(0, 2);
	}

	public static List<ResourceDescriptor> startsWithSearchTerm(int searchTerm, String searchString, List<ResourceDescriptor> resources)
	{
List<ResourceDescriptor> resourceMatched = new LinkedList<ResourceDescriptor>();
		
		for(ResourceDescriptor resource : resources)
		{
			
			
			if(resource.getSearchTerms().size() > searchTerm)
			{
			if(resource.getSearchTerms().get(searchTerm).toLowerCase().startsWith(searchString))
			{
				resourceMatched.add(resource);
			}
			}
		}
		return resourceMatched;
	}

	public static List<ResourceDescriptor> containsSearchTerm(int searchTerm, String searchString, List<ResourceDescriptor> resources)
	{
List<ResourceDescriptor> resourceMatched = new LinkedList<ResourceDescriptor>();
		
		for(ResourceDescriptor resource : resources)
		{
			if(resource.getSearchTerms().size() > searchTerm)
			{
			String s = resource.getSearchTerms().get(searchTerm);
			if(s.toLowerCase().contains(searchString))
			{
				resourceMatched.add(resource);
			}
		}
		}
		return resourceMatched;
	}

	public static List<ResourceDescriptor> containsName(String searchString, List<ResourceDescriptor> resources)
	{
List<ResourceDescriptor> resourceMatched = new LinkedList<ResourceDescriptor>();
		
		for(ResourceDescriptor resource : resources)
		{
			String s = resource.getName();
			if(s.toLowerCase().contains(searchString))
			{
				resourceMatched.add(resource);
			}
		}
		return resourceMatched;
	}

	public static void getResources(String resourceType, AsyncCallback<List<ResourceDescriptor>> callback) {
		getService().getResources(resourceType, null, getServiceLocale(), callback);
		
	}

	public static void getResource(String resourceId, AsyncCallback<ResourceDetail> callback) {
		getService().getResource(resourceId, getServiceLocale(), callback);
	}

	public static void getOrganigram(String categoryId,	AsyncCallback<List<CategoryDescription>> callback) {
		getService().getOrganigram(categoryId, getServiceLocale(), callback);
		
	}

	public static void getFreeResources(String start, String end, String resourceType, AsyncCallback<List<Event>> callback) {
		getService().getFreeResources(start, end, resourceType, getServiceLocale(), callback);
		
	}

	public static void getEvents(String start, String end, String resourceId, AsyncCallback<List<Event>> callback) {
		getService().getEvents(start, end, resourceId, getServiceLocale(), callback);
		
	}
}
