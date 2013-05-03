package org.visico.neighborhoodpss.client;

import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ScenarioComposite extends Composite implements ClickHandler
{
	public ScenarioComposite(ScenarioDTO s)
	{
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
			MainTab.getInstance().addScenario(scenario);
		}
		
		if (event.getSource() == branch)
		{
			this.scenario.createChild();
			HierarchyPanel.getInstance().draw();
		}
	}

	private Button view;
	private Button branch;
	//private Button delete;
	//private Button info;
	
	private ScenarioDTO scenario;
}
