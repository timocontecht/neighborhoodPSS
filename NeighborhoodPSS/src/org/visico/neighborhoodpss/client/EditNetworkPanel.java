package org.visico.neighborhoodpss.client;

import java.io.IOException;

import org.visico.neighborhoodpss.shared.dto.GeoNetworkDTO;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.geom.Point;
import com.google.gwt.maps.client.geom.Size;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditNetworkPanel extends VerticalPanel implements ClickHandler, ChangeHandler, MapClickHandler
{
	private ScenarioPanel scenarioPanel;
	
	private static MarkerOptions options = MarkerOptions.newInstance();
	  
	
	
	private final Button addNetworkBtn = new Button("Add Network");
	private final ListBox network_lb = new ListBox();
	
	private final TextBox inflow_tb = new TextBox();
	private final TextBox outflow_tb = new TextBox();
	private final ToggleButton addNode_btn = new ToggleButton("Add Node");
	
	private final TextBox capacity_tb = new TextBox();
	private final ToggleButton addEdge_btn = new ToggleButton("Add Edge");
	
	
	
	private GeoNetworkDTO activeNetwork = null;
	
	private boolean edgeCreationMode = false;
	private boolean nodeCreationMode = false;
	
	private static NodeMarker firstPickedNode = null; 
	
	public EditNetworkPanel(ScenarioPanel scenarioPanel)
	{
		this.scenarioPanel = scenarioPanel;
		scenarioPanel.getMap().getMap().addMapClickHandler(this);
		draw();
	}
	
	public void drawNetworkList()
	{
		network_lb.clear();
		for (int i=0; i<scenarioPanel.getNetworkDTOs().size(); i++)
		{
			network_lb.addItem(scenarioPanel.getNetworkDTOs().get(i).getName(), Integer.toString(i));
		}
	}
	
	public void draw()
	{
		
		
		
		this.add(addNetworkBtn);
		addNetworkBtn.addClickHandler(this);
		this.add(new Label("Select network to edit"));
		this.add(network_lb);
		drawNetworkList();
		network_lb.addChangeHandler(this);
		this.add(new Label("inflow node"));
		this.add(inflow_tb);
		this.add(new Label("outflow node"));
		this.add(outflow_tb);
		this.add(addNode_btn);
		addNode_btn.addClickHandler(this);
		this.add(new Label("capacity edge"));
		this.add(capacity_tb);
		this.add(addEdge_btn);
		addEdge_btn.addClickHandler(this);
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == addNetworkBtn)
		{
			AddNetworkDlg nwdlg = new AddNetworkDlg(scenarioPanel);
			nwdlg.show();
			drawNetworkList();
		}
		else if (event.getSource() == addNode_btn)
		{
			if (addNode_btn.isDown())
			{
				nodeCreationMode = true;
				edgeCreationMode = false;
				addEdge_btn.setDown(false);
			}
			else
			{
				scenarioPanel.getMap().getMap().removeMapClickHandler(this);
			}
		}
		else if (event.getSource() == addEdge_btn)
		{
			if (addEdge_btn.isDown())
			{
				edgeCreationMode = true;
				nodeCreationMode = false;
				addNode_btn.setDown(false);
			}
			else
			{
				edgeCreationMode = false;
			}
		}
	}

	@Override
	public void onChange(ChangeEvent event) {
		if (event.getSource() == network_lb)
		{
			activeNetwork = scenarioPanel.getNetworkDTOs().get(network_lb.getSelectedIndex());
		}
		
	}

	@Override
	public void onClick(MapClickEvent event) 
	{
		// TODO Auto-generated method stub
		MapWidget sender = event.getSender();
        Overlay overlay = event.getOverlay();
        LatLng point = event.getLatLng();

        if (overlay instanceof Marker == false && nodeCreationMode) 
        {
        	  Icon icon = Icon.newInstance("res/node.png");
        	  icon.setIconSize(Size.newInstance(8, 8));
        	  icon.setIconAnchor(Point.newInstance(4, 4));
        	  options.setIcon(icon);
        	  sender.addOverlay(new NodeMarker(point, options, this));
        	  
		} 
        
        else if (overlay instanceof NodeMarker && edgeCreationMode)
        {
        	if (firstPickedNode == null)
				firstPickedNode = (NodeMarker)overlay;
			else
			{
				LatLng points[] = new LatLng[2];
				points[0] = firstPickedNode.getLatLng(); points[1] = ((NodeMarker)overlay).getLatLng();
				NetworkEdge edge = new NetworkEdge(points);
				sender.addOverlay(edge);
				firstPickedNode = null;
			}
        }
        
	}


}
