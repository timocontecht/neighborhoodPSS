package org.visico.neighborhoodpss.client;


import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ModePanel extends VerticalPanel implements ClickHandler
{
	//final private Button building_btn;
	final private Button close_btn;
	//final private Button network_btn;
	private EditBuildingPanel editBuildingTab;
	
	final private TabLayoutPanel panel = new TabLayoutPanel(2.5, Unit.EM);
	
	
	
	public EditBuildingPanel getEditBuilding() {
		return editBuildingTab;
	}




	public void setEditBuilding(EditBuildingPanel editBuilding) {
		this.editBuildingTab = editBuilding;
	}




	public ModePanel(ScenarioPanel scenarioPanel)
	{
		this.scenarioPanel = scenarioPanel;
		//setWidth("130px");

		//building_btn = new Button("Add Building");
		//network_btn = new Button("Add Network");
/*		add (building_btn);
		building_btn.addClickHandler(this);
		
		
		add (network_btn);
		network_btn.addClickHandler(this);
*/	
		panel.setSize("20em", "40em");
		editBuildingTab = new EditBuildingPanel(scenarioPanel);
		panel.add(editBuildingTab, "Edit Buildings");
		panel.add(new EditNetworkPanel(), new HTML("Edit Networks"));
	
		add (panel);
		
	    
		close_btn = new Button("Save and Close");
		add (close_btn);
		close_btn.addClickHandler(this);
	}
	
	
	
	
	@Override
	public void onClick(ClickEvent event) 
	{
	/*	
		// set the right mode
		if (event.getSource() == building_btn)
		{
			final BuildingPolygon building = new BuildingPolygon();
			building.setScenarioPanel(scenarioPanel);	
			scenarioPanel.getMap().getMap().addOverlay(building);
			building.setDrawingEnabled();
			
		}
		
		if (event.getSource() == network_btn)
		{
			final NetworkPolygon network = new NetworkPolygon();
			network.setScenarioPanel(scenarioPanel);	
			scenarioPanel.getMap().getMap().addOverlay(network);
			network.setDrawingEnabled();
			
		}
		*/
		if (event.getSource() == close_btn)
		{
			ScenarioDTO scenario = scenarioPanel.scenario();
			scenario.setBuilingDTOs(scenarioPanel.getBuildingDTOs());
			scenario.setNetworkDTOs(scenarioPanel.getNetworkDTOs());
			scenarioPanel.removeFromParent();
		}
	}
	
	ScenarioPanel scenarioPanel;
	
}
