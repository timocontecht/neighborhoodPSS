package org.visico.neighborhoodpss.client;

import org.visico.neighborhoodpss.shared.Scenario;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;



public class ScenarioTree extends Tree 
{
	ScenarioTree(Scenario scenario) throws Exception
	{
		if (scenario.hasParent() == true)
			throw new Exception("not a root scenario!");
		
		TreeItem root = new TreeItem(new ScenarioComposite(scenario));
		addChildren(root, scenario);
		this.addItem(root);
		root.setState(true);
	}
	
	private void addChildren(TreeItem rootTreeItem, Scenario rootScenario)
	{
		for (int i=0; i<rootScenario.children().size(); i++)
		{
			Scenario childScenario = rootScenario.children().get(i);
			TreeItem childItem = new TreeItem(new ScenarioComposite(childScenario));
			rootTreeItem.addItem(childItem);
			addChildren(childItem, childScenario);
			childItem.setState(true);
		}
	}
	
}
