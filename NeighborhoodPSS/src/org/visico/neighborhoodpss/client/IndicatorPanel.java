package org.visico.neighborhoodpss.client;

import org.visico.neighborhoodpss.shared.Indicators;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.LegendPosition;
import com.google.gwt.visualization.client.formatters.BarFormat;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;



public class IndicatorPanel extends VerticalPanel
{
//	private static IndicatorPanel instance = null;
	
	public IndicatorPanel(ScenarioPanel p)
	{
		scenarioPanel = p; 
		data = DataTable.create();
	    
	     data.addColumn(ColumnType.STRING, "Indicator");
	     data.addColumn(ColumnType.NUMBER, "Percentage");
	     
	     data.addRows(4);
	     data.setValue(0, 0, "Knowledge");
	     data.setValue(1, 0, "Market D");
	     data.setValue(2, 0, "Market NL");
	     data.setValue(3, 0, "Market World");
	    
	     
	     
	     options = Options.create();
		
	     options.setHeight(150);
	     options.setWidth(700);
	     options.setLegend(LegendPosition.NONE);
	   
	     AxisOptions haxisoptions = AxisOptions.create();
	     haxisoptions.setMaxValue(100);
	   
	     options.setHAxisOptions(haxisoptions);
	     chart = new BarChart(data, options);
	     add(chart);   
	}
/*	
	public static IndicatorPanel getInstance()
	{
		if (instance == null)
			instance = new IndicatorPanel();
		return instance;
	}
	*/
	public void draw()
	{
		 data.setValue(0, 1, Indicators.knowledgeTransfer(scenarioPanel.getBuildings()));
	     
	     data.setValue(1, 1, Indicators.MarketD(scenarioPanel.getBuildings()));
	     
	     data.setValue(2, 1, Indicators.MarketNL(scenarioPanel.getBuildings()));
	     
	     data.setValue(3, 1, Indicators.MarketWorld(scenarioPanel.getBuildings()));
	     
	     BarFormat.Options bfo = BarFormat.Options.create();
	     bfo.setWidth(300);
	     bfo.setShowValue(true);
	     BarFormat bf = BarFormat.create(bfo); 
	     bf.format(data, 0);
	     
	     chart.draw(data, options);
	}
	
	private DataTable data;
	private BarChart chart;
	private Options options;
	private ScenarioPanel scenarioPanel;
	
}
