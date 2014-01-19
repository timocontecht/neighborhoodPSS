package org.visico.neighborhoodpss.gwt.client;


import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ScenarioDialog extends DialogBox implements ClickHandler
{
	private ProjectMediator med;
	
	public ScenarioDialog(ProjectMediator med)
	{
		
		this.med = med;
		
		VerticalPanel panel = new VerticalPanel();
		panel.add (new Label("Please enter the name of the new scenario"));
		
		text = new TextBox();
		panel.add(text);
		
		Button cancel = new Button("OK");
		cancel.addClickHandler(this);
		panel.add(cancel);
		
		add(panel);
		
	}
	
	public String text()
	{
		return text.getText();
	}
	
	private TextBox text;

	@Override
	public void onClick(ClickEvent event) 
	{
		ScenarioDialog.this.hide(true);
		med.addParentScenario(text());
	}
	
	
	
}
