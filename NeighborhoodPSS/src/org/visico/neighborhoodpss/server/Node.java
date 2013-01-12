package org.visico.neighborhoodpss.server;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.visico.neighborhoodpss.shared.NodeDTO;

@Entity
@Table(name="NODE")
public class Node implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9143822400133445446L;

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private double longitude;
	
	@Column
	private double latitude;
	
	@Column
	private double inflow;
	
	@Column
	private double outflow;
	
	

	@Transient
	private NodeDTO dto_object;
	
	public Node()
	{
		
	}
	
	public Node(NodeDTO n) 
	{
		this.dto_object = n;
		this.setId(n.getId());
		this.setLatitude(n.getLatitude());
		this.setLongitude(n.getLongitude());
		this.setInflow(n.getInflow());
		this.setOutflow(n.getOutflow());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getInflow() {
		return inflow;
	}

	public void setInflow(double inflow) {
		this.inflow = inflow;
	}

	public double getOutflow() {
		return outflow;
	}

	public void setOutflow(double outflow) {
		this.outflow = outflow;
	}
	public void update_dtoIds() 
	{
		this.dto_object.setId(this.id);
	}
	
}
