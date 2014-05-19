package org.rapla.plugin.studiinf.client;

/**
 * Picture which contains a ImageURL
 *
 */
public class Picture {

	public static String getImageURL(String roomNumber) {
		return "img/wayDescriptionImgs/" + roomNumber + ".png";
	}
	
}
