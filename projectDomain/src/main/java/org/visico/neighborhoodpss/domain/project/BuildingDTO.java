package org.visico.neighborhoodpss.domain.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gwt.user.client.rpc.IsSerializable;




/** shared class to wrap the building polygon class. This allows saving buildings without polygons,
 * sending them to the server. Buildings are stored with the scenario class. On viewing or saving a 
 * map all BuildingPolygon instances are converted to this class or generated from this class.
 * @author timo
 *
 */


public class BuildingDTO implements Cloneable, IsSerializable
{
	
	// label is automatically created
	private int id;
	private double area;
	private List<GeoPointDTO> points = new ArrayList<GeoPointDTO>();
	private Map<BuildingDataTypeDTO, BuildingDataDTO> data = new HashMap<BuildingDataTypeDTO, BuildingDataDTO>();
	
	public BuildingDTO()
	{
		
	}
	
	public BuildingDTO (BuildingDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		this.setArea(toCopy.getArea());
		// need to clone the points as well
		Iterator<GeoPointDTO> it = toCopy.getPoints().iterator();
		while (it.hasNext())
		{
			GeoPointDTO b = it.next();
			this.getPoints().add(new GeoPointDTO(b));
		}
		
		for (Entry<BuildingDataTypeDTO, BuildingDataDTO> dt : toCopy.getData().entrySet())
			this.getData().put(dt.getKey(), new BuildingDataDTO(dt.getValue()));
		
	}
	
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
	
	public void addPoint(GeoPointDTO p)
	{
		points.add(p);
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

	public Map<BuildingDataTypeDTO, BuildingDataDTO> getData() {
		return data;
	}

	public void setData(Map<BuildingDataTypeDTO, BuildingDataDTO> data) {
		this.data = data;
	}
	
	
	
}
