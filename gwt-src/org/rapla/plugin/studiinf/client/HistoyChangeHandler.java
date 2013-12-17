package org.rapla.plugin.studiinf.client;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class HistoyChangeHandler implements ValueChangeHandler<String> {

	@Override
	public void onValueChange(ValueChangeEvent<String> event) {
			Navigation.goToPage(event.getValue());
	}

}
