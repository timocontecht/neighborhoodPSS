package org.visico.neighborhoodpss.client;

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
		dataPanel = new DataPanel();
	    addWest(modePanel, 150);
	    addSouth(dataPanel, 250);
	    MapWidget mw = map.getMap();
	    add(mw);
	    
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
	
	private Map map; 
	private ModePanel modePanel;
	private DataPanel dataPanel;
	private Scenario scenario;
}
