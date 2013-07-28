package org.visico.neighborhoodpss.gwt.shared.dto;

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
	
	public BuildingNetworkDTO()
	{
		
	}
	
	public BuildingNetworkDTO (BuildingNetworkDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		
		
		Iterator<BuildingEdgeDTO> eit = toCopy.getEdges().iterator();
		while (eit.hasNext())
		{
			BuildingEdgeDTO e = eit.next();
			this.getEdges().add(new BuildingEdgeDTO(e));
		}
		
		Iterator<BuildingDTO> bit = toCopy.getBuildings().iterator();
		while (bit.hasNext())
		{
			BuildingDTO b = bit.next();
			this.getBuildings().add(new BuildingDTO(b));
		}
	}

	
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

	
	
	
	public void addEdge(BuildingEdgeDTO edge) {
		edges.add(edge);
	}

	
	
}
