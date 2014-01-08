package org.rapla.plugin.studiinf.client.pages;
import org.rapla.plugin.freiraum.common.ResourceDetail;
import org.rapla.plugin.studiinf.client.search.CourseDescriptor;

public class DetailPageCourse extends AbstractDetailPage {
	
	private String courseName;

	@Override
	public String getHistoryKey() {
		return "courseDetail";
	}

	@Override
	public String getTitle() {
		if(courseName == null){
			courseName = "";
		}
		return courseName;
	}


	
	@Override
	protected void refresh() {
		super.refresh();
//		nameBtn.setText(roomNumber);
	}


	@Override
	public boolean hasDefaultQrBox() {
		return true;
	}


	@Override
	protected void handleRessource(String id, ResourceDetail resource) {
		courseName = "Kurs "+id;
		CourseDescriptor cd = new CourseDescriptor(resource);
		refresh();
		
	}

}
