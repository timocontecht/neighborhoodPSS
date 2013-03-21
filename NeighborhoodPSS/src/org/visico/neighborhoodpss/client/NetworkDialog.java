package org.visico.neighborhoodpss.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NetworkDialog extends DialogBox implements ClickHandler
{
	public NetworkDialog(NetworkPolygon n, ScenarioPanel p)
	{
		VerticalPanel panel = new VerticalPanel();
		panel.add (new Label("Choose the capacity of the network points:"));
		
		capacity = new TextBox();
		panel.add(capacity);
		
		Button cancel = new Button("OK");
		cancel.addClickHandler(this);
		panel.add(cancel);
		
		add(panel);
		
		networkPlg = n;
		scenarioPanel = p;
	}
	
	
	
	private TextBox capacity;

	@Override
	public void onClick(ClickEvent event) 
	{
		NetworkDialog.this.hide(true);
		networkPlg.setCapacity(Double.parseDouble(capacity.getText()));
		NetworkPolygon.networks.add(networkPlg);
		scenarioPanel.addNetwork(networkPlg);
		scenarioPanel.updateData();
		
	}
	
	private NetworkPolygon networkPlg = null;
	private ScenarioPanel scenarioPanel = null;
}
