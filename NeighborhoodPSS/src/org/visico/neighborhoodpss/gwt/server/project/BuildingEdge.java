package org.visico.neighborhoodpss.gwt.server.project;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.gwt.shared.dto.BuildingEdgeDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.EdgeDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.GeoEdgeDTO;

@Entity
@Table(name="BUILDINGEDGE")

public class BuildingEdge extends Edge implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642958911636611366L;

	
	@ManyToOne(cascade = CascadeType.ALL)
	private Building start_building;
	@ManyToOne(cascade = CascadeType.ALL)
	private Building end_building;
	
	
	@Transient
	private BuildingEdgeDTO dto_object = null;

	public BuildingEdge ()
	{
		
	}
	
	public BuildingEdge(BuildingEdgeDTO dto)
	{
		this.capacity = dto.getCapacity();
		this.dto_object = dto;
		this.end_building = new Building(dto.getEnd_building());
		this.start_building = new Building(dto.getStart_building());
		this.id = dto.getId();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public Building getStart_building() {
		return start_building;
	}

	public void setStart_building(Building start_building) {
		this.start_building = start_building;
	}

	public Building getEnd_building() {
		return end_building;
	}

	public void setEnd_building(Building end_building) {
		this.end_building = end_building;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public BuildingEdgeDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new BuildingEdgeDTO();
			dto_object.setId(this.getId());
			dto_object.setStart_building(this.getStart_building().getDto_object());
			dto_object.setEnd_building(this.getEnd_building().getDto_object());
			dto_object.setCapacity(this.getCapacity());
		}
		return dto_object;
	}

	public void setDto_object(BuildingEdgeDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	public void update_dtoIds()
	{
		this.dto_object.setId(this.id);
	}
	
}
