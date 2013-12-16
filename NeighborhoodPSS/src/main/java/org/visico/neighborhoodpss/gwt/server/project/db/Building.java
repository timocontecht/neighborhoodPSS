package org.visico.neighborhoodpss.gwt.server.project.db;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.GeoPointDTO;




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
	
	
	@Transient
	private double area;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="building_id")
	private List<GeoPoint> points = new ArrayList<GeoPoint>();
	
	@Transient
	private BuildingDTO dto_object = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6838190883337046022L;
	
	
	public Building(BuildingDTO b) 
	{
		
		this.dto_object = b;
		this.setArea(b.getArea());
		this.setId(b.getId());
		
		ArrayList<GeoPoint> pts = new ArrayList<GeoPoint>();
		Iterator<GeoPointDTO> it = b.getPoints().iterator();
		while (it.hasNext())
		{
			pts.add(new GeoPoint(it.next()));
		}
		
		this.setPoints(pts);
	
		
	}

	public Building() 
	{

	}
	
/*
	public void addVertex(double lat, double lon)
	{
		GeoPoint pt = new GeoPoint();
		pt.setLatitude(lat);
		pt.setLongitude(lon);
		
		points.add(pt);
	}*/
	
	public List<GeoPoint> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<GeoPoint> points) {
		this.points = points;
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
	}
	
	

	public void update_dtoIds() {
		this.dto_object.setId(this.id);
		Iterator<GeoPoint> pit = this.points.iterator();
		while(pit.hasNext())
		{
			GeoPoint pt = pit.next();
			pt.update_dtoIds();
		}
	}

	public BuildingDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new BuildingDTO();
			dto_object.setId(this.getId());
			dto_object.setArea(this.getArea());
			
			for (GeoPoint p : points)
				dto_object.addPoint(p.getDto_object());
		}
		return dto_object;
	}

	public void setDto_object(BuildingDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	
	
}
