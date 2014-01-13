package org.rapla.plugin.studiinf.client;

import com.google.gwt.storage.client.Storage;

public class LocalStorage {

	private Storage localStorage = Storage.getLocalStorageIfSupported();
	private String count = "";

	public void writeStorage(String tagetID){
		count = count + 1;
		localStorage.setItem(tagetID, count);
	}
	
	public String readStorage(String targetID){
		return localStorage.getItem(targetID);
	}
}
