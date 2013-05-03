package org.visico.neighborhoodpss.shared.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="GEOCOORDINATE")
public class GeoPointDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4045277191861116497L;
	private double longitude;
	private double latitude;
	private int id;
	
	public GeoPointDTO(GeoPointDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		
		this.setLatitude(toCopy.getLatitude());
		this.setLongitude(toCopy.getLongitude());
	
	}
	
	public GeoPointDTO()
	{
		
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
	
	
}
