package org.visico.neighborhoodpss.gwt.client;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.shared.patterns.IndicatorMediator;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HierarchyPanel extends DockLayoutPanel implements ClickHandler
{
	static private HierarchyPanel instance = null;
	
	static public HierarchyPanel getInstance()
	{
		if (instance == null)
			instance = new HierarchyPanel();
		return instance;
	}
	
	private HierarchyPanel()
	{
		super(Unit.EM);
		parentScenarios = new HashSet<ScenarioDTO>();
		draw();
	}
	
	Set<ScenarioDTO> parentScenarios;
	Button addRoot;
	Button saveSession;
	IndicatorMediator indicatorMed;
	
	public void setProject(ProjectDTO project)
	{
		this.project = project;
		parentScenarios.clear();
		parentScenarios.addAll(project.getParent_scenarios());
		indicatorMed = new IndicatorMediator(project);
		draw();
	}
	
	public void draw()
	{
		try 
		{
			this.clear();
			
			if (project == null)
			{
				this.add(new Label("Please log in and select a project to start sketch planning!"));
			}
			else
			{
				VerticalPanel p = new VerticalPanel();
				//p.setStyleName("boundedVPanel");
				ScrollPanel s = new ScrollPanel();
				//s.setSize("40em", "40em");
				s.add(p);
				VerticalPanel scenarioPanel = new VerticalPanel();
				
				Iterator<ScenarioDTO> it = parentScenarios.iterator();
				while(it.hasNext())
				{
					ScenarioTree st = new ScenarioTree(it.next());
					scenarioPanel.add(st);
				}
				
				p.add(scenarioPanel);
				
				addRoot = new Button("Add Root Scenario");
			    addRoot.addClickHandler(this);
			    p.add(addRoot);
			    
			    this.addWest(s, 50);
			    
			    // add the indicator selection panel
			    IndicatorSelectionPanel indSelPanel = new IndicatorSelectionPanel(indicatorMed);
			    indicatorMed.registerIndicatorSelectionPanel(indSelPanel);
			    this.addEast(indSelPanel, 20);
			    
			    saveSession = new Button("Save Session");
			    //saveSession.setSize("50em", "10em");
			    saveSession.addClickHandler(this);
			    this.addSouth(saveSession, 5);
			}
		    
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	    
	}
	
	

	@Override
	public void onClick(ClickEvent event) 
	{
		if (event.getSource() == addRoot)
		{
			ScenarioDialog dlg = new ScenarioDialog(this);
			dlg.show();
		}
		else if (event.getSource() == saveSession)
		{
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
				}
			};
			
			service.saveProject(this.getProject(), callback);
			
		}
	} 
	
	public void addParentScenario(ScenarioDTO s)
	{
		parentScenarios.add(s);
		project.addParentScenario(s);
		draw();
	}

	public void printScenarios() 
	{
		Iterator<ScenarioDTO> it = parentScenarios.iterator();
		while (it.hasNext())
		{
			ScenarioDTO next = it.next();
			Iterator<BuildingDTO> bit = next.getBuildingDTOs().iterator();
			 while (bit.hasNext())
			 {
				 
			 }
		
		}
		
	}
	
	public ProjectDTO getProject() {
		return project;
	}

	private ProjectDTO project; 
	
}
