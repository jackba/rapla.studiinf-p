package org.rapla.plugin.studiinf.client.search;

import java.util.Collection;

import org.rapla.plugin.freiraum.common.ResourceDetail;

import com.google.gwt.user.client.Window;

public class RoomDescriptor {
	
	private ResourceDetail room;
	private Collection<String> keys;
	
	public RoomDescriptor(ResourceDetail room) {
		this.room = room;
		keys = room.getKeys();
		Window.alert(keys.toString());
	}

}
