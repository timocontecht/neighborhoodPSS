package org.visico.neighborhoodpss.client;


import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;

public class NodeMarker extends Marker  {

	private EditNetworkPanel panel;
	
	
	public NodeMarker(LatLng point, MarkerOptions op, EditNetworkPanel panel) 
	{	
		super(point, op);
		this.panel = panel;
	}

	
		
	
}
