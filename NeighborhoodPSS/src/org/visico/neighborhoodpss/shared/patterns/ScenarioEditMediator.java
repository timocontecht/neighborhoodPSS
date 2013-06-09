package org.visico.neighborhoodpss.shared.patterns;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.visico.neighborhoodpss.client.BuildingPolygon;
import org.visico.neighborhoodpss.client.EditMapPanel;
import org.visico.neighborhoodpss.client.Map;
import org.visico.neighborhoodpss.client.NetworkEdge;
import org.visico.neighborhoodpss.client.NetworkTable;
import org.visico.neighborhoodpss.client.NodeMarker;
import org.visico.neighborhoodpss.server.project.GeoPoint;
import org.visico.neighborhoodpss.shared.dto.BuildingDTO;
import org.visico.neighborhoodpss.shared.dto.EdgeDTO;
import org.visico.neighborhoodpss.shared.dto.GeoEdgeDTO;
import org.visico.neighborhoodpss.shared.dto.GeoNetworkDTO;
import org.visico.neighborhoodpss.shared.dto.GeoPointDTO;
import org.visico.neighborhoodpss.shared.dto.NetworkDTO;
import org.visico.neighborhoodpss.shared.dto.NodeDTO;
import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;



public class ScenarioEditMediator {
	ScenarioDTO scenario;
	
	Map map;
	EditMapPanel editNetworkPanel;
	
	// variables
	NetworkDTO selectedNetwork = null;
	Set<NetworkDTO> visibleNetworks = new HashSet<NetworkDTO>();
	
	double inflow;
	double outflow;
	double capacity;
	
	private HashMap<BuildingPolygon, BuildingDTO> buildingMap = new HashMap<BuildingPolygon, BuildingDTO>();
	private HashMap<NetworkEdge, EdgeDTO> edgeMap = new HashMap<NetworkEdge, EdgeDTO>();
	private HashMap<NodeMarker, NodeDTO> nodeMap = new HashMap<NodeMarker, NodeDTO>();
	
	private Set<Overlay> selected = new HashSet<Overlay>();

	private NetworkTable networkTable;
	
	
	public ScenarioEditMediator(ScenarioDTO scenario)
	{
		this.scenario = scenario;
		
	}
	
	public void initializeOverlays() {
		for (BuildingDTO b : scenario.getBuildingDTOs())
		{
			BuildingPolygon bldgPlg = new BuildingPolygon();
	    	map.getMap().addOverlay(bldgPlg);
	    	
	    	for (int i=0; i<b.getPoints().size(); i++)
			{
				bldgPlg.insertVertex(i, LatLng.newInstance(b.getPoints().get(i).getLatitude(), b.getPoints().get(i).getLongitude()));
			}
	    	buildingMap.put(bldgPlg, b);
		}
		
		ArrayList<GeoNetworkDTO> networks = new ArrayList<GeoNetworkDTO>(scenario.getGeoNetworkDTOs()); 
		networkTable.fillTable(networks);
		
		for (GeoNetworkDTO n : scenario.getGeoNetworkDTOs())
		{
			for (GeoEdgeDTO e : n.getEdges())
			{
				LatLng points[] = new LatLng[2];
				points[0] = LatLng.newInstance(e.getStart_node().getLatitude(), e.getStart_node().getLongitude());
				NodeMarker mStart = new NodeMarker(points[0], Map.getOptions());
				map.getMap().addOverlay(mStart);
				nodeMap.put(mStart, e.getStart_node());
				
				points[1] = LatLng.newInstance(e.getEnd_node().getLatitude(), e.getEnd_node().getLongitude());
				NodeMarker mEnd = new NodeMarker(points[1], Map.getOptions());
				map.getMap().addOverlay(mEnd);
				nodeMap.put(mEnd, e.getEnd_node());
				
				NetworkEdge edge = new NetworkEdge(mStart, mEnd, points, n.getColor());
				map.getMap().addOverlay(edge);
				edgeMap.put(edge, e);
			}
		}
	}

	public NetworkDTO getSelectedNetwork() {
		return selectedNetwork;
	}

	public void setSelectedNetwork(NetworkDTO selectedNetwork) {
		if (selectedNetwork == null)
		{
			map.setMode(Map.editmodes.NO_NETWORK);
			editNetworkPanel.cleanEditModes();
		}
		this.selectedNetwork = selectedNetwork;
	}

