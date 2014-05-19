package org.rapla.plugin.studiinf.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.user.client.DOM;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;

/**
 * Icon to display on buttons or on pages in general
 *
 */
public class FontIcon extends Widget {
	
	
	public static class Position{
		public static Position LEFT = new Position();
		public static Position RIGHT = new Position();
		public static Position BOTH = new Position();
	}
	public static final String MISSING_MAP ="img/KeineKarte.png";
	public static final FontIcon Persons = new FontIcon("icon-Persons");
	public static final FontIcon Phone = new FontIcon("icon-Phone");
	public static final FontIcon Organigram = new FontIcon("icon-Organigram");
	public static final FontIcon No_Map = new FontIcon("icon-No_Map");
	public static final FontIcon Next = new FontIcon("icon-Next");
	public static final FontIcon Home = new FontIcon("icon-Home");
	public static final FontIcon Email = new FontIcon("icon-Email");
	public static final FontIcon Additional_Information = new FontIcon("icon-Additional_Information");
	public static final FontIcon Barrier_Free = new FontIcon("icon-Barrier-Free");
	public static final FontIcon Calendar = new FontIcon("icon-Calendar");
	public static final FontIcon Course_Of_Studies = new FontIcon("icon-Course_Of_Studies");
	public static final FontIcon Courses = new FontIcon("icon-Courses");
	public static final FontIcon Down = new FontIcon("icon-Down");
	public static final FontIcon World = new FontIcon("icon-World");
	public static final FontIcon Up = new FontIcon("icon-Up");
	public static final FontIcon Type_Of_Room = new FontIcon("icon-Type_Of_Room");
	public static final FontIcon Rooms = new FontIcon("icon-Rooms");
	public static final FontIcon QR_Handy = new FontIcon("icon-QR_Handy");
	public static final FontIcon Previous = new FontIcon("icon-Previous");
	public static final FontIcon Delete = new FontIcon("icon-Delete");
	public static final FontIcon Enter = new FontIcon("icon-Enter");
	public static final FontIcon Search = new FontIcon("icon-Loupe");
	public static final FontIcon PoI = new FontIcon("icon-PoI");
	
	
	
	
	public FontIcon() {
		Element spanElement = DOM.createSpan();
		setElement(spanElement);
	}

	public FontIcon(String url) {
		this();
		setUrl(url);
	}
	@Override
	public void setPixelSize(int width, int height) {
		this.getElement().getStyle().setFontSize(Math.max(width, height), Unit.PX);
		super.setPixelSize(width, height);
	}

	public void setUrl(SafeUri url) {
		setUrl(url.asString());
	}
	

	public void setUrl(String url) {
		if(url.startsWith("icon-")){
			getElement().setClassName(url);
		}
	}
	public String getUrl() {
		return getElement().getClassName();	
	}
	
	
}
