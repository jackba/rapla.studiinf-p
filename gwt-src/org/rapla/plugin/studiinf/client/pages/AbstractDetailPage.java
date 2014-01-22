package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.ui.QRBox;

import com.google.gwt.user.client.Window;
import com.google.gwtjsonrpc.common.AsyncCallback;

public abstract class AbstractDetailPage extends AbstractPage {

	public String id ="";
	protected QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	abstract public boolean hasDefaultQrBox();


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		if (this.hasDefaultQrBox() == true){
			qrBox.setHash(getHistoryKey()+"/"+getId());
		}
		handleId(id);
	}

	@Override
	public void init() {
		super.init();
		if (this.hasDefaultQrBox() == true){
			qrBox.getElement().getStyle().setProperty("top", "45vh");
			this.add(qrBox);
		}
		

		
	}
	
	@Override
	protected void refresh() {
		super.refresh();
		
	}

	protected void handleId(final String id){
//		Window.alert(id+": ?");
		ServiceProvider.getResource(id, new AsyncCallback<ResourceDetail>() {
				@Override
				public void onSuccess(ResourceDetail arg0) {
//					Window.alert(id+": " + arg0.toString());
					handleRessource(id, arg0);
				}

				@Override
				public void onFailure(Throwable arg0) {
					//Window.alert("JSON fail: "+arg0.toString());
				}
		});
	};
	
	abstract protected void handleRessource(String id, ResourceDetail resource);

}
