package org.visico.neighborhoodpss.client;


import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;


public class Map implements MapClickHandler
{

	public Map()
	{
		LatLng gaxel = LatLng.newInstance(52.01997, 6.79255);

	    theMap = new MapWidget(gaxel, 2);
	    theMap.setSize("100%", "100%");
	    theMap.setZoomLevel(14);
	   
	    // Add some controls for the zoom level
	    theMap.addControl(new LargeMapControl());
	    theMap.addMapClickHandler(this);
	}
	
	public MapWidget getMap()
	{
		return theMap;
	}
		
	private MapWidget theMap;

	@Override
	public void onClick(MapClickEvent event) 
	{
		
	}
	
	// variable used to mark whether a polygonal object in the drawings is closed 
	boolean isClosed = false;
}
