package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.ArrayList;

/** shared class to wrap the building polygon class. This allows saving buildings without polygons,
 * sending them to the server. Buildings are stored with the scenario class. On viewing or saving a 
 * map all BuildingPolygon instances are converted to this class or generated from this class.
 * @author timo
 *
 */
public class Building implements Serializable
{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6838190883337046022L;
	
	public void setPoints(ArrayList<Double> lat, ArrayList<Double> lon)
	{
		pointsLat = lat;
		pointsLong = lon;
	}
	
	public void addVertex(double lat, double lon)
	{
		pointsLat.add(lat);
		pointsLong.add(lon);
	}
	
	
	
	public ArrayList<Double> getPointsLat() {
		return pointsLat;
	}

	public void setPointsLat(ArrayList<Double> pointsLat) {
		this.pointsLat = pointsLat;
	}

	public ArrayList<Double> getPointsLong() {
		return pointsLong;
	}

	public void setPointsLong(ArrayList<Double> pointsLong) {
		this.pointsLong = pointsLong;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}



	private ArrayList<Double> pointsLat = new ArrayList<Double>();
	private ArrayList<Double> pointsLong = new ArrayList<Double>();
	
	private String type;
	private double area;
}
