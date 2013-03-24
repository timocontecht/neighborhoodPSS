package org.visico.neighborhoodpss.client;

import java.io.IOException;

import org.visico.neighborhoodpss.shared.NetworkDTO;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Icon;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.maps.client.overlay.Overlay;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditNetworkPanel extends VerticalPanel implements ClickHandler, ChangeHandler, MapClickHandler
{
	private ScenarioPanel scenarioPanel;
	
	private final TextBox networkName_tb = new TextBox();
	private final Button addNetworkBtn = new Button("Add Network");
	private final ListBox network_lb = new ListBox();
	
	private final TextBox inflow_tb = new TextBox();
	private final TextBox outflow_tb = new TextBox();
	private final ToggleButton addNode_btn = new ToggleButton("Add Node");
	
	private final TextBox capacity_tb = new TextBox();
	private final Button addEdge_btn = new Button("Add Edge");
	
	private NetworkDTO activeNetwork = null;
	
	public EditNetworkPanel(ScenarioPanel scenarioPanel)
	{
		this.scenarioPanel = scenarioPanel;
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
		this.add(networkName_tb);
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
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == addNetworkBtn)
		{
			NetworkDTO newNetwork = new NetworkDTO();
			newNetwork.setName(networkName_tb.getText());
			scenarioPanel.addNetwork(newNetwork);
			drawNetworkList();
		}
		else if (event.getSource() == addNode_btn)
		{
			if (addNode_btn.isDown())
			{
				scenarioPanel.getMap().getMap().addMapClickHandler(this);
			}
			else
			{
				scenarioPanel.getMap().getMap().removeMapClickHandler(this);
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
	public void onClick(MapClickEvent event) {
		// TODO Auto-generated method stub
		MapWidget sender = event.getSender();
        Overlay overlay = event.getOverlay();
        LatLng point = event.getLatLng();

        if (overlay != null && overlay instanceof Marker) {
          sender.removeOverlay(overlay);
        } else {
          try {
        	  Icon icon = Icon.newInstance("res/node.png");
        	  MarkerOptions options = MarkerOptions.newInstance();
        	  options.setIcon(icon);
        	  sender.addOverlay(new NodeMarker(point, options));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
	}
}
