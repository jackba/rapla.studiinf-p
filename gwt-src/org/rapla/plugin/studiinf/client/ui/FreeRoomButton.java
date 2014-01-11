package org.rapla.plugin.studiinf.client.ui;

import java.util.List;

import org.rapla.plugin.freiraum.common.Event;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class FreeRoomButton extends ResultButton {
	
	private final Label freeUntil;
	
	public FreeRoomButton(Event e) {
		super(e.getResources().get(0).getName().toString(), Navigation.roomDetail,e.getResources().get(0).getId(), new Image(IconProvider.ROOMS));
		freeUntil = new Label("frei bis " + e.getEnd());
	}

	@Override
	public List<Widget> getCellObjects() {
		List<Widget> cells = super.getCellObjects();
		cells.add(freeUntil);
		Window.alert("get");
		return cells;
	}
}
