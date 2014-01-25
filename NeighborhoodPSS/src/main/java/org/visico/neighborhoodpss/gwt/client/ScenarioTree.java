package org.visico.neighborhoodpss.gwt.client;

import java.util.Iterator;

import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;



public class ScenarioTree extends Tree 
{
	ProjectMediator projectMed;
	
	ScenarioTree(ScenarioDTO scenario, ProjectMediator projectMed) throws Exception
	{
		this.projectMed = projectMed;
		
		if (scenario.hasParent() == true)
			throw new Exception("not a root scenario!");
		
		TreeItem root = new TreeItem(new ScenarioComposite(scenario, projectMed));
		addChildren(root, scenario);
		this.addItem(root);
		root.setState(true);
	}
	
	private void addChildren(TreeItem rootTreeItem, ScenarioDTO rootScenario)
	{
		Iterator<ScenarioDTO> it = rootScenario.getChildren().iterator();
		while (it.hasNext())
		{
			ScenarioDTO childScenario = it.next();
			ScenarioComposite scenarioComp = new ScenarioComposite(childScenario, projectMed);
			TreeItem childItem = new TreeItem(scenarioComp);
			rootTreeItem.addItem(childItem);
			addChildren(childItem, childScenario);
			childItem.setState(true);
		}
	}
	
}
