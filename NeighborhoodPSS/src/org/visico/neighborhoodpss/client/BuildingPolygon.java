package org.visico.neighborhoodpss.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.visico.neighborhoodpss.shared.BuildingDTO;

import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Polygon;


public class BuildingPolygon extends Polygon implements PolygonEndLineHandler
{

	public static ArrayList<BuildingPolygon>buildings = new ArrayList<BuildingPolygon>();

	public static ArrayList<String> industryTypes()
	{
		return industryTypes();
	}
	
	static public List<String> industryTypes = Arrays.asList(
			"Machinery",
			"Transport",
			"Metalling",
			"Construction",
			"Printing",
			"Chemicals");

	public BuildingPolygon() 
	{
		super(new LatLng[0], "0000FF", 1, 1.0, "0000FF", 0.1);
		building = new BuildingDTO();
		addPolygonEndLineHandler(this);
	}
	
	
	
	public BuildingPolygon(BuildingDTO next) 
	{
		super(new LatLng[0], "0000FF", 1, 1.0, "0000FF", 0.1);
		building = new BuildingDTO();
		addPolygonEndLineHandler(this);
	}



	public void setType(String t) 
	{
		type = t;
	}
	
	public String getType()
	{
		return type;
	}
	
	private String type;

	@Override
	public void onEnd(PolygonEndLineEvent event) 
	{
		BuildingDialog dlg = new BuildingDialog(this, scenarioPanel);
		dlg.center();
  	  	dlg.show();
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
		
		building.setType(type);
		building.setArea(getArea());
		return building;
	}
	
	private BuildingDTO building; 
	
	ScenarioPanel scenarioPanel = null;

	public void setScenario(BuildingDTO b) 
	{
		building = b;
		
		for (int i=0; i<b.getPoints().size(); i++)
		{
			this.insertVertex(i, LatLng.newInstance(b.getPoints().get(i).getLatitude(), b.getPoints().get(i).getLongitude()));
		}
		
		this.setType(b.getType());
	}
}
