package org.visico.neighborhoodpss.client;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.shared.BuildingDTO;
import org.visico.neighborhoodpss.shared.GeoNetworkDTO;
import org.visico.neighborhoodpss.shared.ScenarioDTO;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class ScenarioPanel extends DockLayoutPanel
{	
	public ScenarioPanel(ScenarioDTO s) 
	{
		super(Unit.EX);
		scenario = s;
		this.setTitle("Scenarios");
		draw();
	}
	
	public void draw()
	{
		map = new Map();
		modePanel = new ModePanel(this);
		dataPanel = new DataPanel(this);
	    addWest(modePanel, 35);
	    addSouth(dataPanel, 25);
	    MapWidget mw = map.getMap();
	    add(mw);
	    
	    Iterator<BuildingDTO> it = scenario.getBuildingDTOs().iterator();
	    while (it.hasNext())
	    {
	    	BuildingDTO b = it.next();
	    	BuildingPolygon bldgPlg = new BuildingPolygon(b);
	    	mw.addOverlay(bldgPlg);
	    	bldgPlg.setScenario(b);
	    	buildingPlgs.add(bldgPlg);
	    }
	    
	    networks.addAll(scenario.getNetworkDTOs());
	    
	    for (GeoNetworkDTO n : networks)
	    {
	    	//TODO: here add physical nodes and edges
	    	
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

	public ArrayList<GeoNetworkDTO> getNetworkDTOs()
	{
		
		return networks;
	}
	
	public void addBuilding(BuildingPolygon p)
	{
		buildingPlgs.add(p);
	}
	
	public ArrayList<BuildingPolygon> getBuildingPlgs()
	{
		return buildingPlgs;
	}
	
	public void addNetwork (GeoNetworkDTO nw)
	{
		networks.add(nw);
	}


	public Set<BuildingDTO> getBuildingDTOs()
	{
		HashSet<BuildingDTO> BuildingDTOs = new HashSet<BuildingDTO>();
		for (int i=0; i < getBuildingPlgs().size(); i++)
		{
			BuildingDTOs.add(getBuildingPlgs().get(i).setBuilding());
		}
		return BuildingDTOs;
	}
	
	
	public ModePanel getModePanel() {
		return modePanel;
	}

	public void setModePanel(ModePanel modePanel) {
		this.modePanel = modePanel;
	}


	private Map map; 
	private ModePanel modePanel;
	private DataPanel dataPanel;
	private ArrayList<BuildingPolygon> buildingPlgs = new ArrayList<BuildingPolygon>();
	private ArrayList<GeoNetworkDTO> networks = new ArrayList<GeoNetworkDTO>();
	private ScenarioDTO scenario;
	
}
