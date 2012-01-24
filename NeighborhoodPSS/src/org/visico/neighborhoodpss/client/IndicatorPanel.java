package org.visico.neighborhoodpss.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;



public class IndicatorPanel extends VerticalPanel
{
	private static IndicatorPanel instance = null;
	
	private IndicatorPanel()
	{
		draw();
	}
	
	public static IndicatorPanel getInstance()
	{
		if (instance == null)
			instance = new IndicatorPanel();
		return instance;
	}
	
	public void draw()
	{
		 
	     
	     DataTable data = DataTable.create();
	     
	    
	     data.addColumn(ColumnType.STRING, "Indicator");
	     data.addColumn(ColumnType.NUMBER, "");
	     
	     data.addRows(4);
	     data.setValue(0, 0, "Knowledge Exchange");
	     data.setValue(0, 1, 50);
	     
	     data.setValue(1, 0, "Market D");
	     data.setValue(1, 1, 30);
	     
	     data.setValue(2, 0, "Market NL");
	     data.setValue(2, 1, 80);
	     
	     data.setValue(3, 0, "Market World");
	     data.setValue(3, 1, 100);
	     
	     
	     Options options = Options.create();
	     options.setTitle("Indicator Values");
	     options.setHeight(100);
	     options.setWidth(500);
	     options.setLegend(LegendPosition.NONE);
	     options.setFontSize(12);
		 BarChart chart = new BarChart(data, options);
		 add(chart);    
	}
	
	
	
}
