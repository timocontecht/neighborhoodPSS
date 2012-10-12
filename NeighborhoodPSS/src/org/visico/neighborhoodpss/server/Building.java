package org.visico.neighborhoodpss.server;

import java.io.Serializable;
import java.util.ArrayList;
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




/** shared class to wrap the building polygon class. This allows saving buildings without polygons,
 * sending them to the server. Buildings are stored with the scenario class. On viewing or saving a 
 * map all BuildingPolygon instances are converted to this class or generated from this class.
 * @author timo
 *
 */

@Entity
@Table(name="BUILDING")
public class Building implements Serializable
{
	
	// label is automatically created
	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String industry;
	@Transient
	private double area;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="building_id")
	private List<GeoPoint> points = new ArrayList<GeoPoint>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6838190883337046022L;
	
	
	public void addVertex(double lat, double lon)
	{
		GeoPoint pt = new GeoPoint();
		pt.setLatitude(lat);
		pt.setLongitude(lon);
		
		points.add(pt);
	}
	
	public List<GeoPoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<GeoPoint> points) {
		this.points = points;
	}

	public String getType() {
		return industry;
	}

	public void setType(String type) {
		this.industry = type;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}


	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
		//TODO: this.dto_object.setId(id);
	}
	
}
