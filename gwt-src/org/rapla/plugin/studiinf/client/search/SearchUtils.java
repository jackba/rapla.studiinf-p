package org.rapla.plugin.studiinf.client.search;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;


public class SearchUtils {	
	
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
			if(resource.getSearchTerms().get(searchTerm).toLowerCase().startsWith(searchString.toLowerCase()))
			{
				resourceMatched.add(resource);
			}
			}
		}
		return resourceMatched;
	}
	
	public static List<ResourceDescriptor> startsWithName(String searchString, List<ResourceDescriptor> resources)
	{
List<ResourceDescriptor> resourceMatched = new LinkedList<ResourceDescriptor>();
		
		for(ResourceDescriptor resource : resources)
		{
			String s = resource.getName();
			if(s.toLowerCase().startsWith(searchString.toLowerCase()))
			{
				resourceMatched.add(resource);
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
			if(s.toLowerCase().contains(searchString.toLowerCase()))
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
			if(s.toLowerCase().contains(searchString.toLowerCase()))
			{
				resourceMatched.add(resource);
			}
		}
		return resourceMatched;
	}
	
	public static void alertSearchTerms(List<ResourceDescriptor> resources){
		for(ResourceDescriptor resource : resources){
			int id = 0;
			for(String term : resource.getSearchTerms()){
				Window.alert(id+ " " + term);
				id++;
			}
		}
	}
}
