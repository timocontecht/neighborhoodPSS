package org.visico.neighborhoodpss.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="BUILDING_COORDINATE")
public class GeoPointDTO implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4045277191861116497L;
	private double longitude;
	private double latitude;
	
	
	
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
	
	public GeoPointDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		GeoPointDTO clone = new GeoPointDTO();
		
		clone.setLatitude(this.getLatitude());
		clone.setLongitude(this.getLongitude());
	
		return clone;
	}
}
