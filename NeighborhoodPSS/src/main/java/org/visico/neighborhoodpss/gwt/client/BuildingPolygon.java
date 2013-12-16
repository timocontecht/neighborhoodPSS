package org.visico.neighborhoodpss.gwt.client;

import java.util.ArrayList;
import java.util.HashSet;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polygon;




public class BuildingPolygon extends Polygon 
{

	//public static ArrayList<BuildingPolygon>buildings = new ArrayList<BuildingPolygon>();
	//private static HashSet<BuildingPolygon>selected = new HashSet<BuildingPolygon>();
	
	private boolean closed = false;
	
	PolyStyleOptions op = PolyStyleOptions.getInstance();

	public BuildingPolygon() 
	{
		super(new LatLng[0], "0000FF", 1, 1.0, "0000FF", 0.1);
		String color = "#0000FF";
		op.setColor(color);
		this.setFillStyle(op);
	}
	
	
	public BuildingPolygon(LatLng[] points) {
		super(points, "0000FF", 1, 1.0, "0000FF", 0.1);
		String color = "#0000FF";
		op.setColor(color);
		this.setFillStyle(op);
	}


	public boolean isClosed() {
		return closed;
	}


	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public void setScenarioPanel(ScenarioPanel p)
	{
		scenarioPanel = p;
	}
	
	public BuildingDTO getBuilding()
	{
		return building;
	}
	
	public BuildingDTO setBuilding()
	{
		building.getPoints().clear();
		for (int i=0; i<this.getVertexCount(); i++)
		{
			LatLng vertex = this.getVertex(i);
			building.addVertex(vertex.getLatitude(), vertex.getLongitude());
		}
		
		building.setArea(getArea());
		return building;
	}
	
	private BuildingDTO building; 
	
	ScenarioPanel scenarioPanel = null;

	
	public void setScenario(BuildingDTO b) 
	{
		
		for (int i=0; i<b.getPoints().size(); i++)
		{
			
			this.insertVertex(i, LatLng.newInstance(b.getPoints().get(i).getLatitude(), b.getPoints().get(i).getLongitude()));
		}
		
		
	}
	
	public void setColor(boolean isSelected)
	{
		String color;
		if (isSelected)
			color = "#FF6666";
		else
			color = "#0000FF";
		op.setColor(color);
		this.setFillStyle(op);
	}
	
	
	
}
