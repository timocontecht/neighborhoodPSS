package org.visico.neighborhoodpss.client;


import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ScenarioDialog extends DialogBox implements ClickHandler
{
	public ScenarioDialog(HierarchyPanel h)
	{
		
		hierarchyPanel = h;
		
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
		ScenarioDTO scenario = new ScenarioDTO(text());
		ScenarioDialog.this.hide(true);
		hierarchyPanel.addParentScenario(scenario);
	}
	
	
	private HierarchyPanel hierarchyPanel;
}
