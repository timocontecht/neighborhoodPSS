package org.visico.neighborhoodpss.shared.patterns;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.visico.neighborhoodpss.client.BuildingPolygon;
import org.visico.neighborhoodpss.client.EditNetworkPanel;
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



public class ScenarioEditMediator {
	ScenarioDTO scenario;
	
	Map map;
	EditNetworkPanel editNetworkPanel;
	
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
				
				NetworkEdge edge = new NetworkEdge(mStart, mEnd, points);
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

	public void registerEditNetworkPanel(EditNetworkPanel edtNetwPnl) {
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
	}

}
