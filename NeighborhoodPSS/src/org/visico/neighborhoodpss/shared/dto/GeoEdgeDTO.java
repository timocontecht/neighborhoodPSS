package org.visico.neighborhoodpss.shared.dto;

import java.io.Serializable;

public class GeoEdgeDTO extends EdgeDTO implements Cloneable, Serializable
{

	private NodeDTO start_node;
	private NodeDTO end_node;
	
	public GeoEdgeDTO()
	{
		
	}
	
	public GeoEdgeDTO(GeoEdgeDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		
		this.setEnd_node(new NodeDTO(toCopy.getEnd_node()));
		this.setStart_node(new NodeDTO(toCopy.getStart_node()));
		
	}
	
	
	public NodeDTO getStart_node() {
		return start_node;
	}

	public void setStart_node(NodeDTO start_node) {
		this.start_node = start_node;
	}

	public NodeDTO getEnd_node() {
		return end_node;
	}

	public void setEnd_node(NodeDTO end_node) {
		this.end_node = end_node;
	}

	
	
}
