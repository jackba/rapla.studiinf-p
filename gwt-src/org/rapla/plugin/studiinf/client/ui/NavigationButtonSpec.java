package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.pages.AbstractPage;

public interface NavigationButtonSpec {
	public String getTargetId();
	public void setTargetId(String targetId);
	public AbstractPage getTargetPage();
	public void setTargetPage(AbstractPage targetPage);
	public int getNumber();
	public void setNumber(int number);
	public FontIcon getIcon();
	public void setIcon(FontIcon icon);
	public void setSize(double viewPortSize);
	public void setEnabled(boolean enabled);
	public boolean getEnabled();
	
}
