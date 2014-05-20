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
	
	public static final String SteleParameterName = "stele";
	public static final String SteleLeft = "L";
	public static final String SteleMiddle = "";
	public static final String SteleRight = "R";
	
	public static final String SteleMode = "stele";
	public static final String MobileMode = "mobile";
	
	private static String stelePosition;
	
	
	static{
		String displayMode = Window.Location.getParameter(DisplayMode.DisplayParameterName);
		if(displayMode != null && displayMode.equals(DisplayMode.SteleMode)){
			DisplayMode.mobile = false;
		}else{
			DisplayMode.mobile = true;
		}
		String stelePositionParam = Window.Location.getParameter(DisplayMode.SteleParameterName);
		if(stelePositionParam != null){
			stelePosition = stelePositionParam;
		}else{
			stelePosition = SteleMiddle;
		}
		
	}
	
	public static boolean isMobile(){
		return mobile;
	}
	public static boolean isStele(){
		return !mobile;
	}
	
	public static String getStelePosition(){
		return stelePosition;
	}
	
	public static String enhanceImageURL(String imageURL){
		String prefix = imageURL.substring(0, imageURL.lastIndexOf("/")+1);
		String suffix = imageURL.substring(imageURL.lastIndexOf("/")+1);
		return prefix + getStelePosition() + suffix;
		
	}
	
}
