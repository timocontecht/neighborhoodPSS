package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GeoNetworkDTO extends NetworkDTO implements Cloneable, Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6028036394408737589L;
	private List<NodeDTO> nodes = new ArrayList<NodeDTO>();
	private List<GeoEdgeDTO> edges = new ArrayList<GeoEdgeDTO>();
	
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
		GeoEdgeDTO e = new GeoEdgeDTO();
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
	
	
	public List<GeoEdgeDTO> getEdges() {
		return edges;
	}

	public void setEdges(List<GeoEdgeDTO> edges) {
		this.edges = edges;
	}

	
	
	
	public GeoNetworkDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		
		GeoNetworkDTO clone = (GeoNetworkDTO)super.clone();
		
		// need to clone the nodes and edges as well
		Iterator<NodeDTO> nit = this.getNodes().iterator();
		while (nit.hasNext())
		{
			NodeDTO n = nit.next();
			clone.getNodes().add(n.clone());
		}
		
		Iterator<GeoEdgeDTO> eit = this.getEdges().iterator();
		while (eit.hasNext())
		{
			GeoEdgeDTO e = eit.next();
			clone.getEdges().add(e.clone());
		}
		
		
		return clone;
	}

	public void addNode(NodeDTO node) {
		nodes.add(node);
		
	}

	public void addEdge(GeoEdgeDTO edge) {
		edges.add(edge);
	}

	
}
