package org.visico.neighborhoodpss.server;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.shared.EdgeDTO;

@Entity
@Table(name="EDGE")

public class Edge implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4642958911636611366L;

	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(cascade = CascadeType.ALL)
	private Node start_node;
	@ManyToOne(cascade = CascadeType.ALL)
	private Node end_node;
	@Column
	double capacity;
	
	@Transient
	private EdgeDTO dto_object = null;

	public Edge ()
	{
		
	}
	
	public Edge(EdgeDTO dto)
	{
		this.capacity = dto.getCapacity();
		this.dto_object = dto;
		this.end_node = new Node(dto.getEnd_node());
		this.start_node = new Node(dto.getStart_node());
		this.id = dto.getId();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Node getStart_node() {
		return start_node;
	}

	public void setStart_node(Node start_node) {
		this.start_node = start_node;
	}

	public Node getEnd_node() {
		return end_node;
	}

	public void setEnd_node(Node end_node) {
		this.end_node = end_node;
	}

	public double getCapacity() {
		return capacity;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public EdgeDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new EdgeDTO();
			dto_object.setId(this.getId());
			dto_object.setStart_node(this.getStart_node().getDto_object());
			dto_object.setEnd_node(this.getEnd_node().getDto_object());
			dto_object.setCapacity(this.getCapacity());
		}
		return dto_object;
	}

	public void setDto_object(EdgeDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	public void update_dtoIds()
	{
		this.dto_object.setId(this.id);
	}
	
}
