package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.user.client.ui.FlowPanel;

public class FreeRoomTable extends ResultTable {

	public FreeRoomTable(FlowPanel footerPanel, int columns, int maxRows) {
		super(footerPanel, (int) (Math.ceil(columns/2)*2), maxRows);
		for(int i = 0; i < columns;i = i+2){
			this.getColumnFormatter().setWidth(i, "60%");
			this.getColumnFormatter().setWidth(i+1, "40%");
		}
	}

}
