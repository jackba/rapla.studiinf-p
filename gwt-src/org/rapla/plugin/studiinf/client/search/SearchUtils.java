package org.rapla.plugin.studiinf.client.search;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;

import com.google.gwt.i18n.client.LocaleInfo;

/**
 * 
 * @author StudiInf
 *
 * Contains all needed search tools for the different search classes
 */
public class SearchUtils {	
	
	public static String getServiceLocale(){
		return  LocaleInfo.getCurrentLocale().getLocaleName();
	}

	/**
	 * Checks if a search term resourceDescription starts with the given search string
	 * @param searchTerm  search term of the resource to search in
	 * @param searchString Term to search
	 * @param resources list of resources to search in
 	 * @return list of resourcesDescriptions that match the search string
	 */
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
	
	/**
	 * Checks if the name starts with the given search string
	 * @param searchString term to search
	 * @param resources resources list of resources to search in
	 * @return list of ResourcesDescriptions that match the search string
	 */
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

	/**
	 * Checks if a search term of a resource contains the given search string.
	 * @param searchTerm  search term of the resource to search in
	 * @param searchString Term to search
	 * @param resources list of resources to search in
 	 * @return list of ResourcesDescriptions that match the search string
	 */
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

	/**
	 * 
	 * Checks if the name contains given search string
	 * @param searchString term to search
	 * @param resources resources list of resources to search in
	 * @return list of ResourcesDescriptions that match the search string
	 */
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
	
	
	/**
	 * Replaces umlauts and ß with normalized characters
	 * @param searchString term to search with
	 * @return cleaned search string
	 */
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
	
	/**
	 * Checks if search string is equal to an ID of a resource
	 * @param searchString term to search with
	 * @param resources resources list of resources to search in
	 * @return resource with given ID
	 */
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
