package org.visico.neighborhoodpss.shared;

import java.io.Serializable;

public class EdgeDTO implements Cloneable, Serializable
{

	private int id;
	private NodeDTO start_node;
	private NodeDTO end_node;
	private double capacity;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2009155193814407284L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	protected EdgeDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		EdgeDTO edge = new EdgeDTO();
		
		edge.setCapacity(this.getCapacity());
		edge.setEnd_node(this.getEnd_node().clone());
		edge.setStart_node(this.getStart_node().clone());
		
		return edge;
	}
}
