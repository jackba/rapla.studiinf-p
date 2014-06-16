package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.DisplayMode;

import com.google.gwt.user.client.ui.FlowPanel;

/**
 * A TileContainer can contain several Tiles
 *
 */
public class TileContainer extends FlowPanel {
	public TileContainer() {
		super();
		this.addStyleName("tileContainer");
		if (DisplayMode.isMobile()){
			this.addStyleName("mobile");
		}
	}
	
}
