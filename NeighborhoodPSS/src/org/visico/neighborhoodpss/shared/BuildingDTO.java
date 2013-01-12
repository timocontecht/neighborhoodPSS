package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;




/** shared class to wrap the building polygon class. This allows saving buildings without polygons,
 * sending them to the server. Buildings are stored with the scenario class. On viewing or saving a 
 * map all BuildingPolygon instances are converted to this class or generated from this class.
 * @author timo
 *
 */


public class BuildingDTO implements Cloneable, Serializable
{
	
	// label is automatically created
	private int id;
	private String industry;
	private double area;
	private List<GeoPointDTO> points = new ArrayList<GeoPointDTO>();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6838190883337046022L;
	
	
	public void addVertex(double lat, double lon)
	{
		GeoPointDTO pt = new GeoPointDTO();
		pt.setLatitude(lat);
		pt.setLongitude(lon);
		
		points.add(pt);
	}
	
	public List<GeoPointDTO> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<GeoPointDTO> points) {
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
	}
	
	public BuildingDTO clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		BuildingDTO clone = new BuildingDTO();
		
		clone.setArea(this.getArea());
		//clone.setId(this.getId());
		clone.setType(this.getType());
		
		// need to clone the points as well
		Iterator<GeoPointDTO> it = this.getPoints().iterator();
		while (it.hasNext())
		{
			GeoPointDTO b = it.next();
			clone.getPoints().add(b.clone());
		}
		
		return clone;
	}
	
}
