package org.visico.neighborhoodpss.server;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.shared.dto.GeoEdgeDTO;

@MappedSuperclass

public abstract class Edge implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642958911636611366L;

	@Id
	@GeneratedValue
	protected int id;
	
	@Column
	protected double capacity;
	
	@Transient
	protected GeoEdgeDTO dto_object = null;

	public Edge ()
	{
		
	}
	
	public Edge(GeoEdgeDTO dto)
	{
		this.capacity = dto.getCapacity();
		this.dto_object = dto;
		
		this.id = dto.getId();
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
