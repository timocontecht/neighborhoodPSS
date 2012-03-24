package org.visico.neighborhoodpss.client;


import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler;
import com.google.gwt.maps.client.event.PolygonEndLineHandler;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler;
import com.google.gwt.maps.client.event.PolygonCancelLineHandler.PolygonCancelLineEvent;
import com.google.gwt.maps.client.event.PolygonEndLineHandler.PolygonEndLineEvent;
import com.google.gwt.maps.client.event.PolygonLineUpdatedHandler.PolygonLineUpdatedEvent;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.PolyStyleOptions;
import com.google.gwt.maps.client.overlay.Polygon;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ModePanel extends VerticalPanel implements ClickHandler
{

	public ModePanel(ScenarioPanel p)
	{
		scenarioPanel = p;
		setWidth("130px");
		
		building_btn = new Button("Add Building");
		add (building_btn);
		building_btn.addClickHandler(this);
		
		close_btn = new Button("Save and Close");
		add (close_btn);
		close_btn.addClickHandler(this);
	}
	
	final private Button building_btn;
	
	final private Button close_btn;
	
	
	@Override
	public void onClick(ClickEvent event) 
	{
		
		// set the right mode
		if (event.getSource() == building_btn)
		{
			final BuildingPolygon building = new BuildingPolygon();
			building.setScenarioPanel(scenarioPanel);	
			scenarioPanel.getMap().getMap().addOverlay(building);
			building.setDrawingEnabled();
			Scenario scenario = scenarioPanel.scenario();
			
		}
		
		if (event.getSource() == close_btn)
		{
			Scenario scenario = scenarioPanel.scenario();
			scenario.setBuildings(scenarioPanel.getBuildings());
			scenarioPanel.removeFromParent();
		}
	}
	
	ScenarioPanel scenarioPanel;
	
}
