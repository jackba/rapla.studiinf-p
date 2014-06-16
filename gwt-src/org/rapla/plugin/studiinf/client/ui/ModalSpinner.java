package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

/**
 * Shows or hides the busy-indicator
 *
 */
public class ModalSpinner {
	private static final Element modalElement = Document.get().getElementById("loadingSpinner");
	/**
	 * Shows the busy-indicator
	 */
	public static void show(){
		modalElement.getStyle().setProperty("display", "block");
	}
	
	/**
	 * Hides the busy-indicator
	 */
	public static void hide(){
		modalElement.getStyle().setProperty("display", "none");
	}
}
