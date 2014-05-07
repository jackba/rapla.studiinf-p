package org.rapla.plugin.studiinf.client.ui;


/**
 * Table, which contains information for free rooms
 *
 */
public class FreeRoomTable extends ResultTable {

	public FreeRoomTable(AccessibilityRow accessibilityRow, int columns, int maxRows) {
		super(accessibilityRow, (int) (Math.ceil(columns/2)*2), maxRows);
		for(int i = 0; i < columns;i = i+2){
			this.getColumnFormatter().setWidth(i, "60%");
			this.getColumnFormatter().setWidth(i+1, "40%");
		}
	}

}
