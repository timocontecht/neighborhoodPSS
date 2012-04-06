package org.visico.neighborhoodpss.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.Building;
import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class ScenarioPanel extends DockLayoutPanel
{	
	public ScenarioPanel(Scenario s) 
	{
		super(Unit.PX);
		scenario = s;
		this.setTitle("Scenarios");
		draw();
	}
	
	public void draw()
	{
		map = new Map();
		modePanel = new ModePanel(this);
		dataPanel = new DataPanel(this);
	    addWest(modePanel, 150);
	    addSouth(dataPanel, 250);
	    MapWidget mw = map.getMap();
	    add(mw);
	    
	    for (int i=0; i<scenario.getBuildings().size(); i++)
	    {
	    	BuildingPolygon bldgPlg = new BuildingPolygon();
	    	mw.addOverlay(bldgPlg);
	    	bldgPlg.setScenario(scenario.getBuildings().get(i));
	    }
	    
	}
	
	public Map getMap()
	{
		return map;
	}
	
	public void updateData()
	{
		dataPanel.updateData();
	}
	
	public Scenario scenario()
	{
		return scenario;
	}
	
	public void addBuilding(BuildingPolygon p)
	{
		buildings.add(p);
	}
	
	public ArrayList<BuildingPolygon> getBuildingPlgs()
	{
		return buildings;
	}
	
	public ArrayList<Building> getBuildings()
	{
		ArrayList<Building> buildings = new ArrayList<Building>();
		for (int i=0; i < getBuildingPlgs().size(); i++)
		{
			buildings.add(getBuildingPlgs().get(i).setBuilding());
		}
		return buildings;
	}
	
	private Map map; 
	private ModePanel modePanel;
	private DataPanel dataPanel;
	private ArrayList<BuildingPolygon>buildings = new ArrayList<BuildingPolygon>();
	private Scenario scenario;
}
