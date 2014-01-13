package org.rapla.plugin.studiinf.client;

import javax.swing.Icon;

import org.rapla.gui.images.Images;

import com.google.gwt.resources.client.ImageResource;

public class Picture {

	public static String getImageURL(String roomNumber) {
		return "img/wayDescriptionImgs/" + roomNumber + ".png";
	}
	
}
