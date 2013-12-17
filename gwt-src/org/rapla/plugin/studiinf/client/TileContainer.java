package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

public class TileContainer extends FlowPanel {
	public TileContainer() {
		super();
		this.addStyleName("tileContainer");
	}
	
	public void add(Tile tile){
		this.add((Button) tile);
		/* TODO: Update the size of the tiles depending on their power */
	}
}
