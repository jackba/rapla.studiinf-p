package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.Window;

public class StelePosition {
	
	public static final String SteleParameterName = "stele";
	public static final String SteleLeft = "L";
	public static final String SteleMiddle = "";
	public static final String SteleRight = "R";
	
	static{
		String stelePositionParam = Window.Location.getParameter(SteleParameterName);
		if(stelePositionParam != null){
			stelePosition = stelePositionParam.toUpperCase();
		}else{
			stelePosition = SteleMiddle;
		}
	}
	
	static String stelePosition;
	public static String getStelePosition(){
		return stelePosition;
	}

}
