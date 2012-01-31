package org.visico.neighborhoodpss.client;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

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
		scenarios = new Tree();
		
		TreeItem root = new TreeItem(new ScenarioComposite());
	    root.addItem(new ScenarioComposite());
	    root.addItem(new ScenarioComposite());
	    root.addItem(new ScenarioComposite());

	    // Add a CheckBox to the tree
	    TreeItem item = new TreeItem(new ScenarioComposite());
	    root.addItem(item);
	    
	    scenarios.addItem(root);
	    
	    add(scenarios);
	}
	
	Tree scenarios;
}
