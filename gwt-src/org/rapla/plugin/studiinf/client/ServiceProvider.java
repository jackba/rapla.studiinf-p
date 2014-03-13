package org.rapla.plugin.studiinf.client;

import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.freiraum.common.RaplaJsonService;
import org.rapla.plugin.freiraum.common.RaplaJsonService.CategoryDescriptionList;
import org.rapla.plugin.freiraum.common.RaplaJsonService.EventList;
import org.rapla.plugin.freiraum.common.RaplaJsonService.ResourceDescriptionList;
import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.search.SearchUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwtjsonrpc.common.AsyncCallback;

/**
 * 
 *
 */
public class ServiceProvider {

	private static RaplaJsonService service = null;

	/**
	 * 
	 * @return
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
	 * 
	 * @param resourceType
	 * @param callback
	 */
	public static void getResources(String resourceType, final AsyncCallback<List<ResourceDescription>> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		AsyncCallback<ResourceDescriptionList> callbackWrapper = new AsyncCallback<RaplaJsonService.ResourceDescriptionList>() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(ResourceDescriptionList result) {
				List<ResourceDescription> list = result.get();
				callback.onSuccess(list);
			}
		};
		getService().getResources(resourceType, null, serviceLocale).get(callbackWrapper);
		
	}

	/**
	 * 
	 * @param resourceId
	 * @param callback
	 */
	public static void getResource(String resourceId, AsyncCallback<ResourceDetail> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		getService().getResource(resourceId, serviceLocale).get(callback);
	}

	/**
	 * 
	 * @param categoryId
	 * @param callback
	 */
	public static void getOrganigram(String categoryId,	final AsyncCallback<List<CategoryDescription>> callback) {
		AsyncCallback<CategoryDescriptionList> callbackWrapper = new AsyncCallback<RaplaJsonService.CategoryDescriptionList>() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(CategoryDescriptionList result) {
				callback.onSuccess(result.get());
			}
		};
		getService().getOrganigram(categoryId, SearchUtils.getServiceLocale()).get(callbackWrapper);
		
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param resourceType
	 * @param callback
	 */
	public static void getFreeResources(String start, String end, String resourceType, final AsyncCallback<List<Event>> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		AsyncCallback<EventList> callbackWrapper =new AsyncCallback<RaplaJsonService.EventList>() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(EventList result) {
				callback.onSuccess(result.get());
				
			}
		};
		getService().getFreeResources(start, end, resourceType, serviceLocale).get(callbackWrapper);
		
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @param resourceId
	 * @param callback
	 */
	public static void getEvents(String start, String end, String resourceId, final AsyncCallback<List<Event>> callback) {
		String serviceLocale = SearchUtils.getServiceLocale();
		AsyncCallback<EventList> callbackWrapper =new AsyncCallback<RaplaJsonService.EventList>() {

			@Override
			public void onFailure(Throwable caught) {
				callback.onFailure(caught);
			}

			@Override
			public void onSuccess(EventList result) {
				callback.onSuccess(result.get());
				
			}
		};
		getService().getEvents(start, end, resourceId, serviceLocale).get(callbackWrapper);
		
	}

}
