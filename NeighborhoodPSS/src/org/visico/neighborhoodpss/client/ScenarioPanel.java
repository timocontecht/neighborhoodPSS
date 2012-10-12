package org.visico.neighborhoodpss.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.server.Building;
import org.visico.neighborhoodpss.shared.ScenarioDTO;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class ScenarioPanel extends DockLayoutPanel
{	
	public ScenarioPanel(ScenarioDTO s) 
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
	    
	    Iterator<Building> it = scenario.getBuildings().iterator();
	    while (it.hasNext())
	    {
	    	BuildingPolygon bldgPlg = new BuildingPolygon();
	    	mw.addOverlay(bldgPlg);
	    	bldgPlg.setScenario(it.next());
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
	
	public ScenarioDTO scenario()
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
	
	public Set<Building> getBuildings()
	{
		HashSet<Building> buildings = new HashSet<Building>();
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
	private ScenarioDTO scenario;
}
