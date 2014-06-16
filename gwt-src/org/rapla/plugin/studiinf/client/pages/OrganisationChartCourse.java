package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;
import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.ui.FontIcon;
import org.rapla.plugin.studiinf.client.ui.ResultButton;

public class OrganisationChartCourse extends OrganisationChart {

	@Override
	public String getHistoryKey() {
		return "orgCourse";
	}
	@Override
	public void updateResults(List<ResourceDescription> ressourcesMatched) {
		organigram.clearResults();	
		for(ResourceDescription course : ressourcesMatched)
		{
			organigram.addResult(new ResultButton(course.getName(), Navigation.courseDetail, course.getId(), FontIcon.Courses, this));
		}
		organigram.refresh();
	}
	@Override
	public String getResourceType() {
		return SearchPageCourse.ResourceType;
	}
	
}
