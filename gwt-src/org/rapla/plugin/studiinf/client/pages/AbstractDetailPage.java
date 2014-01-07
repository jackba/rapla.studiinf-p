package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.ui.QRBox;

public abstract class AbstractDetailPage extends AbstractPage {

	abstract protected void handleId(String id);
	public String id ="";
	private QRBox qrBox = new QRBox(getHistoryKey()+"/"+getId());
	abstract public boolean hasDefaultQrBox();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
		this.remove(qrBox);
		if (this.hasDefaultQrBox() == true){
			qrBox = new QRBox(getHistoryKey()+"/"+getId());
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


}
