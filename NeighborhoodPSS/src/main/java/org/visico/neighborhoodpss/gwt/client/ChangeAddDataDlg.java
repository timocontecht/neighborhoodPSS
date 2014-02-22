package org.visico.neighborhoodpss.gwt.client;

import java.util.List;

import org.visico.neighborhoodpss.domain.project.BuildingDataDTO;
import org.visico.neighborhoodpss.gwt.shared.patterns.ScenarioEditMediator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChangeAddDataDlg extends DialogBox implements ClickHandler{
	
	ScenarioEditMediator sceMed;
	Grid buildingDataGrid = new Grid(1,2);
	private final Button changeDataBtn = new Button("Change Data");
	private final Button cancelBtn = new Button("Cancel");
	
	public ChangeAddDataDlg(ScenarioEditMediator sceMed)
	{
		this.sceMed = sceMed;
		this.setText("Edit additional Data");
		draw();
	}
	
	private void draw()
	{
		VerticalPanel vp = new VerticalPanel();
		this.add(vp);
		
		VerticalPanel buildingPanel = new VerticalPanel();
		vp.add(buildingPanel);
		buildingPanel.setStyleName("boundedVPanel");
		buildingPanel.setWidth("25em");
		
		Label buildingCaption = new Label("Additional Building Data:");
		buildingCaption.setStyleName("captionLabel");
		buildingPanel.add(buildingCaption);
		
		buildingPanel.add(buildingDataGrid);
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		changeDataBtn.addClickHandler(this);
		buttonPanel.add(changeDataBtn);
		cancelBtn.addClickHandler(this);
		buttonPanel.add(cancelBtn);
		vp.add(buttonPanel);
	}

	public Grid getBuildingDataGrid() {
		return buildingDataGrid;
	}

	public void setBuildingDataGrid(Grid buildingDataGrid) {
		this.buildingDataGrid = buildingDataGrid;
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() == changeDataBtn)
		{
			this.hide();
			sceMed.changeAdditionalBuildingData();
		}
		else if (event.getSource() == cancelBtn)
		{
			this.hide();
		}
		
	}

	
}
