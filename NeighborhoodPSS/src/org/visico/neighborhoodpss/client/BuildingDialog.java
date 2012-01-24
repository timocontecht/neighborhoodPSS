package org.visico.neighborhoodpss.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class BuildingDialog extends DialogBox implements ClickHandler
{
	public BuildingDialog(Building b)
	{
		VerticalPanel panel = new VerticalPanel();
		panel.add (new Label("Select the Industry Type:"));
		
		type = new ListBox();
		for (int i=0; i<Building.industryTypes.size(); i++)
		{
			type.addItem(Building.industryTypes.get(i));
		}
		panel.add(type);
		
		Button cancel = new Button("OK");
		cancel.addClickHandler(this);
		panel.add(cancel);
		
		add(panel);
		
		building = b;
	}
	
	public String type()
	{
		return type.getItemText(type.getSelectedIndex());
	}
	
	private ListBox type;

	@Override
	public void onClick(ClickEvent event) 
	{
		BuildingDialog.this.hide(true);
		building.setType(type());
		Building.buildings.add(building);
		BuildingTable.getInstance().draw();
	}
	
	private Building building = null;
}
