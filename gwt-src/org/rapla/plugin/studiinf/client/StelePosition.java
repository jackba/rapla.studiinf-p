package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.Window;
/**
 * 
 * Static class that provides the position of the Stele to load correct room-locations
 *
 */
public class StelePosition {
	
	public static final String SteleParameterName = "stele";
	public static final String SteleLeft = "L";
	public static final String SteleMiddle = "";
	public static final String SteleRight = "R";
	
	private static String stelePosition;
	
	static{
		String stelePositionParam = Window.Location.getParameter(SteleParameterName);
		if(stelePositionParam != null){
			stelePosition = stelePositionParam.toUpperCase();
		}else{
			stelePosition = SteleMiddle;
		}
	}
	
	/**
	 * Returns the prefix representing the position of the stele
	 * @return position of the stele
	 */
	public static String getStelePosition(){
		return stelePosition;
	}

	/**
	 * adds the prefix representing the position of the stele to an image URL
	 * @param imageURL the URL to enhance
	 * @return URL with the position of the stele
	 */
	public static String enhanceImageURL(String imageURL){
		String prefix = imageURL.substring(0, imageURL.lastIndexOf("/")+1);
		String suffix = imageURL.substring(imageURL.lastIndexOf("/")+1);
		return prefix + getStelePosition() + suffix;
		
	}

}
