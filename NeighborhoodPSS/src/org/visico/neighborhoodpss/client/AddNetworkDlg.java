package org.visico.neighborhoodpss.client;

import net.auroris.ColorPicker.client.ColorPicker;

import org.visico.neighborhoodpss.shared.dto.BuildingNetworkDTO;
import org.visico.neighborhoodpss.shared.dto.GeoNetworkDTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;



public class AddNetworkDlg extends DialogBox implements ClickHandler{
	
	private final TextBox networkName_tb = new TextBox();
	private final CheckBox isBuilding_cb = new CheckBox();
	private final Button addNetworkBtn = new Button("Add Network");
	private final Button cancelBtn = new Button("Cancel");
	private ScenarioPanel scenarioPanel;
	private final ColorPicker picker = new ColorPicker();
	
	public AddNetworkDlg(ScenarioPanel scenarioPanel)
	{
		this.scenarioPanel = scenarioPanel;
		draw();
	}
	
	public void draw()
	{
		VerticalPanel vp = new VerticalPanel();
		vp.add(new Label("network name"));
		vp.add(networkName_tb);
		
		HorizontalPanel checkBoxPanel = new HorizontalPanel();
		checkBoxPanel.add(new Label("building network"));
		checkBoxPanel.add(isBuilding_cb);
		vp.add(checkBoxPanel);
		
		vp.add(new Label("Choose color:"));
		vp.add(picker);
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		addNetworkBtn.addClickHandler(this);
		buttonPanel.add(addNetworkBtn);
		cancelBtn.addClickHandler(this);
		buttonPanel.add(cancelBtn);
		vp.add(buttonPanel);
		
		
		this.add(vp);		
	}

	@Override
	public void onClick(ClickEvent event) 
	{
		if (event.getSource() == addNetworkBtn)
		{
			if (isBuilding_cb.getValue() == Boolean.TRUE)
			{
				BuildingNetworkDTO newNetwork = new BuildingNetworkDTO();
				newNetwork.setName(networkName_tb.getText());
				newNetwork.setColor(picker.getHexColor());
				scenarioPanel.scenario().addBuildingNetworkDTO(newNetwork);
			}
			else
			{
				GeoNetworkDTO newNetwork = new GeoNetworkDTO();
				newNetwork.setName(networkName_tb.getText());
				newNetwork.setColor(picker.getHexColor());
				scenarioPanel.scenario().addGeoNetworkDTO(newNetwork);
			}
			this.hide();
		}
		else if (event.getSource() == cancelBtn)
		{
			this.hide();
		}
	}

	
}
