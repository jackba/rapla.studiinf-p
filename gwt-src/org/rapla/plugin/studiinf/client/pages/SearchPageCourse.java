package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.search.CourseSearch;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

/**
 * 
 * @author Team StudiInf
 * 
 * Page for searching courses
 */
public class SearchPageCourse extends AbstractSearchPage {
	
	
	
	public static final String ResourceType = "courses";

	
	public SearchPageCourse() {
		super(true, true,true,FontIcon.Courses,Navigation.courseDetail);
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
			addResult(new ResultButton(course.getName(), Navigation.courseDetail, course.getId(), FontIcon.Courses, this));
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
