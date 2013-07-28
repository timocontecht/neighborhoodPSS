package org.visico.neighborhoodpss.gwt.client;



import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import org.visico.neighborhoodpss.gwt.shared.patterns.ObserverInterface;
import org.visico.neighborhoodpss.gwt.shared.patterns.ScenarioEditMediator;
import org.visico.neighborhoodpss.gwt.shared.patterns.Subject;


import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.control.LargeMapControl;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.PolygonClickHandler;
import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.ui.Composite;


public class Map extends Composite implements MapClickHandler, PolygonEndLineHandler
{
	public enum editmodes
	{
		NO_NETWORK,
		SELECTION,
		ADD_BUILDING,
		ADD_NODE,
		ADD_EDGE
	};
	
	BuildingPolygon building;
	private editmodes mode;
	private MapWidget theMap;
	ScenarioEditMediator med;
	private static MarkerOptions options = MarkerOptions.newInstance();
	private static NodeMarker firstPickedNode = null; 
	private static Icon nodeIcon = null;
	
	public Map(ScenarioEditMediator med)
	{
		nodeIcon = Icon.newInstance("res/node.png");
		nodeIcon.setIconSize(Size.newInstance(8, 8));
		nodeIcon.setIconAnchor(Point.newInstance(4, 4));
  	  	options.setIcon(nodeIcon);
    	  
		this.med = med;
		med.registerMap(this);
		
		LatLng gaxel = LatLng.newInstance(52.01997, 6.79255);

	    theMap = new MapWidget(gaxel, 2);
	    theMap.setSize("100%", "100%");
	    theMap.setZoomLevel(14);
	   
	    // Add some controls for the zoom level
	    theMap.addControl(new LargeMapControl());
	    theMap.addMapClickHandler(this);
	    
	    mode = editmodes.SELECTION;
	    this.initWidget(theMap);
	}
	
	public MapWidget getMap()
	{
		return theMap;
	}
	
	public editmodes getMode() {
		return mode;
	}

	public void setMode(editmodes mode) {
		this.mode = mode;
	}

	@Override
	public void onClick(MapClickEvent event) 
	{
		MapWidget sender = event.getSender();
		Overlay overlay = event.getOverlay();
        LatLng point = event.getLatLng();
        
        if (mode == editmodes.SELECTION || mode == editmodes.NO_NETWORK)
        {
        	med.addMapSelection(overlay);
        }
        else if (overlay instanceof Marker == false && mode == editmodes.ADD_NODE)
        {
        	
	      	  NodeMarker node = new NodeMarker(point, options);
	      	  sender.addOverlay(node);
	      	  med.addNewNode(node); 
        }
        else if (overlay instanceof NodeMarker && mode == editmodes.ADD_EDGE)
        {
        	if (firstPickedNode == null)
				firstPickedNode = (NodeMarker)overlay;
			else
			{
				LatLng points[] = new LatLng[2];
				points[0] = firstPickedNode.getLatLng(); points[1] = ((NodeMarker)overlay).getLatLng();
				NetworkEdge edge = new NetworkEdge(firstPickedNode, (NodeMarker)overlay, points, med.getActiveNetworkColor());
				sender.addOverlay(edge);
				firstPickedNode = null;
				med.addNewEdge(edge);
			}
        }
        else if (overlay instanceof BuildingPolygon == false 
        		&& mode == editmodes.ADD_BUILDING && (building == null || building.isClosed()))
        {
        	building = new BuildingPolygon();
        	building.addPolygonEndLineHandler(this);
			sender.addOverlay(building);
			building.setDrawingEnabled();
        }
        else if (overlay instanceof BuildingPolygon == true)
        {
        	
        }
	}

	
	public static MarkerOptions getOptions() {
		return options;
	}

	public static void setOptions(MarkerOptions options) {
		Map.options = options;
	}


	// variable used to mark whether a polygonal object in the drawings is closed 
	boolean isClosed = false;



	
	@Override
	public void onEnd(PolygonEndLineEvent event) {
		BuildingPolygon building = (BuildingPolygon)event.getSource();
		building.setClosed(true);
		med.addNewBuilding(building);
	}
	
}
