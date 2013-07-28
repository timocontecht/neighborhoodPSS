package org.visico.neighborhoodpss.gwt.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.gwt.shared.dto.ScenarioDTO;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class MainTab extends TabLayoutPanel 
{
	static private MainTab instance = null;
	
	static public MainTab getInstance()
	{
		if (instance == null)
			instance = new MainTab();
		
		return instance;
	}
	
	private MainTab() 
	{
		super(5, Unit.EM);
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
		HierarchyPanel panel = HierarchyPanel.getInstance();
		add(panel, "Scenarios");
		
		
		
	}
	
	public void addScenario(ScenarioDTO scenario)
	{
		ScenarioPanel dock = new ScenarioPanel(scenario);
		dock.setTitle(scenario.getName() + " " + scenario.label());
		panels.add(dock);
		add(dock, dock.getTitle());
	}
	
	ArrayList<ScenarioPanel> panels;
	
}
