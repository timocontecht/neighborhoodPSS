package org.visico.neighborhoodpss.server;


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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.shared.EdgeDTO;
import org.visico.neighborhoodpss.shared.NetworkDTO;
import org.visico.neighborhoodpss.shared.NodeDTO;

@Entity
@Table(name="NETWORK")
public class Network implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5457475366073332784L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String name;
	
	
	@Transient
	private NetworkDTO dto_object = null;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="network_id")
	private List<Node> nodes = new ArrayList<Node>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="network_id")
	private List<Edge> edges = new ArrayList<Edge>();
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	
	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}

	public Network()
	{
	}
	
	public Network(NetworkDTO dto)
	{
		this.dto_object = dto;
		this.setId(dto.getId());
		this.setName(dto.getName());
		
		ArrayList<Node> nds = new ArrayList<Node>();
		Iterator<NodeDTO> nit = dto.getNodes().iterator();
		while(nit.hasNext())
		{
			nds.add(new Node(nit.next()));
		}
		this.setNodes(nds);
		
		ArrayList<Edge> eds = new ArrayList<Edge>();
		Iterator<EdgeDTO> eit = dto.getEdges().iterator();
		while(eit.hasNext())
		{
			eds.add(new Edge(eit.next()));
		}
		this.setEdges(eds);
		
	}
	
	public void update_dtoIds() {
		this.dto_object.setId(this.id);
		
		Iterator<Node> nit = this.nodes.iterator();
		while(nit.hasNext())
		{
			Node n = nit.next();
			n.update_dtoIds();
		}
		
		Iterator<Edge> eit = this.edges.iterator();
		while(eit.hasNext())
		{
			Edge e = eit.next();
			e.update_dtoIds();
		}
	}

	public NetworkDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new NetworkDTO();
			dto_object.setId(this.getId());
			dto_object.setName(this.getName());
			
			for (Node n : nodes)
				dto_object.addNode(n.getDto_object());
			
			for (Edge e : edges)
				dto_object.addEdge(e.getDto_object());
		}
		return dto_object;
	}

	public void setDto_object(NetworkDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	
}
