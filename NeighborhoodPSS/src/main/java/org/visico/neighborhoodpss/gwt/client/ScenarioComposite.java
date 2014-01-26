package org.visico.neighborhoodpss.gwt.client;

import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ScenarioComposite extends Composite implements ClickHandler
{
	ProjectMediator projectMed;
	
	public ScenarioComposite(ScenarioDTO s, ProjectMediator projectMed)
	{
		this.projectMed = projectMed;
		
		scenario = s;
		
		VerticalPanel vp = new VerticalPanel();
		HorizontalPanel hp = new HorizontalPanel();
		
		vp.add(new Label(s.label()));
		vp.add(new Label(s.getName()));
		vp.add(hp);
		
		view = new Button("View");
		view.addClickHandler(this);
		branch = new Button("Branch");
		branch.addClickHandler(this);
		//delete = new Button("Delete");
		//info = new Button("Info");
		hp.add(view);
		hp.add(branch);
		//hp.add(delete);
		//hp.add(info);
		
		initWidget(vp);
	}

	
	public void onClick(ClickEvent event) 
	{
		if (event.getSource() == view)
		{
			projectMed.addScenarioComp(scenario);
			
		}
		
		if (event.getSource() == branch)
		{
			this.scenario.createChild();
			projectMed.drawScenarioHierarchy();
		}
	}

	private Button view;
	private Button branch;
	//private Button delete;
	//private Button info;
	
	private ScenarioDTO scenario;
}
