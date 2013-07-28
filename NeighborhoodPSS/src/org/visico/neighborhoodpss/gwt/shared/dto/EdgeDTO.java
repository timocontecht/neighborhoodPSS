package org.visico.neighborhoodpss.gwt.shared.dto;

import java.io.Serializable;

public abstract class EdgeDTO implements Cloneable, Serializable
{

	private int id;
	private double capacity;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2009155193814407284L;

	public EdgeDTO()
	{
		
	}
	
	public EdgeDTO(EdgeDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the clone should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated		
		this.setCapacity(toCopy.getCapacity());
		
	}
	
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

	
}
