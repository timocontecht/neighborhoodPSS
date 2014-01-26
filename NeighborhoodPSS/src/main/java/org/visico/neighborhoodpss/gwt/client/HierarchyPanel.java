package org.visico.neighborhoodpss.gwt.client;

import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HierarchyPanel extends DockLayoutPanel implements ClickHandler
{
	Button addRoot;
	Button saveSession;
	ProjectMediator projectMed;
	VerticalPanel scenarioPanel = new VerticalPanel();
	
	public HierarchyPanel(ProjectMediator projectMed)
	{
		super(Unit.EM);
		this.projectMed = projectMed;
	}
	
	
	
	public void drawLogin()
	{
		this.clear();
		this.add(new Label("Please log in and select a project to start sketch planning!"));
	}
	
	public void drawHierarchyPanel()
	{
		try 
		{
			this.clear();
			
			ScrollPanel s = new ScrollPanel();
			
			VerticalPanel p = new VerticalPanel();
			s.add(p);
			
			scenarioPanel = new VerticalPanel();
			p.add(scenarioPanel);
				
			addRoot = new Button("Add Root Scenario");
		    addRoot.addClickHandler(this);
		    p.add(addRoot);
			    
		    this.addWest(s, 50);
			    
		    // add the indicator selection panel
		    IndicatorSelectionPanel indSelPanel = new IndicatorSelectionPanel(projectMed);
		    projectMed.registerIndicatorSelectionPanel(indSelPanel);
		    this.addEast(indSelPanel, 20);
		    
		    saveSession = new Button("Save Session");
		    saveSession.setSize("50em", "10em");
		    saveSession.addClickHandler(this);
		    this.addSouth(saveSession, 5);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	    
	}
	
	public void addScenarioRootTree(ScenarioTree scenarioTree)
	{
		scenarioPanel.add(scenarioTree);
	}

	@Override
	public void onClick(ClickEvent event) 
	{
		if (event.getSource() == addRoot)
		{
			ScenarioDialog dlg = new ScenarioDialog(projectMed);
			dlg.show();
		}
		else if (event.getSource() == saveSession)
		{
			projectMed.saveProject();
		}
	} 
	
	
	
	public ProjectMediator getIndicatorMed() {
		return projectMed;
	}

	public VerticalPanel getScenarioPanel() {
		return scenarioPanel;
	}



	
}
