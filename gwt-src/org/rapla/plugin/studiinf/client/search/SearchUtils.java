package org.rapla.plugin.studiinf.client.search;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;

import com.google.gwt.i18n.client.LocaleInfo;


public class SearchUtils {	
	
	public static String getServiceLocale(){
		return  LocaleInfo.getCurrentLocale().getLocaleName();
	}

	public static List<ResourceDescription> startsWithSearchTerm(int searchTerm, String searchString, List<ResourceDescription> resources)
	{
List<ResourceDescription> resourceMatched = new LinkedList<ResourceDescription>();
		
		for(ResourceDescription resource : resources)
		{
			
			
			if(resource.getSearchTerms().size() > searchTerm)
			{
			if(clearSearchString(resource.getSearchTerms().get(searchTerm)).startsWith(clearSearchString(searchString)))
			{
				resourceMatched.add(resource);
			}
			}
		}
		return resourceMatched;
	}
	
	public static List<ResourceDescription> startsWithName(String searchString, List<ResourceDescription> resources)
	{
List<ResourceDescription> resourceMatched = new LinkedList<ResourceDescription>();
		
		for(ResourceDescription resource : resources)
		{
			String s = resource.getName();
			if(clearSearchString(s).startsWith(clearSearchString(searchString)))
			{
				resourceMatched.add(resource);
			}
		}
		return resourceMatched;
	}

	public static List<ResourceDescription> containsSearchTerm(int searchTerm, String searchString, List<ResourceDescription> resources)
	{
List<ResourceDescription> resourceMatched = new LinkedList<ResourceDescription>();
		
		for(ResourceDescription resource : resources)
		{
			if(resource.getSearchTerms().size() > searchTerm)
			{
			String s = resource.getSearchTerms().get(searchTerm);
			if(clearSearchString(s).contains(clearSearchString(searchString)))
			{
				resourceMatched.add(resource);
			}
		}
		}
		return resourceMatched;
	}

	public static List<ResourceDescription> containsName(String searchString, List<ResourceDescription> resources)
	{
List<ResourceDescription> resourceMatched = new LinkedList<ResourceDescription>();
		
		for(ResourceDescription resource : resources)
		{
			String s = resource.getName();
			if(clearSearchString(s).contains(clearSearchString(searchString)))
			{
				resourceMatched.add(resource);
			}
		}
		return resourceMatched;
	}
	
	/*public static void alertSearchTerms(List<ResourceDescriptor> resources){
		for(ResourceDescriptor resource : resources){
			int id = 0;
			for(String term : resource.getSearchTerms()){
				Window.alert(id+ " " + term);
				id++;
			}
		}
	}*/
	
	public static String clearSearchString(String searchString)
	{
		searchString = searchString.toLowerCase();
		searchString = searchString.replace("ä", "ae");
		searchString = searchString.replace("ü", "ue");
		searchString = searchString.replace("ö", "oe");
		searchString = searchString.replace("ß", "ss");
		
		String clearedString = searchString;
		
		return clearedString;
		
	}
	
	public static ResourceDescription byId(String searchString, List<ResourceDescription> resources)
	{
		
		for(ResourceDescription resource : resources)
		{
			String s = resource.getId();
			if(clearSearchString(s).equals(clearSearchString(searchString)))
			{
				return resource;
			}
		}
		return null;
	}
}
