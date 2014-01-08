package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescriptor;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

import com.google.gwt.user.client.ui.Image;


public class CourseSearchPage extends AbstractSearchPage {

	@Override
	public String getTitle() {
		return Studiinf.i18n.courseSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "course";
	}

	@Override
	public boolean hasOrganigramm() {
		return true;
	}

	@Override
	protected void handleSearch(String searchTerm) {
		new CourseSearch(searchTerm, this);
	}

	@Override
	public void updateResults(List<ResourceDescriptor> ressourcesMatched) {
		clearResult();
		int counter = 1;
		for(ResourceDescriptor course : ressourcesMatched)
		{
			addResult(new ResultButton(counter, course.getName(), Navigation.courseDetail, course.getId(), new Image(IconProvider.COURSES)));
			counter++;
		}
	}

}
