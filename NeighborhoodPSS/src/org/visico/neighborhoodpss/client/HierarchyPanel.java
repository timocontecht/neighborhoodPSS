package org.visico.neighborhoodpss.client;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
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
	
	public void draw()
	{
		try 
		{
			
			this.clear();
			
			
			VerticalPanel p = new VerticalPanel();
			ScrollPanel s = new ScrollPanel();
			s.setSize("50em", "50em");
			p.add(s);
			VerticalPanel scenarioPanel = new VerticalPanel();
			
			Iterator<ScenarioDTO> it = parentScenarios.iterator();
			while(it.hasNext())
			{
				ScenarioTree st = new ScenarioTree(it.next());
				scenarioPanel.add(st);
			}
			
			s.add(scenarioPanel);
			
			addRoot = new Button("Add Root Scenario");
		    addRoot.addClickHandler(this);
		    p.add(addRoot);
		    
		    this.addWest(p, 50);
		    
		    saveSession = new Button("Save Session");
		    saveSession.setSize("50em", "10em");
		    saveSession.addClickHandler(this);
		    this.addSouth(saveSession, 20);
		    
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	    
	}
	
	Set<ScenarioDTO> parentScenarios;
	Button addRoot;
	Button saveSession;

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
			
			AsyncCallback<Set<ScenarioDTO>> callback = new AsyncCallback<Set<ScenarioDTO>>()
			{

				@Override
				public void onFailure(Throwable caught) 
				{
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(Set<ScenarioDTO> result) 
				{
					Window.alert("Saved scenarios!");
					
					// need to overwrite scnearios to get id from server for database persistence
					parentScenarios = result;
				}
			};
			
			service.saveScenarios(parentScenarios, callback);
			
		}
	} 
	
	public void addParentScenario(ScenarioDTO s)
	{
		parentScenarios.add(s);
		draw();
	}
	
}
