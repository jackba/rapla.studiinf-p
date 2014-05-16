package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.Window;

public class DisplayMode {
	
	private static boolean mobile = false;
	
	public static final String DisplayParameterName = "display";
	public static final String SteleMode = "stele";
	public static final String MobileMode = "mobile";
	
	static{
		String displayMode = Window.Location.getParameter(DisplayMode.DisplayParameterName);
		if(displayMode != null && displayMode.equals(DisplayMode.SteleMode)){
			DisplayMode.mobile = false;
		}else{
			DisplayMode.mobile = true;
		}
		
	}
	
	public static boolean isMobile(){
		return mobile;
	}
	public static boolean isStele(){
		return !mobile;
	}
}
