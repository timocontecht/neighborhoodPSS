package org.visico.neighborhoodpss.client;

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
	static private ModePanel instance = null;
	
	static public ModePanel getInstance()
	{
		if (instance == null)
			instance = new ModePanel();
		return instance;
	}
	
	private ModePanel()
	{
		setWidth("130px");
		
		building_btn = new Button("Add Building");
		add (building_btn);
		building_btn.addClickHandler(this);
		
	}
	
	final private Button building_btn;
	
	
	@Override
	public void onClick(ClickEvent event) 
	{
		
		// set the right mode
		if (event.getSource() == building_btn)
		{
			final Building building = new Building();
				
			Map.getInstance().getMap().addOverlay(building);
			building.setDrawingEnabled();
		}
	}
	
	
	
}
