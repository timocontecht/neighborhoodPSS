package org.visico.neighborhoodpss.gwt.client;


import java.util.Set;

import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;
import org.visico.neighborhoodpss.gwt.shared.patterns.ScenarioEditMediator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;


public class IndicatorTable extends ScrollPanel implements ClickHandler
{

	ProjectMediator indMed;
	ScenarioEditMediator scenarioMed;
	
	Grid buttonGrid = null;
	
	public IndicatorTable(ProjectMediator indMed, ScenarioEditMediator scenarioMed)
	{
		this.indMed = indMed;
		this.scenarioMed = scenarioMed;
		
		setSize("500px", "150px");
		
		buttonGrid = new Grid();
		//buttonGrid.setStyleName("BuildingTable");
		this.add(buttonGrid);
		draw();
	}
	
	public void draw()
	{
		buttonGrid.clear();
		buttonGrid.resize(1, 1);
		//buttonGrid.getRowFormatter().addStyleName(0, "BuildingTableHeader");
	}
	
	
	
	public Grid getButtonGrid() {
		return buttonGrid;
	}

	public void setButtonGrid(Grid buildingGrid) {
		this.buttonGrid = buildingGrid;
	}

	public void addIndicators(Set<String> indicators) {
		buttonGrid.clear();
		buttonGrid.resize(indicators.size(), 2);
		
		int i=0;
		for (String ind : indicators)  {
			buttonGrid.setText(i, 0, ind);
			Button calculate = new Button("Calculate");
			calculate.setTitle(ind);
			calculate.addClickHandler(this);
			buttonGrid.setWidget(i, 1, calculate);
			i++;
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		if (event.getSource() instanceof Button)  {
			Button btn = (Button) event.getSource();
			scenarioMed.calculateIndicator(btn.getTitle());
		}
	}



	

}
