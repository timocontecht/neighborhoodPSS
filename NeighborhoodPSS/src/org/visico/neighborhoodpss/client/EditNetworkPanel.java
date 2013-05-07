package org.visico.neighborhoodpss.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.visico.neighborhoodpss.shared.dto.BuildingNetworkDTO;
import org.visico.neighborhoodpss.shared.dto.GeoNetworkDTO;
import org.visico.neighborhoodpss.shared.dto.NetworkDTO;
import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;
import org.visico.neighborhoodpss.shared.patterns.ObserverInterface;
import org.visico.neighborhoodpss.shared.patterns.Subject;

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

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditNetworkPanel extends Composite implements ClickHandler, ChangeHandler, MapClickHandler, ObserverInterface
{
	private ScenarioPanel scenarioPanel;
	
	private final VerticalPanel mainPanel = new VerticalPanel();
	private static MarkerOptions options = MarkerOptions.newInstance();
	private final Button addNetworkBtn = new Button("Add Network");
	private final NetworkTable network_tbl;
	private final Button deleteNetworkBtn = new Button("Delete Network");;
	private final TextBox inflow_tb = new TextBox();
	private final TextBox outflow_tb = new TextBox();
	private final ToggleButton addNode_btn = new ToggleButton("Add Node");
	private final TextBox capacity_tb = new TextBox();
	private final ToggleButton addEdge_btn = new ToggleButton("Add Edge");
	private Label selectedLbl = new Label("Selected: 0 nodes; 0 edges;");
	
	private boolean edgeCreationMode = false;
	private boolean nodeCreationMode = false;
	
	
	private static NodeMarker firstPickedNode = null; 
	
	public EditNetworkPanel(ScenarioPanel scenarioPanel)
	{
		this.scenarioPanel = scenarioPanel;
		this.scenarioPanel.scenario().addObserver(this);
		network_tbl = new NetworkTable(scenarioPanel.scenario());
		scenarioPanel.getMap().getMap().addMapClickHandler(this);
		draw();
	}
	
	
	
	public void draw()
	{
		VerticalPanel addNetwPanel = new VerticalPanel();
		addNetwPanel.setStyleName("boundedVPanel");
		addNetworkBtn.setStyleName("normalButton");
		addNetwPanel.add(addNetworkBtn);
		addNetworkBtn.addClickHandler(this);
		mainPanel.add(addNetwPanel);
		
		VerticalPanel addTblPanel = new VerticalPanel();
		Label netwCaption = new Label("Networks:");
		netwCaption.setStyleName("captionLabel");
		addTblPanel.add(netwCaption);
		addTblPanel.setStyleName("boundedVPanel");
		addTblPanel.add(network_tbl);
		addTblPanel.add(deleteNetworkBtn);
		deleteNetworkBtn.addClickHandler(this);
		mainPanel.add(addTblPanel);
		
		VerticalPanel addBtnPanel = new VerticalPanel();
		addBtnPanel.setStyleName("boundedVPanel");
		addBtnPanel.add(addNode_btn);
		addNode_btn.addClickHandler(this);
		addBtnPanel.add(addEdge_btn);
		addEdge_btn.addClickHandler(this);
		mainPanel.add(addBtnPanel);
		
		VerticalPanel editNetwkCompPanel = new VerticalPanel();
		editNetwkCompPanel.setStyleName("boundedVPanel");
		Label caption = new Label("Element properties:");
		caption.setStyleName("captionLabel");
		editNetwkCompPanel.add(caption);
		
		editNetwkCompPanel.add(new Label("inflow node"));
		editNetwkCompPanel.add(inflow_tb);
		editNetwkCompPanel.add(new Label("outflow node"));
		editNetwkCompPanel.add(outflow_tb);
		editNetwkCompPanel.add(new Label("capacity edge"));
		editNetwkCompPanel.add(capacity_tb);
		selectedLbl.setStyleName("informLabel");
		editNetwkCompPanel.add(selectedLbl);
		mainPanel.add(editNetwkCompPanel);
		
		this.initWidget(mainPanel);
		
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == addNetworkBtn)
		{
			AddNetworkDlg nwdlg = new AddNetworkDlg(scenarioPanel);
			nwdlg.show();
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
		else if (event.getSource() == deleteNetworkBtn)
		{
			//TODO: warning box
			NetworkDTO networkToDelete = network_tbl.getSelectedNetwork();
			if (Window.confirm("Really delete network "+ networkToDelete.getName()))
			{
				if (networkToDelete instanceof GeoNetworkDTO)
					scenarioPanel.scenario().deleteGeoNetwork((GeoNetworkDTO) networkToDelete);
				else if (networkToDelete instanceof BuildingNetworkDTO)
					scenarioPanel.scenario().deleteBuildingNetwork((BuildingNetworkDTO) networkToDelete);
			}
			
		}
	}

	@Override
	public void onChange(ChangeEvent event) {
		if (event.getSource() == network_tbl)
		{
			//activeNetwork = networks.get(network_lb.getSelectedIndex());
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

	@Override
	public void update(Subject o) {
		
	}


}
