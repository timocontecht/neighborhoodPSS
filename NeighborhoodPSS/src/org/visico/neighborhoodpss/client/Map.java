package org.visico.neighborhoodpss.client;

import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;

public class Map implements MapClickHandler
{
/*	static private Map instance = null;
	
	static public Map getInstance()
	{
		if (instance == null)
			instance = new Map();
		return instance;
	}
	*/
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
