package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;

public class DetailEntry extends FlowPanel {
	
	public DetailEntry(String label, String value)
	{
		super();
		Label key = new Label(label+":");
		SafeHtml htmlText = SafeHtmlUtils.fromTrustedString(value);
		HTMLPanel info = new HTMLPanel(htmlText);
		key.setStyleName("detailKey");
		info.setStyleName("detailValue");
		
		this.add(key);
		this.add(info);
	}

}
