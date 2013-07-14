package org.visico.neighborhoodpss.server.project;


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

import org.visico.neighborhoodpss.shared.dto.project.BuildingDTO;
import org.visico.neighborhoodpss.shared.dto.project.GeoEdgeDTO;
import org.visico.neighborhoodpss.shared.dto.project.GeoNetworkDTO;
import org.visico.neighborhoodpss.shared.dto.project.NodeDTO;

@Entity
@Table(name="GEONETWORK")
public class GeoNetwork extends Network implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5457475366073332784L;

	
	
	
	@Transient
	private GeoNetworkDTO dto_object = null;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="network_id")
	private List<Node> nodes = new ArrayList<Node>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="network_id")
	private List<GeoEdge> edges = new ArrayList<GeoEdge>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "NETWORK_BUILDING", 
		joinColumns = @JoinColumn(name="network_id"),
		inverseJoinColumns = @JoinColumn(name = "building_id")
	)
	private List<Building> buildings = new ArrayList<Building>();
	
	

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	
	public List<GeoEdge> getEdges() {
		return edges;
	}

	public void setEdges(List<GeoEdge> edges) {
		this.edges = edges;
	}

	public List<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(List<Building> buildings) {
		this.buildings = buildings;
	}

	public GeoNetwork()
	{
	}
	
	public GeoNetwork(GeoNetworkDTO dto)
	{
		this.dto_object = dto;
		this.setId(dto.getId());
		this.setName(dto.getName());
		this.setColor(dto.getColor());
		
		ArrayList<Node> nds = new ArrayList<Node>();
		Iterator<NodeDTO> nit = dto.getNodes().iterator();
		while(nit.hasNext())
		{
			nds.add(new Node(nit.next()));
		}
		this.setNodes(nds);
		
		ArrayList<GeoEdge> eds = new ArrayList<GeoEdge>();
		Iterator<GeoEdgeDTO> eit = dto.getEdges().iterator();
		while(eit.hasNext())
		{
			eds.add(new GeoEdge(eit.next()));
		}
		this.setEdges(eds);
		
	
		
	}
	
	public void update_dtoIds() {
		this.dto_object.setId(this.id);
		this.dto_object.setColor(this.getColor());
		Iterator<Node> nit = this.nodes.iterator();
		while(nit.hasNext())
		{
			Node n = nit.next();
			n.update_dtoIds();
		}
		
		Iterator<GeoEdge> eit = this.edges.iterator();
		while(eit.hasNext())
		{
			GeoEdge e = eit.next();
			e.update_dtoIds();
		}
		
		Iterator<Building> bit = this.buildings.iterator();
		while(bit.hasNext())
		{
			Building b = bit.next();
			b.update_dtoIds();
		}
	}

	public GeoNetworkDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new GeoNetworkDTO();
			dto_object.setId(this.getId());
			dto_object.setName(this.getName());
			dto_object.setColor(this.getColor());
			
			for (Node n : nodes)
				dto_object.addNode(n.getDto_object());
			
			for (GeoEdge e : edges)
				dto_object.addEdge(e.getDto_object());
			
			
		}
		return dto_object;
	}

	public void setDto_object(GeoNetworkDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	
}
