package org.visico.neighborhoodpss.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.DockLayoutPanel;

public class ScenarioPanel extends DockLayoutPanel
{	
	public ScenarioPanel(int scenarioid) 
	{
		super(Unit.PX);
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
	
	private Map map; 
	private ModePanel modePanel;
	private DataPanel dataPanel;
	private int scenarioId;
}