	public Set<NetworkDTO> getVisibleNetworks() {
		return visibleNetworks;
	}

	public void setVisibleNetworks(Set<NetworkDTO> visibleNetworks) {
		this.visibleNetworks = visibleNetworks;
	}

	public void selectionMode() {
		if (selectedNetwork != null)
			map.setMode(Map.editmodes.SELECTION);
		
			
	}
	
	public void addNodeMode() {
		if (selectedNetwork != null)
			map.setMode(Map.editmodes.ADD_NODE);
		
	}
	
	public void addEdgeMode() {
		if (selectedNetwork != null)
			map.setMode(Map.editmodes.ADD_EDGE);
	}

	public void registerMap(Map map) {
		this.map = map;
	}

	public void registerEditNetworkPanel(EditMapPanel edtNetwPnl) {
		this.editNetworkPanel = edtNetwPnl;
	}
	
	public void registerNetworkTable(NetworkTable table) {
		this.networkTable = table;
	}

	public void noMode() {
		map.setMode(Map.editmodes.NO_NETWORK);
	}

	public double getInflow() {
		return inflow;
	}

	public void setInflow(double inflow) {
		this.inflow = inflow;
	}

	public double getOutflow() {
		return outflow;
	}

	public void setOutflow(double outflow) {
		this.outflow = outflow;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public void addBuildingMode() {
		map.setMode(Map.editmodes.ADD_BUILDING);
	}

	public void changeSelected() {
		for (Overlay o : selected)
		{
			if (o instanceof NodeMarker)
			{
				NodeDTO nodeDTO = nodeMap.get((NodeMarker)o);
				nodeDTO.setInflow(getInflow());
		     	nodeDTO.setOutflow(getOutflow());
			}
			else if (o instanceof NetworkEdge)
			{
				EdgeDTO edgeDTO = edgeMap.get((NetworkEdge)o);
				edgeDTO.setCapacity(capacity);
			}
			
		}
		
	}

	public void deleteSelected() {
		for (Overlay o : selected)
		{
			if (o instanceof NetworkEdge)
			{
				EdgeDTO edgeDTO = edgeMap.get((NetworkEdge)o);
				((GeoNetworkDTO)selectedNetwork).deleteEdge(edgeDTO);
				edgeMap.remove(o);
			}
			else if (o instanceof BuildingPolygon)
			{
				BuildingDTO buildingDTO = buildingMap.get(((BuildingPolygon)o));
				scenario.deleteBuilding(buildingDTO);
				buildingMap.remove(o);
			}
			map.getMap().removeOverlay(o);
		}
		selected.clear();
		editNetworkPanel.changeSelectedLabel(0,0,0);
	}

	public void addNewNode(NodeMarker node) {
		  NodeDTO nodeDTO = new NodeDTO();
     	  nodeDTO.setLatitude(node.getLatLng().getLatitude());
     	  nodeDTO.setLongitude(node.getLatLng().getLongitude());
     	  nodeDTO.setInflow(getInflow());
     	  nodeDTO.setOutflow(getOutflow());
     	  ((GeoNetworkDTO)selectedNetwork).addNode(nodeDTO);
     	  nodeMap.put(node, nodeDTO);
	}

	public void addNewEdge(NetworkEdge edge) {
		if (selectedNetwork instanceof GeoNetworkDTO)
		{
			GeoEdgeDTO edgeDTO = new GeoEdgeDTO();
			edgeDTO.setCapacity(capacity);
			edgeDTO.setStart_node(nodeMap.get(edge.getStart()));
			edgeDTO.setEnd_node(nodeMap.get(edge.getEnd()));
			((GeoNetworkDTO)selectedNetwork).addEdge(edgeDTO);
			edgeMap.put(edge, edgeDTO);
		}	
	}

	public void addNewBuilding(BuildingPolygon building) {
		BuildingDTO buildingDTO = new BuildingDTO();
		
		for (int i=0; i < building.getVertexCount(); i++)
		{
			buildingDTO.addVertex( building.getVertex(i).getLatitude(), building.getVertex(i).getLongitude());
		}
		
		buildingDTO.setArea(building.getArea());
		scenario.addBuilingDTO(buildingDTO);
		buildingMap.put(building, buildingDTO);
	}

	public void addMapSelection(Overlay overlay) {
		selected.add(overlay);
		if (overlay instanceof NetworkEdge)
		{
			((NetworkEdge) overlay).setOpacity(0.5);
		}
		else if (overlay instanceof NodeMarker)
		{
			((NodeMarker) overlay).setSelected();
		}
		changeSelectionString();
	}
	
	public void removeSelection(Overlay overlay){
		selected.remove(overlay);
		if (overlay instanceof NetworkEdge)
		{
			((NetworkEdge) overlay).setOpacity(1.0);
		}
		else if (overlay instanceof NodeMarker)
		{
			((NodeMarker) overlay).setSelected();
		}
		changeSelectionString();
	}
	
	public void clearAllSelected()
	{
		selected.clear();
		changeSelectionString();
	}
	
	
	private void changeSelectionString()
	{
		int selectedNodes = 0, selectedEdges = 0, selectedBuildings = 0;
		
		for (Overlay o : selected)
		{
			if (o instanceof NodeMarker)
				selectedNodes ++;
			else if (o instanceof NetworkEdge)
				selectedEdges ++;
			else if (o instanceof BuildingPolygon)
				selectedBuildings++;
		}
			
		editNetworkPanel.changeSelectedLabel(selectedNodes, selectedEdges, selectedBuildings);
	}

	public void addGeoNetwork(String text, String hexColor) {
		GeoNetworkDTO newNetwork = new GeoNetworkDTO();
		newNetwork.setName(text);
		newNetwork.setColor(hexColor);
		scenario.addGeoNetworkDTO(newNetwork);
		networkTable.fillTable(new ArrayList<GeoNetworkDTO>(scenario.getGeoNetworkDTOs()));
	}

	public void deleteGeoNetwork(GeoNetworkDTO networkToDelete) {
		scenario.deleteGeoNetwork(networkToDelete);
		networkTable.fillTable(new ArrayList<GeoNetworkDTO>(scenario.getGeoNetworkDTOs()));
		
		HashSet<NetworkEdge> edgesToDelete = new HashSet<NetworkEdge>();
		HashSet<NodeMarker> nodesToDelete = new HashSet<NodeMarker>();
		
		for (EdgeDTO edge : networkToDelete.getEdges())
		{
			Iterator<Entry<NetworkEdge, EdgeDTO>> it = edgeMap.entrySet().iterator();
			while (it.hasNext())
			{
				Entry<NetworkEdge, EdgeDTO> entry =  it.next();
				if (entry.getValue() == edge)
				{
					NetworkEdge nwEdge = entry.getKey();
					map.getMap().removeOverlay(nwEdge);
					edgesToDelete.add(nwEdge);
					
					nodesToDelete.add(nwEdge.getStart());
					nodesToDelete.add(nwEdge.getEnd());
				}
			}
		}
		
		for (NetworkEdge e : edgesToDelete)
		{
			edgeMap.remove(e);
			selected.remove(e);
		}
		
		for (NodeMarker n : nodesToDelete)
		{
			map.getMap().removeOverlay(n);
			nodeMap.remove(n);
			selected.remove(n);
		}
		
		changeSelectionString();
		
	}

	public void deleteHangingNodes() {
		if (selectedNetwork instanceof GeoNetworkDTO)
		{
			GeoNetworkDTO nw = (GeoNetworkDTO)selectedNetwork;
			
			HashSet<NodeDTO> connected = new HashSet<NodeDTO>();  
			for (GeoEdgeDTO edge : nw.getEdges())
			{
				connected.add(edge.getStart_node());
				connected.add(edge.getEnd_node());
			}
			
			ArrayList<NodeMarker> markersToDelete = new ArrayList<NodeMarker>();
			for (NodeDTO node : nw.getNodes())
			{
				if (connected.contains(node) == false)
				{
					
					Iterator<Entry<NodeMarker, NodeDTO>> it = nodeMap.entrySet().iterator();
					while (it.hasNext())
					{
						Entry<NodeMarker, NodeDTO> entry =  it.next();
						if (entry.getValue() == node)
						{
							markersToDelete.add(entry.getKey());
						}
					}
				}
			}
			
			for (NodeMarker nm : markersToDelete)
			{
				nw.deleteNode(nodeMap.get(nm));
				nodeMap.remove(nm);
				map.getMap().removeOverlay(nm);
			}
		}
		
	}

	public String getActiveNetworkColor() {
		return selectedNetwork.getColor();
	}

}
