package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.rest.RemoteLogger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.ServiceDefTarget;



public class RessourceLogger {
	/*
	private static RemoteLogger service = null;
	
	private static final String localeStorageClientId = "org.rapla.plugin.studiinf.clientId";
		
	private static String clientId = null;
	
	private static RemoteLogger getService() {
		if(service == null){
			service = GWT.create(RemoteLogger.class);
			String address = GWT.getModuleBaseURL() + "../rapla/json/" + RemoteLogger.class.getName();
		 	((ServiceDefTarget) service).setServiceEntryPoint(address);
		}
		return service;
	}
	
	private static String generateClientId(){
		String tmpClientId = "";
		 String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ123456789+-_";
		 for (int i=0; i<64; i++)
	      {
	          int index = (int)(Random.nextDouble()*letters.length());
	          tmpClientId += letters.substring(index, index+1);
	      }
		return tmpClientId;
	}
	
	private static String getClientId(){
		if(clientId == null){
			Storage localStorage = Storage.getLocalStorageIfSupported();
			if(localStorage != null){
				String tmpClientId = localStorage.getItem(localeStorageClientId);
				if(tmpClientId != null){
					clientId =  tmpClientId;
				}else{
					clientId = generateClientId();
				}
			}else{
				clientId = generateClientId();
			}
		}
		return clientId;
	}
	
	public static void logRessource(AbstractPage targetPage, AbstractPage sourcePage, String searchString) {
		if(DisplayMode.isStele()){
			String clientId = getClientId();
			String currentPage = targetPage.getHistoryKey();
			String previousPage = sourcePage.getHistoryKey();
			getService().info("csvaccesslog",clientId+';'+currentPage+';'+searchString+';'+previousPage);
		}
	}
	
	*/
	
}
