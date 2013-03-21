package org.visico.neighborhoodpss.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.visico.neighborhoodpss.shared.BuildingDTO;
import org.visico.neighborhoodpss.shared.EdgeDTO;
import org.visico.neighborhoodpss.shared.NetworkDTO;
import org.visico.neighborhoodpss.shared.NodeDTO;

import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;


public class NetworkPolygon extends Polygon implements PolygonEndLineHandler
{

	public static ArrayList<NetworkPolygon> networks = new ArrayList<NetworkPolygon>();
	private double capacity;

	public NetworkPolygon() 
	{
		super(new LatLng[0], "0000FF", 1, 1.0, "0000FF", 0.1);
		network = new NetworkDTO();
		addPolygonEndLineHandler(this);
	}
	
	
	
	public NetworkPolygon(NetworkDTO next) 
	{
		super(new LatLng[0], "0000FF", 1, 1.0, "0000FF", 0.1);
		network = new NetworkDTO();
		addPolygonEndLineHandler(this);
	}


	@Override
	public void onEnd(PolygonEndLineEvent event) 
	{
		NetworkDialog dlg = new NetworkDialog(this, scenarioPanel);
		dlg.center();
  	  	dlg.show();
	}

	public void setScenarioPanel(ScenarioPanel p)
	{
		scenarioPanel = p;
	}
	
	public NetworkDTO getNetwork()
	{
		return network;
	}
	
	public NetworkDTO setNetwork()
	{
		LatLng start = this.getVertex(0);
		for (int i=1; i<this.getVertexCount(); i++)
		{
			LatLng vertex = this.getVertex(i);
			
			NodeDTO startNode = new NodeDTO();
			startNode.setLatitude(start.getLatitude());
			startNode.setLongitude(start.getLongitude());
			
			NodeDTO endNode = new NodeDTO();
			endNode.setLatitude(vertex.getLatitude());
			endNode.setLongitude(vertex.getLongitude());
			
			network.addNode(startNode);
			network.addNode(endNode);
			network.addEdge(startNode, endNode, 0.0); //TODO: add capacity dialog upfront
		}
		
		return network;
	}
	
	private NetworkDTO network; 
	
	ScenarioPanel scenarioPanel = null;

	public void setScenario(NetworkDTO n) 
	{
		network = n;
		
		for (int i=0; i<n.getNodes().size(); i++)
		{
			this.insertVertex(i, LatLng.newInstance(n.getNodes().get(i).getLatitude(), 
					n.getNodes().get(i).getLongitude()));
		}
		
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	
}
