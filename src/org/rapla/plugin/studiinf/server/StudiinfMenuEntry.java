package org.rapla.plugin.studiinf.server;

import org.rapla.framework.RaplaContext;
import org.rapla.servletpages.DefaultHTMLMenuEntry;

public class StudiinfMenuEntry extends DefaultHTMLMenuEntry 
{
	public StudiinfMenuEntry(RaplaContext context) {
		super(context);
	}
		
	@Override
	public String getName() {
		return "Studiinf-Client";
	}
	@Override
	public String getLinkName() {
		return "studiinf.html";
	}
		
}
