package org.visico.neighborhoodpss.gwt.client;

import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;
import org.visico.neighborhoodpss.gwt.shared.patterns.ScenarioEditMediator;

import com.google.gwt.user.client.ui.TabPanel;

public class DataPanel extends TabPanel 
{
	ScenarioEditMediator sceMed;
	ProjectMediator indMed;
	
	public DataPanel(ScenarioPanel p, ScenarioEditMediator sceMed, ProjectMediator indMed)
	{
		this.setWidth("20px");
		this.indMed = indMed;
		this.sceMed = sceMed;
		scenarioPanel = p;
		buildingTable = new BuildingTable(indMed, sceMed);
		sceMed.registerBuildingTable(buildingTable);
		add(buildingTable, "Buildings");
	    

	    // Show the 'bar' tab initially.
	    selectTab(0);
	}
	
	public void updateData()
	{
		buildingTable.draw();
	}
	
	BuildingTable buildingTable;
	ScenarioPanel scenarioPanel;
}
