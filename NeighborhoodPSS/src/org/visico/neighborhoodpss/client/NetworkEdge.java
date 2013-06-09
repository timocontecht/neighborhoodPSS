package org.visico.neighborhoodpss.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polyline;

public class NetworkEdge extends Polyline
{
	NodeMarker start = null;
	NodeMarker end = null;
	
	public NetworkEdge(NodeMarker start, NodeMarker end, LatLng[] points, String color) {
		super(points, color, 3, 1.0);
		this.start = start;
		this.end = end;
	}

	public NodeMarker getStart() {
		return start;
	}

	public void setStart(NodeMarker start) {
		this.start = start;
	}

	public NodeMarker getEnd() {
		return end;
	}

	public void setEnd(NodeMarker end) {
		this.end = end;
	}
	
	

}
