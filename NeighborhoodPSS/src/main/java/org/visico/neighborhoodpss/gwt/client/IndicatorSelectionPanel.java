package org.visico.neighborhoodpss.gwt.client;

import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class IndicatorSelectionPanel extends Composite {
	ProjectMediator med;
	VerticalPanel mainPanel;
	
	public IndicatorSelectionPanel(ProjectMediator med)
	{
		this.med = med;
		draw();
	}
	
	private void draw()
	{
		mainPanel = new VerticalPanel();
		this.initWidget(mainPanel);
	}
	
	public void addIndicatorWidget(IndicatorWidget widget)
	{
		mainPanel.add(widget);
	}

	public void clear() {
		mainPanel.clear();
		
	}
}
