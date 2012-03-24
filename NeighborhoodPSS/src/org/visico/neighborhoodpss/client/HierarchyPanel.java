package org.visico.neighborhoodpss.client;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class HierarchyPanel extends ScrollPanel
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
		draw();
	}
	
	public void draw()
	{
		try 
		{
			VerticalPanel p = new VerticalPanel();
			//TODO: here are a number of dummy scenarios
			Scenario one = new Scenario("Timo 1");
			Scenario two = one.createChild();
			Scenario three = one.createChild();
			Scenario four = one.createChild();
			Scenario fourchild = four.createChild();
			Scenario five = new Scenario("Timo 2");
			
			ScenarioTree oneTree;
			ScenarioTree fiveTree;
			
			oneTree = new ScenarioTree(one);
			fiveTree = new ScenarioTree(five);	    
		    p.add(oneTree);
		    p.add(fiveTree);
		    
		    this.add(p);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	    
	}
	
	
}
