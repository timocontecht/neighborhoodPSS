package org.visico.neighborhoodpss.gwt.shared.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.BuildingDataDTO;
import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.client.BuildingTable;
import org.visico.neighborhoodpss.gwt.client.IndicatorSelectionPanel;
import org.visico.neighborhoodpss.gwt.client.IndicatorService;
import org.visico.neighborhoodpss.gwt.client.IndicatorServiceAsync;
import org.visico.neighborhoodpss.gwt.client.IndicatorWidget;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;


public class ProjectMediator {
	
	IndicatorServiceAsync service = GWT.create(IndicatorService.class);
	IndicatorSelectionPanel indicatorSelectionPanel;
	BuildingTable buildingTable;
	
	HashMap<String, IndicatorWidget> indicatorWidgets = new HashMap<String, IndicatorWidget>();

	private ProjectDTO project;
	
	public ProjectMediator(ProjectDTO project)
	{
		this.project = project;
	}
	
	
	public ProjectDTO getProject() {
		return project;
	}


	public void registerIndicatorSelectionPanel(IndicatorSelectionPanel indicatorSelectionPanel)
	{
		this.indicatorSelectionPanel = indicatorSelectionPanel;
		addExistingIndicators();
	}
	
	public void registerBuildingTable(BuildingTable buildingTable)  {
		this.buildingTable = buildingTable;
		setBuildingData();
	}

	public void addExistingIndicators()
	{	
		indicatorWidgets.clear();
		indicatorSelectionPanel.clear();
		
		AsyncCallback<ArrayList<IndicatorDTO>> callback = new AsyncCallback<ArrayList<IndicatorDTO>>()
		{

			@Override
			public void onFailure(Throwable caught) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<IndicatorDTO> result) 
			{
				for (IndicatorDTO ind : result)
				{
					// set widget
					IndicatorWidget wid = new IndicatorWidget(ProjectMediator.this);
					wid.setDescription(ind.getDescription());
					wid.setName(ind.getName());
					wid.setVersion(ind.getVersion());
					wid.setActivated(ind.getActivated());
					indicatorWidgets.put(ind.getName(), wid);
					indicatorSelectionPanel.addIndicatorWidget(wid);
					
					// add data type to project
					for (BuildingDataTypeDTO dt : ind.getBuildingDataTypes())
					{
						if (project.getBuildingDataTypes().add(dt) )
						{
							// new data type, update all buildings
							for (ScenarioDTO scenario : project.getParent_scenarios())
							{
								addDataToBuildings(scenario, dt);
							}
						}
					}
					
				}
			}

			private void addDataToBuildings(ScenarioDTO scenario, BuildingDataTypeDTO dt) {
				for (ScenarioDTO child : scenario.getChildren())
					addDataToBuildings(child, dt);
				
				for (BuildingDTO building : scenario.getBuildingDTOs())
				{
					BuildingDataDTO data = new BuildingDataDTO();
					data.setBuilding(building);
					data.setType(dt);
					data.setValue(dt.getDefault_val());
					building.getData().add(data);
				}
			}
		};
		
		service.getIndicatorList(getProject(), callback);
	}


	public void registerIndicator(String indicatorName) {
		AsyncCallback<String> callback = new AsyncCallback<String>()
		{
			@Override
			public void onFailure(Throwable caught) 
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(String result) 
			{
				Window.confirm(result);
				addExistingIndicators();
			}
		};
		service.activateIndicator(getProject(), indicatorName, callback);
	}


	public void deregisterIndicator(String indicatorName) {
		AsyncCallback<String> callback = new AsyncCallback<String>()
				{
					@Override
					public void onFailure(Throwable caught) 
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(String result) 
					{
						Window.confirm(result);
						addExistingIndicators();
					}
				};
				service.deactivateIndicator(getProject(), indicatorName, callback);
	}
	
	public void setBuildingData()
	{
		final Grid dataGrid = buildingTable.getBuildingGrid();
		
		AsyncCallback<Set<String>> callback = new AsyncCallback<Set<String>>()
		{
			@Override
			public void onFailure(Throwable caught) 
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(Set<String> result) 
			{
				dataGrid.resize(dataGrid.getRowCount(), result.size() + 1);
				dataGrid.setText(0, 0, "ID");
				int column = 1;
				for (String type : result)
				{
					dataGrid.setText(0, column, type);
					column ++;
				}
				
			}
		};
		
		service.buildingDataTypes(getProject(), callback);
	}

	public void addParentScenario(String parentName) {
		ScenarioDTO scenario = new ScenarioDTO(parentName);
		project.addParentScenario(scenario);
	}
	
}
