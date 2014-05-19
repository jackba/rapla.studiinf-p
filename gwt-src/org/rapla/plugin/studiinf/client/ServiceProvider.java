package org.rapla.plugin.studiinf.client;

import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.search.SearchUtils;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 * The Service Provider implements all methods offered by the rapla freiraum API
 *
 */
public class ServiceProvider {

	private static RaplaJsonService service = null;

	/**
	 * created a GWT Service
	 * @return created JSON service
	 */
	private static RaplaJsonService getService() {
		if(service == null){
			service = GWT.create(RaplaJsonService.class);
			String address = GWT.getModuleBaseURL() + "../rapla/json/" + RaplaJsonService.class.getName();
		 	((ServiceDefTarget) service).setServiceEntryPoint(address);
		}
		return service;
	}

	/**
	 * Gets all resources depending on the resource type and the categoryID.
	 * @param resourceType type of resources that should be returned
	 * @param callback
	 */
	public static void getResources(String resourceType, String categoryId, final AsyncCallback<List<ResourceDescription>> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		getService().getResources(resourceType, categoryId, serviceLocale).get(callback);
		
	}

	/**
	 * Gets details to a specific resource id
	 * @param resourceId ID of the resource to get details for
	 * @param callback
	 */
	public static void getResource(String resourceId, AsyncCallback<ResourceDetail> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		getService().getResource(resourceId, serviceLocale).get(callback);
	}

	/**
	 * Gets the organigram to a specific category
	 * @param categoryId
	 * @param callback
	 */
	public static void getOrganigram(String categoryId,	final AsyncCallback<List<CategoryDescription>> callback) {
		getService().getOrganigram(categoryId, SearchUtils.getServiceLocale()).get(callback);
		
	}

	/**
	 * Gets free resources, resources without events in a specified time span
	 * @param start relevant start time
	 * @param end relevant end time
	 * @param resourceType type of relevant resources.
	 * @param callback
	 */
	public static void getFreeResources(String start, String end, String resourceType, final AsyncCallback<List<Event>> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		getService().getFreeResources(start, end, resourceType, serviceLocale).get(callback);
		
	}

	/**
	 * Gets all events for a resource depending on the given start and end date and time
	 * @param start relevant start time
	 * @param end relevant end time
	 * @param resourceId ID of resource to find events for
	 * @param callback
	 */
	public static void getEvents(String start, String end, String resourceId, final AsyncCallback<List<Event>> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		getService().getEvents(start, end, resourceId, serviceLocale).get(callback);
		
	}

}
