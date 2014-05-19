package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.rapla.plugin.studiinf.client.DisplayMode;
import org.rapla.plugin.studiinf.client.Studiinf;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 *
 */
public class ResultTable extends FlexTable {

	private double size;
	private List<ResultObject> results = new LinkedList<ResultObject>();
	private int columns;
	private int maxRows;
	private final AccessibilityRow accessibilityRow;
	private int page;
	private NavButton backButton = new NavButton(FontIcon.Up,FontIcon.Position.BOTH,Studiinf.i18n.previous(),null,null);
	private NavButton nextButton = new NavButton(FontIcon.Down,FontIcon.Position.BOTH,Studiinf.i18n.next(),null,null);
	//private NavButton backButtonBottom = new NavButton(IconProvider.Previous,null,null,null);
	//private NavButton nextButtonBottom = new NavButton(IconProvider.Next,null,null,null);

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page > 0 && page < getMaxPages()){
			this.page = page;
		
		}else if(page > 0){
			this.page = getMaxPages();
			
		}else{
			this.page = 0;
		}
		refresh();
			
	}
	public void setSize(){
		if(DisplayMode.isMobile()){
			size = 1;
		}else{
			size= 0.5;
		}
		
	}

	public void nextPage(){
		setPage(getPage() + 1); 
	}

	public void previousPage(){
		setPage(getPage() - 1); 
	}

	/**
	 * 
	 */
	public int getMaxPages() {
		int cells = 0;
		for(ResultObject result : results){
			cells = cells + result.getCellObjects().size();
		}
		return (int) Math.ceil(((double)cells) / (double)(maxRows*columns));
	}

	public boolean hasPages(){
		return getMaxPages() > 1;
	}
	public boolean hasNextPage(){
		return hasPages() && (getPage()+1) < getMaxPages();
	}
	public boolean hasPreviousPage(){
		return hasPages() && getPage() > 0;
	}

	public ResultTable(AccessibilityRow accessibilityRow,int columns,int maxRows) {
	this.columns = columns;	
	this.maxRows = maxRows;
	this.accessibilityRow = accessibilityRow;
	this.page = 0;
	setSize();
	this.backButton.setSize(size);
	this.backButton.getElement().getStyle().setWidth(100, Unit.PCT);
	this.backButton.setShowWhenDisabled(false);
	this.nextButton.setSize(size);
	this.nextButton.getElement().getStyle().setWidth(100, Unit.PCT);
	this.nextButton.setShowWhenDisabled(false);
	this.backButton.setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			previousPage();
		}
	});
	this.nextButton.setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			nextPage();
		}
	});
	

	accessibilityRow.getBackButton().setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			previousPage();
		}
	});
	accessibilityRow.getNextButton().setClickHandler(new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			nextPage();
		}
	});
	}
	
	public void addResult(ResultObject result){
		results.add(result);
	}
	
	public boolean removeResult(ResultObject result) {
		boolean removed = results.remove(result);
		return removed;
	}
	
	public void clearResults() {
		setPage(0);
		results.clear();
		refresh();
	}
	
	public void refresh(){
		clear();
		accessibilityRow.clear();
		int count = 0;
		int footerOffset = 0;
		backButton.setEnabled(false);
		
		getFlexCellFormatter().setColSpan(0, 0, columns);
		setWidget(0, 0, backButton);
		
		for (ResultObject result : results){
			result.setNumber(-1);
			for(Widget cell : result.getCellObjects()){
				if(getRowNumber(count)> maxRows){
					break;
				}
				if(count >= page*columns*maxRows){
					
					try {
						NavButton btn = (NavButton) cell;
						btn.setSize(size);
						btn.setWidth("100%");
					} catch (Exception e) {
					}
					getFlexCellFormatter().setColSpan(getRowNumber(count), getCellInRow(count), 1);
					setWidget(getRowNumber(count), getCellInRow(count), cell);
				}
				count++;
			}
			if(count >= page*columns*maxRows+1){
				if (result.getShowFooter()){
					NavButton fbut = result.getFooterButton();
					fbut.setSize(0.5);
					accessibilityRow.add(fbut);
					result.setNumber(accessibilityRow.getNextNumber(footerOffset));
				}
			}else{
				if(result.getShowFooter()){
					footerOffset++;
				}
			}
			if(getRowNumber(count)> maxRows){
				break;
			}
		}
		
		
		
		while(getCellInRow(count) != 0){
			
			setWidget(getRowNumber(count), getCellInRow(count), new FlowPanel());
			count++;
		}
		if(results.size() <= 0){
			Label noData = new Label(Studiinf.i18n.noData());
			
			noData.getElement().getStyle().setProperty("fontSize", "1.5vh");
			noData.getElement().getStyle().setProperty("lineHeight", "1.9vh");
			noData.getElement().getStyle().setProperty("textAlign", "center");
			
			getFlexCellFormatter().setColSpan(getRowNumber(count), 0, columns);
			setWidget(getRowNumber(count), 0, noData);
			count = count + columns;
		}
		getFlexCellFormatter().setColSpan(getRowNumber(count), 0, columns);
		setWidget(getRowNumber(count), 0, nextButton);
			backButton.setEnabled(hasPreviousPage());
			nextButton.setEnabled(hasNextPage());
			accessibilityRow.getBackButton().setEnabled(hasPreviousPage());
			accessibilityRow.getNextButton().setEnabled(hasNextPage());
	}
	
	private int getRowNumber(int cellCount){
		return (int)((cellCount / columns))+1-(page*maxRows);
	}
	
	private int getCellInRow(int cellCount){
		return (cellCount % columns);
	}
	
	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		setPage(0);
	}
}
