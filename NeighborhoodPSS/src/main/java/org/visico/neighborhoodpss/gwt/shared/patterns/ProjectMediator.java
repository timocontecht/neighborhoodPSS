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
import org.visico.neighborhoodpss.gwt.client.IndicatorTable;
import org.visico.neighborhoodpss.gwt.client.IndicatorWidget;
import org.visico.neighborhoodpss.gwt.client.MainTab;
import org.visico.neighborhoodpss.gwt.client.ScenarioPanel;
import org.visico.neighborhoodpss.gwt.client.ScenarioService;
import org.visico.neighborhoodpss.gwt.client.ScenarioServiceAsync;
import org.visico.neighborhoodpss.gwt.client.ScenarioTree;
import org.visico.neighborhoodpss.gwt.client.UserPanel;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;
import org.visico.neighborhoodpss.plugin.IndicatorPlugin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;


public class ProjectMediator {
	
	private IndicatorServiceAsync indicatorService = GWT.create(IndicatorService.class);
	private ScenarioServiceAsync scenarioService = GWT.create(ScenarioService.class);
	
	private IndicatorSelectionPanel indicatorSelectionPanel;
	private BuildingTable buildingTable;
	private HierarchyPanel hierarchyPanel;
	private IndicatorTable indicatorTable;
	private MainTab mainPanel;
	private UserPanel userPanel;
	
	private HashMap<String, IndicatorWidget> indicatorWidgets = new HashMap<String, IndicatorWidget>();
	private HashMap<String, IndicatorDTO> allIndicators = new HashMap<String, IndicatorDTO>();
	private HashMap<ScenarioEditMediator, ScenarioPanel> scenarioPanels = new HashMap<ScenarioEditMediator, ScenarioPanel>();
	private Set<BuildingDataTypeDTO> buildingDataTypes = new HashSet<BuildingDataTypeDTO>();
	
	

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

	
	public HashMap<String, IndicatorDTO> getAllIndicators() {
		return allIndicators;
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
	
	public void registerIndicatorTable(IndicatorTable indTable) {
		this.indicatorTable = indTable;
		indTable.addIndicators(allIndicators.keySet());
		
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
				Window.alert("Could not lookup indicators - contact your system administrator");
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
					
					for (ScenarioEditMediator scenarioMed : scenarioPanels.keySet())  {
						scenarioMed.updateIndicators();
					}
				}
				setAdditionalBuildingData();
			}	
		};
		
		indicatorService.getIndicatorList(getProject(), callback);
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
		indicatorService.activateIndicator(getProject(), indicatorName, callback);
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
				indicatorService.deactivateIndicator(getProject(), indicatorName, callback);
	}
	
	public void addParentScenario(String parentName) {
		ScenarioDTO scenario = new ScenarioDTO(parentName);
		project.addParentScenario(scenario);
		drawScenarioHierarchy();
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
		
		scenarioService.saveProject(this.getProject(), callback);
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


	public IndicatorDTO getIndicatorDTO(String title) {
		return allIndicators.get(title);
	}


}
