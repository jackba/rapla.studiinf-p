package org.rapla.plugin.studiinf.client.search;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.ResourceDescriptor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.rpc.ServiceDefTarget;


public class SearchUtils {	
	
	private static RaplaJsonService service = null;
	
	public static RaplaJsonService getService() {
		if(service == null){
			service = GWT.create(RaplaJsonService.class);
			String address = GWT.getModuleBaseURL() + "../rapla/json/org.rapla.plugin.freiraum.RaplaJsonService";
		 	((ServiceDefTarget) service).setServiceEntryPoint(address);
		}
		return service;
	}
	
	public static String getServiceLocale(){
		return  LocaleInfo.getCurrentLocale().getLocaleName().substring(0, 2);
	}

	public static List<ResourceDescriptor> startsWithSearchTerm(int searchTerm, String searchString, List<ResourceDescriptor> resources)
	{
List<ResourceDescriptor> resourceMatched = new LinkedList<ResourceDescriptor>();
		
		for(ResourceDescriptor resource : resources)
		{
			if(resource.getSearchTerms().size() > searchTerm)
			{
			String s = resource.getSearchTerms().get(searchTerm);
			if(s.toLowerCase().startsWith(searchString))
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
}
