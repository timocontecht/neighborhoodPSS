package org.visico.neighborhoodpss.gwt.client;

import org.visico.neighborhoodpss.gwt.shared.patterns.IndicatorMediator;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;


public class IndicatorSelectionPanel extends Composite {
	IndicatorMediator med;
	VerticalPanel mainPanel;
	
	public IndicatorSelectionPanel(IndicatorMediator med)
	{
		this.med = med;
		draw();
	}
	
	private void draw()
	{
		mainPanel = new VerticalPanel();
		
		mainPanel.add(new Label("here the indicators available"));
		
		this.initWidget(mainPanel);
	}
	
	public void addIndicatorWidget(IndicatorWidget widget)
	{
		mainPanel.add(widget);
	}
}
