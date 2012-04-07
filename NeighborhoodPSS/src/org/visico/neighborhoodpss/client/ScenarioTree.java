package org.visico.neighborhoodpss.client;

import java.util.Iterator;

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
		Iterator<Scenario> it = rootScenario.getChildren().iterator();
		while (it.hasNext())
		{
			Scenario childScenario = it.next();
			TreeItem childItem = new TreeItem(new ScenarioComposite(childScenario));
			rootTreeItem.addItem(childItem);
			addChildren(childItem, childScenario);
			childItem.setState(true);
		}
	}
	
}
