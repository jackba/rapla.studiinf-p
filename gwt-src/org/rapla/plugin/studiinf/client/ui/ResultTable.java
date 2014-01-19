package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.IconProvider;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class ResultTable extends FlexTable {
	
	private List<ResultObject> results = new LinkedList<ResultObject>();
	private int columns;
	private int maxRows;
	private final FlowPanel footerPanel;
	private int page;
	private IconButton backButton = new IconButton("previous",new Image(IconProvider.LEFT));
	private IconButton nextButton = new IconButton("next",new Image(IconProvider.RIGHT));
	private Button backButtonBottom = new Button("<i class='fa fa-chevron-left'></i>");
	private Button nextButtonBottom = new Button("<i class='fa fa-chevron-right'></i>");
	
	public int getPage() {
		return page;
	}

	public IconButton getBackButton() {
		return backButton;
	}

	public IconButton getNextButton() {
		return nextButton;
	}

	public void setPage(int page) {
		if(page > 0 && page < getMaxPages()){
			this.page = page;
		
		}else if(page > 0){
			this.page = getMaxPages();
			
		}else{
			this.page = 0;
		}
			
	}

	public void nextPage(){
		setPage(getPage() + 1); 
	}

	public void previousPage(){
		setPage(getPage() - 1); 
	}

	public int getMaxPages() {
		return (int) Math.ceil(results.size() / (columns*maxRows));
	}

	public boolean hasPages(){
		return getMaxPages() > 1;
	}
	public boolean hasNextPage(){
		return hasPages() && getPage() < getMaxPages();
	}
	public boolean hasPreviousPage(){
		return hasPages() && getPage() > 0;
	}



	public ResultTable(FlowPanel footerPanel,int columns,int maxRows) {
	this.columns = columns;	
	this.maxRows = maxRows;
	this.footerPanel = footerPanel;
	this.page = 0;
	
	this.backButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			previousPage();
			refresh();
		}
	});
	this.nextButton.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			nextPage();
			refresh();
		}
	});
	
	this.backButtonBottom.setStyleName("backButtonBottom");
	this.nextButtonBottom.setStyleName("nextButtonBottom");
	this.backButtonBottom.setVisible(false);
	this.nextButtonBottom.setVisible(false);
	
	this.backButtonBottom.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			previousPage();
			refresh();
		}
	});
	this.nextButtonBottom.addClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			nextPage();
			refresh();
		}
	});
		
	}
	

	public void addResult(ResultObject result){
		results.add(result);
		result.setNumber(results.indexOf(result) + 1);
	}
	
	public boolean removeResult(ResultObject result) {
		boolean removed = results.remove(result);
		return removed;
	}
	
	public void clearResults() {
		results.clear();
		refresh();
	}
	
	public void refresh(){
		clear();
		footerPanel.clear();
		int count = 0;
		for (ResultObject result : results){
			for(Widget cell : result.getCellObjects()){
				if(Math.floor((count / columns))-(page*maxRows)>= maxRows){
					break;
				}
				if(count >= page*columns*maxRows){
				setWidget((int)(count / columns)-(page*maxRows), (int) count % columns, cell);
				}
				count++;
			}
			if(count >= page*columns*maxRows+1){
			footerPanel.add(result.getFooterButton());
			}
			if(Math.floor((count / columns))-(page*maxRows)>= maxRows){
				break;
			}
		}
		
		if(hasPages()){
			backButton.setEnabled(hasPreviousPage());
			nextButton.setEnabled(hasNextPage());
			
			backButtonBottom.setEnabled(hasPreviousPage());
			nextButtonBottom.setEnabled(hasNextPage());
			
			backButtonBottom.setVisible(hasPreviousPage());
			nextButtonBottom.setVisible(hasNextPage());
			
//			setWidget((int)((count / columns))-(page*maxRows), 0, backButton);
//			setWidget((int)((count / columns))-(page*maxRows), 1, nextButton);
			
	
		}
	}
	
	public List<Button> getButtonsBottom(){
		List<Button> buttons = new LinkedList<Button>();
		buttons.add(backButtonBottom);
		buttons.add(nextButtonBottom);
		return buttons;
	}
	
	


	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		setPage(0);
		refresh();
	}
}
