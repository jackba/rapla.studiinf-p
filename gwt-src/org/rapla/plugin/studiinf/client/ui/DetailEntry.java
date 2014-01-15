package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class DetailEntry extends FlowPanel {
	
	public DetailEntry(String label, String value)
	{
		super();
		Label key = new Label(label+":");
		Label info = new Label(value);
		key.setStyleName("detailKey");
		info.setStyleName("detailValue");
		
		this.add(key);
		this.add(info);
	}

}
