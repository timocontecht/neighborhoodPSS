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
import org.visico.neighborhoodpss.gwt.client.HierarchyPanel;
import org.visico.neighborhoodpss.gwt.client.IndicatorSelectionPanel;
import org.visico.neighborhoodpss.gwt.client.IndicatorService;
import org.visico.neighborhoodpss.gwt.client.IndicatorServiceAsync;
import org.visico.neighborhoodpss.gwt.client.IndicatorWidget;
import org.visico.neighborhoodpss.gwt.client.MainTab;
import org.visico.neighborhoodpss.gwt.client.ScenarioPanel;
import org.visico.neighborhoodpss.gwt.client.ScenarioService;
import org.visico.neighborhoodpss.gwt.client.ScenarioServiceAsync;
import org.visico.neighborhoodpss.gwt.client.ScenarioTree;
import org.visico.neighborhoodpss.gwt.client.UserPanel;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class ProjectMediator {
	
	IndicatorServiceAsync service = GWT.create(IndicatorService.class);
	IndicatorSelectionPanel indicatorSelectionPanel;
	BuildingTable buildingTable;
	HierarchyPanel hierarchyPanel;
	MainTab mainPanel;
	UserPanel userPanel;
	
	HashMap<String, IndicatorWidget> indicatorWidgets = new HashMap<String, IndicatorWidget>();
	HashMap<String, IndicatorDTO> allIndicators = new HashMap<String, IndicatorDTO>();
	HashMap<ScenarioEditMediator, ScenarioPanel> scenarioPanels = new HashMap<ScenarioEditMediator, ScenarioPanel>();
	Set<BuildingDataTypeDTO> buildingDataTypes = new HashSet<BuildingDataTypeDTO>();
	
	

	private ProjectDTO project;
	
	public ProjectMediator()  {
		
	}
	
	
	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project)  {
		this.project = project;
		hierarchyPanel.drawHierarchyPanel();
		drawScenarioHierarchy(); 
	}

	public Set<BuildingDataTypeDTO> getBuildingDataTypes() {
		return buildingDataTypes;
	}


	public void registerIndicatorSelectionPanel(IndicatorSelectionPanel indicatorSelectionPanel)
	{
		this.indicatorSelectionPanel = indicatorSelectionPanel;
		addExistingIndicators();
	}
	
	public void registerHierachyPanel (HierarchyPanel hierarchyPanel)  {
		this.hierarchyPanel = hierarchyPanel;
	}
	
	public void registerMainPanel(MainTab panel) {
		this.mainPanel = panel;
		
	}


	public void registerUserPanel(UserPanel userPanel) {
		this.userPanel = userPanel;
		
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
					if (buildingDataTypes.contains(type) == false)
						buildingDataTypes.add(type);
					else  {
						for (BuildingDataTypeDTO nextType : buildingDataTypes)  {
							if (nextType.equals(type)) 
								type = nextType;
						}
					}
							
					for (ScenarioDTO scenario : project.getParent_scenarios())
						addDataToBuildings(scenario, type);
				}
			}
		}
		
		/*for (ScenarioPanel p : scenarioPanels)  {
			p.getScenarioMed().changeAdditionalBuildingData();
		}*/
	}

	public void addScenarioComp(ScenarioDTO scenario) {
		ScenarioEditMediator scenarioMed = new ScenarioEditMediator(scenario, this);
		ScenarioPanel dock = new ScenarioPanel(scenario, scenarioMed, this);
		scenarioMed.registerScenarioPanel(dock);
        dock.setTitle(scenario.getName() + " " + scenario.label());
		mainPanel.addScenarioPanel(dock);
		scenarioPanels.put(scenarioMed, dock);
	}

	

	public void saveProject() {
		// add all active additional data to project
		setAdditionalBuildingData();
		project.setBuildingDataTypes(buildingDataTypes);
		
		ScenarioServiceAsync service = GWT.create(ScenarioService.class);
		
		AsyncCallback<ProjectDTO> callback = new AsyncCallback<ProjectDTO>()
		{

			@Override
			public void onFailure(Throwable caught) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ProjectDTO result) 
			{
				Window.alert("Saved scenarios!");
				
				// need to overwrite scnearios to get id from server for database persistence
				project = result;
				synchronizeScenarioPanels();
			}

			
		};
		
		service.saveProject(this.getProject(), callback);
	}

	private void synchronizeScenarioPanels()  {
		try {
			for (ScenarioEditMediator scenarioMed : scenarioPanels.keySet())  {
				ScenarioDTO scenario = project.getScenarioByLabel(
						scenarioMed.getScenario().getName(),
						scenarioMed.getScenario().getLabel());
				scenarioMed.setScenario(scenario);
				scenarioPanels.get(scenarioMed).draw();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void drawScenarioHierarchy()  {
		hierarchyPanel.getScenarioPanel().clear();
		for (ScenarioDTO scenario : project.getParent_scenarios())  {
			ScenarioTree scTree = new ScenarioTree(scenario, this);
			hierarchyPanel.addScenarioRootTree(scTree);
		}
	}

	public double getLatitude() {
		return project.getLatitude();
	}

	public double getLongitude() {
		return project.getLongitude();
	}
}
