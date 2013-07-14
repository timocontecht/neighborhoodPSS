package org.visico.neighborhoodpss.shared.dto.project;

import java.io.Serializable;

public class BuildingEdgeDTO extends EdgeDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1063185573018035186L;

	private BuildingDTO start_building;
	private BuildingDTO end_building;
	
	public BuildingEdgeDTO()
	{
		
	}
	
	public BuildingEdgeDTO(BuildingEdgeDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated	
		super(toCopy);
		this.setCapacity(toCopy.getCapacity());
		this.setEnd_building(new BuildingDTO(toCopy.getEnd_building()));
		this.setStart_building(new BuildingDTO(toCopy.getStart_building()));
		
	}
	
	public BuildingDTO getStart_building() {
		return start_building;
	}

	public void setStart_building(BuildingDTO start_building) {
		this.start_building = start_building;
	}

	public BuildingDTO getEnd_building() {
		return end_building;
	}

	public void setEnd_building(BuildingDTO end_building) {
		this.end_building = end_building;
	}


	
}
