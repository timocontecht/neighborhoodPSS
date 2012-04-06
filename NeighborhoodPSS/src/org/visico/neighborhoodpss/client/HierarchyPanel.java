package org.visico.neighborhoodpss.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HierarchyPanel extends ScrollPanel implements ClickHandler
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
		parentScenarios = new ArrayList<Scenario>();
		draw();
	}
	
	public void draw()
	{
		try 
		{
			
			this.clear();
			
			VerticalPanel p = new VerticalPanel();
			
			for (int i=0; i<parentScenarios.size(); i++)
			{
				ScenarioTree st = new ScenarioTree(parentScenarios.get(i));
				p.add(st);
			}
			
			addRoot = new Button("Add Root Scenario");
		    addRoot.addClickHandler(this);
		    p.add(addRoot);
		    
		    this.add(p);
		    
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	    
	}
	
	ArrayList<Scenario> parentScenarios;
	Button addRoot;

	@Override
	public void onClick(ClickEvent event) 
	{
		
		ScenarioDialog dlg = new ScenarioDialog(this);
		dlg.show();
		
		
	} 
	
	public void addParentScenario(Scenario s)
	{
		parentScenarios.add(s);
		draw();
	}
	
}
