package org.visico.neighborhoodpss.client;




import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.Selection;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.events.SelectHandler;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NeighborhoodPSS implements EntryPoint 
{
	// GWT module entry point method.
	  public void onModuleLoad() {
	   /*
	    * Asynchronously loads the Maps API.
	    *
	    * The first parameter should be a valid Maps API Key to deploy this
	    * application on a public server, but a blank key will work for an
	    * application served from localhost.
	   */
	   Maps.loadMapsApi("", "2", false, new Runnable() {
	      public void run() {
	        buildUi();
	      }
	    });
	 
	   
	   Runnable onLoadCallback = new Runnable()
	   {
		      public void run() 
		      {
		    	  //initialize the panel
		    	 IndicatorPanel panel = IndicatorPanel.getInstance();
		    	  
			  }
	   };
	   VisualizationUtils.loadVisualizationApi(onLoadCallback, BarChart.PACKAGE);
	   
	  }
	  
	  private Options createOptions() {
		    Options options = Options.create();
		    options.setWidth(400);
		    options.setHeight(240);
		    
		    options.setTitle("My Daily Activities");
		    return options;
		  }

	  private void buildUi() {
	    // Open a map centered on Cawker City, KS USA
	    

	    final DockLayoutPanel dock = new DockLayoutPanel(Unit.PX);
	    
	    dock.addNorth(UserPanel.getInstance(), 120);
	    dock.addWest(ModePanel.getInstance(), 150);
	    dock.addSouth(DataPanel.getInstance(), 200);
	    dock.add(Map.getInstance().getMap());
	    

	    // Add the map to the HTML host page
	    RootLayoutPanel.get().add(dock);
	  }	
	  
	  private AbstractDataTable createTable() {
		    DataTable data = DataTable.create();
		    data.addColumn(ColumnType.STRING, "Task");
		    data.addColumn(ColumnType.NUMBER, "Hours per Day");
		    data.addRows(2);
		    data.setValue(0, 0, "Work");
		    data.setValue(0, 1, 14);
		    data.setValue(1, 0, "Sleep");
		    data.setValue(1, 1, 10);
		    return data;
		  }
		
	  
}
