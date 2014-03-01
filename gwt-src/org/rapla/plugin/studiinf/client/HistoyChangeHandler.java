package org.rapla.plugin.studiinf.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

/**
 * 
 *
 */
public class HistoyChangeHandler implements ValueChangeHandler<String> {

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
			String navigationUrl =event.getValue();
			int sepIndex = navigationUrl.indexOf("/");
			if(sepIndex >= 0){
				Navigation.goToPage(navigationUrl.substring(0, sepIndex ),Navigation.idForService(navigationUrl.substring( sepIndex +1)));
			}else{
				Navigation.goToPage(navigationUrl);
			}
			
	}

}
