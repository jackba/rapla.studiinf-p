package org.rapla.plugin.studiinf.client.search;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;


public class SearchUtils {	
	
	public static List<ResourceDescriptor> startsWithSearchTerm(int searchTerm, String searchString, List<ResourceDescriptor> resources)
	{
List<ResourceDescriptor> resourceMatched = new LinkedList<ResourceDescriptor>();
		
		for(ResourceDescriptor resource : resources)
		{
			String s = resource.getSearchTerms().get(searchTerm);
			if(s.toLowerCase().startsWith(searchString))
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
			String s = resource.getSearchTerms().get(searchTerm);
			if(s.toLowerCase().contains(searchString))
			{
				resourceMatched.add(resource);
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
