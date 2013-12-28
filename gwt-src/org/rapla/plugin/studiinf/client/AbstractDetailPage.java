package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;
import org.rapla.plugin.studiinf.client.ui.QRBox;

public abstract class AbstractDetailPage extends AbstractPage {

	abstract public void handleId(String id);
	private QRBox qrBox = new QRBox();
	
	@Override
	public void init() {
		super.init();
		qrBox.setStyleName("qrBox");
		this.add(qrBox);

		
	}
	
	@Override
	protected void refresh() {
		super.refresh();
		
	}

}
