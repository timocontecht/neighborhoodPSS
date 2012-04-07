package org.visico.neighborhoodpss.client;




import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.maps.client.Maps;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.BarChart;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class NeighborhoodPSS implements EntryPoint 
{
	// GWT module entry point method.
	  public void onModuleLoad() 
	  {
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
		    	 @SuppressWarnings("unused")
				 IndicatorPanel panel = new IndicatorPanel(null);
		    	  
			  }
	   };
	   VisualizationUtils.loadVisualizationApi(onLoadCallback, BarChart.PACKAGE);
	   
	  }
	  
	  

	  private void buildUi() 
	  {
	    MainTab panel = MainTab.getInstance();
	    RootLayoutPanel.get().add(panel);
	  }	
	  
	  		
	  
}
