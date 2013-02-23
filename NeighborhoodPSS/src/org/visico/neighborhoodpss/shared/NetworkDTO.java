package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NetworkDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4908867498424445477L;
	private String name;
	private int id;
	private ScenarioDTO scenario;
	private List<NodeDTO> nodes = new ArrayList<NodeDTO>();
	private List<EdgeDTO> edges = new ArrayList<EdgeDTO>();
	
	public void addNode(double lat, double lon, double inflow, double outflow)
	{
		NodeDTO nd = new NodeDTO();
		nd.setLongitude(lon);
		nd.setLatitude(lat);
		nd.setInflow(inflow);
		nd.setOutflow(outflow);
		
		nodes.add(nd);
	}
	
	public void addEdge(NodeDTO start, NodeDTO end, double capacity)
	{
		EdgeDTO e = new EdgeDTO();
		e.setCapacity(capacity);
		e.setEnd_node(end);
		e.setStart_node(start);
		edges.add(e);
	}
	
	public List<NodeDTO> getNodes() {
		return nodes;
	}
	public void setNodes(List<NodeDTO> nodes) {
		this.nodes = nodes;
	}
	
	
	public List<EdgeDTO> getEdges() {
		return edges;
	}

	public void setEdges(List<EdgeDTO> edges) {
		this.edges = edges;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ScenarioDTO getScenario() {
		return scenario;
	}
	public void setScenario(ScenarioDTO scenario) {
		this.scenario = scenario;
	} 
	
	public NetworkDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		NetworkDTO clone = new NetworkDTO();
		
		clone.setName(this.getName());
		clone.setScenario(this.getScenario());
		
		// need to clone the nodes and edges as well
		Iterator<NodeDTO> nit = this.getNodes().iterator();
		while (nit.hasNext())
		{
			NodeDTO n = nit.next();
			clone.getNodes().add(n.clone());
		}
		
		Iterator<EdgeDTO> eit = this.getEdges().iterator();
		while (eit.hasNext())
		{
			EdgeDTO e = eit.next();
			clone.getEdges().add(e.clone());
		}
		
		return clone;
	}

	public void addNode(NodeDTO node) {
		nodes.add(node);
		
	}

	public void addEdge(EdgeDTO edge) {
		edges.add(edge);
	}
}
