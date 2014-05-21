package org.rapla.plugin.studiinf.client.pages;

import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ui.PageFooter;
import org.rapla.plugin.studiinf.client.ui.PageHeader;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.ComplexPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Template for all pages, which adds header and footer to the pages.
 *
 */
public abstract class AbstractPage extends ComplexPanel{
	protected PageHeader header;
	protected PageFooter footer;
	protected static ResetTimer timer = new ResetTimer();
	

	  public  AbstractPage() {
	    this(DOM.createDiv());
	    
	    Navigation.add(this);
	    
	    this.setStyleName("page");
	    this.addStyleName("page-"+getHistoryKey());
	    
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
	  
	  /**
	   * Initializes page and adds needed components
	   */
	   public void init(){
		    add(header);
		    add(footer);
		    footer.init();
	   };
	   
		
		@Override
		public void add(Widget child) {
			this.add(child, (Element) this.getElement());
		};
		/**
		 * Returns the HistoryKey of the page for Navigation
		 * @return HistoryKey for this page
		 */
	   abstract public String getHistoryKey();
	   
	   /**
	    * Returns the title of the page to show in header
	    * @return the title of the page
	    */
	   abstract public String getTitle();

	   /**
	    * Refreshs the elements of the page and resets the timer to go back to homepage
	    */
	   protected void refresh(){
		   header.refresh();
		   if (DisplayMode.isStele()){
			   timer.cancel();
			   timer.schedule(300000);
		   }
	   }
	   /**
	    * triggered on show of the page
	    */
	   public void onShow(){
		   refresh();
	   }
	   

	   

	   
}
