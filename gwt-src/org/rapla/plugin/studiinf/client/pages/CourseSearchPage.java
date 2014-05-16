package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.IconProvider;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseSearch;
import org.rapla.plugin.studiinf.client.ui.ResultButton;


public class CourseSearchPage extends AbstractSearchPage {
	
	
	
	public static final String ResourceType = "courses";

	public CourseSearchPage() {
		super(true, true,true,IconProvider.Courses,Navigation.courseDetail, DisplayMode.isMobile());
	}

	@Override
	public String getTitle() {
		return Studiinf.i18n.courseSearchPage();
	}

	@Override
	public String getHistoryKey() {
		return "course";
	}


	@Override
	protected void handleSearch(String searchTerm) {
		new CourseSearch(searchTerm, this);
	}

	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		clearResult();
		for(ResourceDescription course : ressourcesMatched)
		{
			addResult(new ResultButton(course.getName(), Navigation.courseDetail, course.getId(), IconProvider.Courses, this));
		}
		refresh();
	}

	@Override
	public String getResourceType() {
		return ResourceType;
	}

	@Override
	AbstractPage getOrganisationType() {
		return Navigation.organisationChartCourse;
	}


}
