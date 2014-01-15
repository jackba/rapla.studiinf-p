package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.ServiceProvider;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.ui.QRBoxOLD;

import com.google.gwt.user.client.Window;
import com.google.gwtjsonrpc.common.AsyncCallback;

public abstract class AbstractDetailPage extends AbstractPage {

	public String id ="";
	private QRBoxOLD qrBox = new QRBoxOLD(getHistoryKey()+"/"+getId());
	abstract public boolean hasDefaultQrBox();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.remove(qrBox);
		if (this.hasDefaultQrBox() == true){
			qrBox = new QRBoxOLD(getHistoryKey()+"/"+getId());
			this.add(qrBox);
		}
		handleId(id);
	}

	@Override
	public void init() {
		super.init();
		qrBox.setStyleName("qrBox");
		if (this.hasDefaultQrBox() == true){
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
					Window.alert("JSON fail: "+arg0.toString());
				}
		});
	};
	
	abstract protected void handleRessource(String id, ResourceDetail resource);

}
