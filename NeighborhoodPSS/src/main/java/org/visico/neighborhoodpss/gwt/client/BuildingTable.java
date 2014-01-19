package org.visico.neighborhoodpss.gwt.client;


import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;
import org.visico.neighborhoodpss.gwt.shared.patterns.ScenarioEditMediator;

import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;


public class BuildingTable extends ScrollPanel 
{

	ProjectMediator indMed;
	ScenarioEditMediator scenarioMed;
	
	public BuildingTable(ProjectMediator indMed, ScenarioEditMediator scenarioMed)
	{
		this.indMed = indMed;
		this.scenarioMed = scenarioMed;
		
		setSize("500px", "150px");
		
		buildingGrid = new Grid();
		buildingGrid.setStyleName("BuildingTable");
		this.add(buildingGrid);
		
		draw();
	}
	
	public void draw()
	{
		buildingGrid.clear();
		buildingGrid.resize(1, 1);
		buildingGrid.getRowFormatter().addStyleName(0, "BuildingTableHeader");
		
	}
	
	
	
	public Grid getBuildingGrid() {
		return buildingGrid;
	}

	public void setBuildingGrid(Grid buildingGrid) {
		this.buildingGrid = buildingGrid;
	}



	Grid buildingGrid = null;

}
