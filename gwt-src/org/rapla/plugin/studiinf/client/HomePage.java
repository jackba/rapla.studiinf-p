package org.rapla.plugin.studiinf.client;

import com.google.gwt.user.client.ui.HTML;

public class HomePage extends AbstractPage {
	
	public HomePage() {
		super();
		this.add(
		new HTML("<nav><ul>"
				+ "		<li class='big'><i class='fa fa-users'></i><span>"+i18n.courses()+"</span></li>"
				+ "		<li class='medium'><i class='fa fa-building-o'></i><span>"+i18n.rooms()+"</span></li>"
				+ "		<li class='small'><i class='fa fa-user'></i><span>"+i18n.people()+"</span></li>"
				+ "		<li class='small'><i class='fa fa-star'></i><span>"+i18n.pointsOfInterest()+"</span></li>"
				+ "	</ul></nav>"), getElement());
	}
	
	@Override
	public String getTitle() {
		return i18n.homeScreenTitle();
	}

}
