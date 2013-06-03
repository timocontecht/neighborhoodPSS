package org.visico.neighborhoodpss.server.project;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.shared.dto.GeoPointDTO;

@Entity
@Table(name="GEOCOORDINATE")
public class GeoPoint implements Serializable
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
	
	@Transient
	private GeoPointDTO dto_object = null;
	
	public GeoPoint()
	{
		
	}
	
	public GeoPoint(GeoPointDTO pt) 
	{
		this.dto_object = pt;
		this.setId(pt.getId());
		this.setLatitude(pt.getLatitude());
		this.setLongitude(pt.getLongitude());
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

	public void update_dtoIds() 
	{
		this.dto_object.setId(this.id);
		
	}

	public GeoPointDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new GeoPointDTO();
			dto_object.setId(this.getId());
			dto_object.setLatitude(this.getLatitude());
			dto_object.setLongitude(this.getLongitude());
		}
		
		return dto_object;
	}

	public void setDto_object(GeoPointDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	
	
	
	
}
