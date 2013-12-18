package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public class ModalSpinner {
	private static final Element modalElement = Document.get().getElementById("loadingSpinner");
	
	public static void show(){
		modalElement.getStyle().setProperty("display", "block");
	}

	public static void hide(){
		modalElement.getStyle().setProperty("display", "none");
	}
}
