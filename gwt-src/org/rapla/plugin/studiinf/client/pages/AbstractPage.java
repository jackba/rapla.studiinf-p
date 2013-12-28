package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ui.PageFooter;
import org.rapla.plugin.studiinf.client.ui.PageHeader;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class AbstractPage extends ComplexPanel{
	private Widget header;
	private Widget footer;
	

	  public  AbstractPage() {
	    this(DOM.createDiv());
	    
	    Navigation.add(this);
	    
	    this.setStyleName("page");
	    
	    header = new PageHeader(this);
	    footer = new PageFooter(this);
	    
	   
	    
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
	  
	   public void init(){
		    add(header);
		    add(footer);
	   };
	   
		
		@Override
		public void add(Widget child) {
			this.add(child,(com.google.gwt.dom.client.Element) this.getElement());
		};
	
	   abstract public String getHistoryKey();
	   
	   abstract public String getTitle();
	   
	   
	   
}
