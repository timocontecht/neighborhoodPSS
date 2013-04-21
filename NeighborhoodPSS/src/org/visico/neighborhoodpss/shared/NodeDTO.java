package org.visico.neighborhoodpss.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BUILDING_COORDINATE")
public class NodeDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4045277191861116497L;
	private int id;
	private double longitude;
	private double latitude;
	private double inflow;
	private double outflow;
	
	
	public int getId() {
		return id;
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
	
	public Object clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		NodeDTO clone = new NodeDTO();
		
		clone.setLatitude(this.getLatitude());
		clone.setLongitude(this.getLongitude());
		clone.setInflow(this.getInflow());
		clone.setOutflow(this.getOutflow());
	
		return clone;
	}
}
