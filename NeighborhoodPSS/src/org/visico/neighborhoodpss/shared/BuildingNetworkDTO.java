package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildingNetworkDTO extends NetworkDTO implements Cloneable, Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -833655125804076550L;
	
	private List<BuildingEdgeDTO> edges = new ArrayList<BuildingEdgeDTO>();
	private List<BuildingDTO> buildings = new ArrayList<BuildingDTO>();
	
	
	
	public void addEdge(BuildingDTO start, BuildingDTO end, double capacity)
	{
		BuildingEdgeDTO e = new BuildingEdgeDTO();
		e.setCapacity(capacity);
		e.setEnd_building(end);
		e.setStart_building(start);
		edges.add(e);
	}
	
	
	
	public List<BuildingEdgeDTO> getEdges() {
		return edges;
	}

	public void setEdges(List<BuildingEdgeDTO> edges) {
		this.edges = edges;
	}

	public void addBuilding(BuildingDTO b)
	{
		buildings.add(b);
	}
	
	public List<BuildingDTO> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<BuildingDTO> buildings) {
		this.buildings = buildings;
	}

	
	public BuildingNetworkDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		BuildingNetworkDTO clone = (BuildingNetworkDTO)super.clone();
		
		
		Iterator<BuildingEdgeDTO> eit = this.getEdges().iterator();
		while (eit.hasNext())
		{
			BuildingEdgeDTO e = eit.next();
			clone.getEdges().add(e.clone());
		}
		
		Iterator<BuildingDTO> bit = this.getBuildings().iterator();
		while (bit.hasNext())
		{
			BuildingDTO b = bit.next();
			clone.getBuildings().add(b.clone());
		}
		
		return clone;
	}

	
	public void addEdge(BuildingEdgeDTO edge) {
		edges.add(edge);
	}

	
}
