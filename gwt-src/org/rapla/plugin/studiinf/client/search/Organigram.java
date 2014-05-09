package org.rapla.plugin.studiinf.client.search;

import java.util.List;

import org.rapla.plugin.freiraum.common.CategoryDescription;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.rest.gwtjsonrpc.common.AsyncCallback;

import com.google.gwt.user.client.Window;

public class Organigram implements AsyncCallback<List<CategoryDescription>>{

	public Organigram(){
		
	}
	
	public void getOrganigram(String categoryId){
		ServiceProvider.getOrganigram(categoryId, this);

	}

	@Override
	public void onFailure(Throwable caught) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSuccess(List<CategoryDescription> result) {
		List<CategoryDescription> resultOrganigram = result;
		Window.alert(resultOrganigram.toString());
//		String id = resultOrganigram.get(0).getId();
//		
//		if (id != null)
//		{
//			this.getOrganigram(id);
//		}
	}
}
