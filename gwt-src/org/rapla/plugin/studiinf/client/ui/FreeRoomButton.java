package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class FreeRoomButton extends ResultButton {
	
	private final Widget freeUntil;
	private List<Widget> cellList;
	
	public FreeRoomButton(Event e) {
		super(e.getResources().get(0).getName().toString(), Navigation.roomDetail,e.getResources().get(0).getId(), new Image(IconProvider.ROOMS));
		freeUntil = new IconButton(Studiinf.i18n.freeUntil() + e.getEnd(),new Image(IconProvider.CALENDAR));
	}

	@Override
	public List<Widget> getCellObjects() {
		if(cellList == null){
			cellList = new LinkedList<Widget>();
			cellList.add(this);
			cellList.add(freeUntil);
		}
		return cellList;
	}
}
