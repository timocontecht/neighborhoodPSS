package org.visico.neighborhoodpss.client;


import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;

public class NodeMarker extends Marker  {

	MarkerOptions op = null;
	
	
	public NodeMarker(LatLng point, MarkerOptions op) 
	{	
		super(point, op);
		this.op = op;
	}

	public void setSelected()
	{
//		Icon nodeIcon = Icon.newInstance("res/selected_node.png");
//		nodeIcon.setIconSize(Size.newInstance(8, 8));
//		nodeIcon.setIconAnchor(Point.newInstance(4, 4));
		
		this.setImage("res/selected_node.png");
  	  	
  	  	
//		this.getIcon().setIconSize(Size.newInstance(size,  size));
	}

	public void setUnselected() {
		// TODO Auto-generated method stub
		this.setImage("res/node.png");
	}
	
}
