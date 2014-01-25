package org.visico.neighborhoodpss.gwt.shared.patterns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import org.visico.neighborhoodpss.gwt.client.MainTab;
import org.visico.neighborhoodpss.gwt.client.ScenarioPanel;
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
	HashMap<String, IndicatorDTO> allIndicators = new HashMap<String, IndicatorDTO>();
	
	Set<BuildingDataTypeDTO> buildingDataTypes = new HashSet<BuildingDataTypeDTO>();
	Set<ScenarioPanel> scenarioPanels = new HashSet<ScenarioPanel>();
	

	private ProjectDTO project;
	
	public ProjectMediator(ProjectDTO project)
	{
		this.project = project;
	}
	
	
	public ProjectDTO getProject() {
		return project;
	}

	

	public Set<BuildingDataTypeDTO> getBuildingDataTypes() {
		return buildingDataTypes;
	}


	public void registerIndicatorSelectionPanel(IndicatorSelectionPanel indicatorSelectionPanel)
	{
		this.indicatorSelectionPanel = indicatorSelectionPanel;
		addExistingIndicators();
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
					allIndicators.put(ind.getName(), ind);
				}
				setAdditionalBuildingData();
			}	
		};
		
		service.getIndicatorList(getProject(), callback);
	}
	
	private void addDataToBuildings(ScenarioDTO scenario, BuildingDataTypeDTO dt) {
		
		for (ScenarioDTO child : scenario.getChildren())
			addDataToBuildings(child, dt);
		
		for (BuildingDTO building : scenario.getBuildingDTOs())
		{
			if (building.getData().containsKey(dt) == false)  {
				BuildingDataDTO data = new BuildingDataDTO();
				data.setBuilding(building);
				data.setType(dt);
				data.setValue(dt.getDefault_val());
				building.getData().put(data.getType(), data);
			}
		}
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
	
	public void addParentScenario(String parentName) {
		ScenarioDTO scenario = new ScenarioDTO(parentName);
		project.addParentScenario(scenario);
	}
	
	private void setAdditionalBuildingData()  {
		buildingDataTypes.clear();
		
		for (String indicatorStr : indicatorWidgets.keySet())  {
			IndicatorDTO indicator = allIndicators.get(indicatorStr);
			if (indicator.getActivated())  {
				for (BuildingDataTypeDTO type : indicator.getBuildingDataTypes() ) {
					buildingDataTypes.add(type);
					
					for (ScenarioDTO scenario : project.getParent_scenarios())
						addDataToBuildings(scenario, type);
				}
			}
		}
		
		for (ScenarioPanel p : scenarioPanels)  {
			p.getScenarioMed().changeAdditionalBuildingData();
		}
	}


	public void addScenarioComp(ScenarioDTO scenario) {
		MainTab.getInstance().addScenario(scenario);

	}


	public void addNewScenarioPanel(ScenarioPanel dock) {
		scenarioPanels.add(dock);
	}
	
}
