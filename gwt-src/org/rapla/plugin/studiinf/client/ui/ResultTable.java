package org.rapla.plugin.studiinf.client.ui;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ResultTable extends FlexTable {
	
	private List<ResultObject> results = new LinkedList<ResultObject>();
	private int columns;
	private int maxRows;
	private final FlowPanel footerPanel;
	
	public ResultTable(FlowPanel footerPanel,int columns,int maxRows) {
	this.columns = columns;	
	this.maxRows = maxRows;
	this.footerPanel = footerPanel;
	}
	
		
	
	
	public void addResult(ResultObject result){
		results.add(result);
		result.setNumber(results.indexOf(result) + 1);
//		refresh();
	}
	
	public boolean removeResult(ResultObject result) {
		boolean removed = results.remove(result);
//		refresh();
		return removed;
	}
	
	public void clearResults() {
		results.clear();
		refresh();
	}
	
	public void refresh(){
//		Window.alert("refresh");
		clear();
		footerPanel.clear();
		int count = 0;
		for (ResultObject result : results){
			for(Widget cell : result.getCellObjects()){
				if(Math.floor((count / columns))>= maxRows){
					break;
				}

				setWidget((int)(count / columns), (int) count % columns, cell);
				count++;
			}
			footerPanel.add(result.getFooterButton());
			if(Math.floor((count / columns))>= maxRows){
				break;
			}
		}
	}
	
	


	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
		refresh();
	}
}
