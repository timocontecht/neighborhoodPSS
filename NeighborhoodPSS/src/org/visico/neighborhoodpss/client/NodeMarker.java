package org.visico.neighborhoodpss.client;

import java.io.IOException;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;

public class NodeMarker extends Marker {

	public NodeMarker(LatLng point, MarkerOptions op) throws IOException {	
		super(point, op);
	}
}
