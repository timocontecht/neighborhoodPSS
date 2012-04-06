package org.visico.neighborhoodpss.client;

import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
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
		parentScenarios = new ArrayList<Scenario>();
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
			
			for (int i=0; i<parentScenarios.size(); i++)
			{
				ScenarioTree st = new ScenarioTree(parentScenarios.get(i));
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
	
	ArrayList<Scenario> parentScenarios;
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
					Window.alert(result);
					
				}
			};
			
			service.saveScenarios(parentScenarios, callback);
			
		}
	} 
	
	public void addParentScenario(Scenario s)
	{
		parentScenarios.add(s);
		draw();
	}
	
}
