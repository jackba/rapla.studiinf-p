package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.Window;

/**
 * 
 * @author Team StudiInf
 *
 * The Displaymode defines if the application runs on a "Stele" or on a mobile device
 */
public class DisplayMode {
	
	private static boolean mobile = false;
		
	public static final String DisplayParameterName = "display";
	public static final String SteleMode = "stele";
	public static final String MobileMode = "mobile";
	
	
	
	static{
		String displayMode = Window.Location.getParameter(DisplayMode.DisplayParameterName);
		if(displayMode != null && displayMode.toLowerCase().equals(DisplayMode.SteleMode.toLowerCase())){
			DisplayMode.mobile = false;
		}else{
			DisplayMode.mobile = true;
		}
		
		
	}
	/**
	 * Returns whether the application is running on mobile mode
	 * @return mobile mode is active
	 */
	public static boolean isMobile(){
		return mobile;
	}
	
	/**
	 * Returns whether the application is running on stele mode
	 * @return stele mode is active
	 */
	public static boolean isStele(){
		return !mobile;
	}
	
}
