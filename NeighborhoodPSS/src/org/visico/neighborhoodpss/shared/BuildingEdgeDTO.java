package org.visico.neighborhoodpss.shared;

import java.io.Serializable;

public class BuildingEdgeDTO extends EdgeDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1063185573018035186L;

	private BuildingDTO start_building;
	private BuildingDTO end_building;
	
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


	protected Object clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		BuildingEdgeDTO edge = (BuildingEdgeDTO)super.clone();
		
		edge.setCapacity(this.getCapacity());
		edge.setEnd_building((BuildingDTO) this.getEnd_building().clone());
		edge.setStart_building((BuildingDTO) this.getStart_building().clone());
		
		return edge;
	}
}
