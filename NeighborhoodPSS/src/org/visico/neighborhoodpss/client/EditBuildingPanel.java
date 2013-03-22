package org.visico.neighborhoodpss.client;

import java.util.HashSet;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditBuildingPanel extends VerticalPanel implements ClickHandler
{
	final private ListBox type_lb = new ListBox();
	final private Button building_btn = new Button("Add Building");
	final private Button unselect_btn = new Button("Unselect All");
	private ScenarioPanel scenarioPanel;
	HashSet<BuildingPolygon> selected = new HashSet<BuildingPolygon>();
	
	public EditBuildingPanel(ScenarioPanel scenarioPanel)
	{
		this.scenarioPanel = scenarioPanel;
		draw();
	}
	
	public void draw()
	{
		for (int i=0; i<BuildingPolygon.industryTypes.size(); i++)
		{
			type_lb.addItem(BuildingPolygon.industryTypes.get(i));
		}
		add(type_lb);
		
		building_btn.addClickHandler(this);
		add (building_btn);
		
		unselect_btn.addClickHandler(this);
	}
	
	public void selectedBuildings(HashSet<BuildingPolygon> selected)
	{
		if (selected.size() != 0)
		{
			building_btn.setText("Change Selected");
			this.selected = selected;
			add (unselect_btn);
		}
		else
		{
			building_btn.setText("Add Building");
			this.selected.clear();
			remove(unselect_btn);
		}
	}
	
	
	public void onClick(ClickEvent event) 
	{
		// set the right mode
		if (event.getSource() == building_btn)
		{
			if (BuildingPolygon.getSelected().isEmpty())
			{
				final BuildingPolygon building = new BuildingPolygon();
				building.setScenarioPanel(scenarioPanel);	
				building.setType(type_lb.getItemText(type_lb.getSelectedIndex()));
				scenarioPanel.getMap().getMap().addOverlay(building);
				building.setDrawingEnabled();
			}
			else
			{
				for (BuildingPolygon p:BuildingPolygon.getSelected())
				{
					p.setType(type_lb.getItemText(type_lb.getSelectedIndex()));
				}
				scenarioPanel.updateData();
			}
		}
		
		if (event.getSource() == unselect_btn)
		{
			BuildingPolygon.unselectAll();
			selectedBuildings(BuildingPolygon.getSelected());
		}
		
	}
}
