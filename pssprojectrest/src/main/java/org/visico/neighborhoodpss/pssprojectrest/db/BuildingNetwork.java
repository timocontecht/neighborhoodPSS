package org.visico.neighborhoodpss.pssprojectrest.db;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.BuildingEdgeDTO;
import org.visico.neighborhoodpss.domain.project.BuildingNetworkDTO;
import org.visico.neighborhoodpss.domain.project.GeoEdgeDTO;
import org.visico.neighborhoodpss.domain.project.GeoNetworkDTO;
import org.visico.neighborhoodpss.domain.project.NodeDTO;

@Entity
@Table(name="BUILDINGNETWORK")
public class BuildingNetwork extends Network implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5457475366073332784L;

	
	
	
	@Transient
	private BuildingNetworkDTO dto_object = null;
	
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="network_id")
	private List<BuildingEdge> edges = new ArrayList<BuildingEdge>();
	
	
	public List<BuildingEdge> getEdges() {
		return edges;
	}

	public void setEdges(List<BuildingEdge> edges) {
		this.edges = edges;
	}

	public BuildingNetwork()
	{
	}
	
	public BuildingNetwork(BuildingNetworkDTO dto)
	{
		this.dto_object = dto;
		this.setId(dto.getId());
		this.setName(dto.getName());
		this.setColor(dto.getColor());
		
		ArrayList<BuildingEdge> eds = new ArrayList<BuildingEdge>();
		Iterator<BuildingEdgeDTO> eit = dto.getEdges().iterator();
		while(eit.hasNext())
		{
			eds.add(new BuildingEdge(eit.next()));
		}
		this.setEdges(eds);
		
	
		
	}
	
	public void update_dtoIds() {
		this.dto_object.setId(this.id);
		this.dto_object.setColor(this.getColor());
		
		Iterator<BuildingEdge> eit = this.edges.iterator();
		while(eit.hasNext())
		{
			BuildingEdge e = eit.next();
			e.update_dtoIds();
		}
		
		
	}

	public BuildingNetworkDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new BuildingNetworkDTO();
			dto_object.setId(this.getId());
			dto_object.setName(this.getName());
			dto_object.setColor(this.getColor());
			
			for (BuildingEdge e : edges)
				dto_object.addEdge(e.getDto_object());
			
			
		}
		return dto_object;
	}

	public void setDto_object(BuildingNetworkDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	
}
