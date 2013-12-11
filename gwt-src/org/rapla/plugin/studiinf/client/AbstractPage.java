package org.rapla.plugin.studiinf.client;

import org.rapla.plugin.studiinf.client.i18n.I18n;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.*;

public abstract class AbstractPage extends ComplexPanel{
	
	private Widget header;
	private Widget footer;
	
	protected I18n i18n = GWT.create(I18n.class);
	
	@Override
	public void add(Widget child) {
		
		this.add(child, getElement());
	};
	

	  public  AbstractPage() {
	    this(DOM.createDiv());

	    DOM.setStyleAttribute(getElement(), "position", "absolute");
	    DOM.setStyleAttribute(getElement(), "overflow", "hidden");
	    DOM.setStyleAttribute(getElement(), "top", "0px");
	    DOM.setStyleAttribute(getElement(), "left", "0px");
	    DOM.setStyleAttribute(getElement(), "bottom", "0px");
	    DOM.setStyleAttribute(getElement(), "right", "0px");
	    DOM.setStyleAttribute(getElement(), "background", "#d6dadc");
	    
	    /*Dangerous Code!!!*/
	    if(header == null){
	    	header = new PageHeader(this);
	    }
	    	
	    /*Dangerous Code!!!*/
	    if(footer == null){
	    	footer = new PageFooter(this);
	    }
	    
	    add(header);
	    add(footer);
	    
	  }
	  
	  /**
	   * Creates an AbsolutePanel with the given element. This is protected so that
	   * it can be used by {@link RootPanel} or a subclass that wants to substitute
	   * another element. The element is presumed to be a &lt;div&gt;.
	   * 
	   * @param elem the element to be used for this panel
	   */
	  protected AbstractPage(Element elem) {
	    setElement(elem);
	  }
	  
	  
	  abstract public String getTitle();
	
}
