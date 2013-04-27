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
	
	public GeoNetworkDTO()
	{
		
	}
	
	public GeoNetworkDTO(GeoNetworkDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated	
		
		// need to copy the nodes and edges as well
		Iterator<NodeDTO> nit = toCopy.getNodes().iterator();
		while (nit.hasNext())
		{
			NodeDTO n = nit.next();
			this.getNodes().add(new NodeDTO(n));
		}
		
		Iterator<GeoEdgeDTO> eit = toCopy.getEdges().iterator();
		while (eit.hasNext())
		{
			GeoEdgeDTO e = eit.next();
			this.getEdges().add(new GeoEdgeDTO(e));
		}
		
	}
	
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

	
	
	
	

	public void addNode(NodeDTO node) {
		nodes.add(node);
		
	}

	public void addEdge(GeoEdgeDTO edge) {
		edges.add(edge);
	}

	
}
