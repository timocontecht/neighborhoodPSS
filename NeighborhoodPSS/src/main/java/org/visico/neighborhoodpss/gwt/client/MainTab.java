package org.visico.neighborhoodpss.gwt.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class MainTab extends TabLayoutPanel 
{
	ProjectMediator projectMed;
	
	ArrayList<ScenarioPanel> panels;
	HierarchyPanel hierarchyPanel; 


	
	
	public MainTab(ProjectMediator projectMed) 
	{
		super(5, Unit.EM);
		
		this.projectMed = projectMed;
		
		panels = new ArrayList<ScenarioPanel>();
		
		// work around for bug in google maps - if not implemented tiles of the map are gray
		this.addSelectionHandler(new SelectionHandler<Integer>() 
		{
			public void onSelection(final SelectionEvent<Integer> event) 
			{
				Scheduler s = Scheduler.get();
				if(event.getSelectedItem()!=0) // this is the scenario selection tab
				{
					s.scheduleDeferred(new ScheduledCommand() 
					{
				          public void execute() 
				          {
				        	  panels.get(event.getSelectedItem() - 1).getMap().getMap().checkResizeAndCenter();
				          }
				     });	
				}
			}
		});
		
		draw();
	}

	public void draw()
	{
		hierarchyPanel = new HierarchyPanel(projectMed);
		projectMed.registerHierachyPanel(hierarchyPanel);
		hierarchyPanel.drawLogin();
		add(hierarchyPanel, "Scenarios");
	}
	
	public void clearScenarioTabs() {
		for (int i=1; i<this.getWidgetCount(); i++)
		{
			this.remove(i);
		}
	}
	
	public void addScenarioPanel(ScenarioPanel scenarioPanel)  {
		panels.add(scenarioPanel);
		add(scenarioPanel, scenarioPanel.getTitle());
	}
}
