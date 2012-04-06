package org.visico.neighborhoodpss.shared;

import java.util.ArrayList;

/** shared class to wrap the building polygon class. This allows saving buildings without polygons,
 * sending them to the server. Buildings are stored with the scenario class. On viewing or saving a 
 * map all BuildingPolygon instances are converted to this class or generated from this class.
 * @author timo
 *
 */
public class Building
{
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
	
	public ArrayList<Double> getLatPoints()
	{
		return pointsLat;
	}
	
	public ArrayList<Double> getLongPoints()
	{
		return pointsLong;
	}
	
	public void setType(String t)
	{
		type = t;
	}
	
	public String type()
	{
		return type;
	}
	
	public String getType()
	{
		return type;
	}
	
	public double getArea()
	{
		return area;
	}
	
	public void setArea(double a)
	{
		area = a;
	}
	
	private ArrayList<Double> pointsLat = new ArrayList<Double>();
	private ArrayList<Double> pointsLong = new ArrayList<Double>();
	
	private String type;
	private double area;
}
