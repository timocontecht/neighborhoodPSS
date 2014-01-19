package org.visico.neighborhoodpss.gwt.client;

import org.visico.neighborhoodpss.gwt.shared.patterns.ProjectMediator;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class IndicatorWidget extends Composite implements ClickHandler {

	Grid mainPanel = new Grid(1,2);
	
	VerticalPanel labelPanel = new VerticalPanel();;
	Label name_lbl;
	Label author_lbl;
	Label version_lbl;
	Label description_lbl;
	
	Button activated_btn;
	
	ProjectMediator med;

	public IndicatorWidget(ProjectMediator med)
	{
		this.med = med;
		
		name_lbl = new Label();
		author_lbl = new Label();
		description_lbl = new Label();
		version_lbl = new Label();
		activated_btn = new Button();
		setActivated(false);
		activated_btn.addClickHandler(this);
		
		labelPanel.setStyleName("indicatorInfoPanel");
		labelPanel.add(name_lbl);
		labelPanel.add(author_lbl);
		labelPanel.add(version_lbl);
		labelPanel.add(description_lbl);
		labelPanel.add(activated_btn);
		
		name_lbl.addStyleName("indicatorInfoTitle");
		author_lbl.addStyleName("indicatorInfoStage");
		version_lbl.addStyleName("indicatorInfoStage");
		description_lbl.addStyleName("indicatorInfoDescription");
		
		mainPanel.setWidget(0, 0, labelPanel);
		initWidget(mainPanel);
	}
	
	public void setName(String name) {
		name_lbl.setText(name);
	}

	public void setDescription(String description) {
		description_lbl.setText(description);
		
	}

	public void setAuthor(String author) {
		author_lbl.setText(author);
		
	}
	
	public void setVersion(String version) {
		version_lbl.setText(version);
	}

	public void setActivated(boolean activated)
	{
		if (activated == true)
		{
			activated_btn.setText("Deactivate");
		}
		else 
		{
			activated_btn.setText("Activate");
		}
	}
	
	@Override
	public void onClick(ClickEvent event) {
		if (activated_btn.getText().equals("Deactivate"))
			med.deregisterIndicator(name_lbl.getText());
		else
			med.registerIndicator(name_lbl.getText());
		
		med.addExistingIndicators();
	}

}
