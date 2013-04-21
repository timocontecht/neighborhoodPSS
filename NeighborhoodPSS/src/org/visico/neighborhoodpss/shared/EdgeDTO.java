package org.visico.neighborhoodpss.shared;

import java.io.Serializable;

public class EdgeDTO implements Cloneable, Serializable
{

	private int id;
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

	

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	protected Object clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		EdgeDTO edge = new EdgeDTO();
		
		edge.setCapacity(this.getCapacity());
		
		return edge;
	}
}
