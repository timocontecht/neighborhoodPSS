package org.visico.neighborhoodpss.gwt.shared.patterns;

import java.util.ArrayList;
import java.util.HashMap;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.gwt.client.IndicatorSelectionPanel;
import org.visico.neighborhoodpss.gwt.client.IndicatorService;
import org.visico.neighborhoodpss.gwt.client.IndicatorServiceAsync;
import org.visico.neighborhoodpss.gwt.client.IndicatorWidget;
import org.visico.neighborhoodpss.gwt.client.ScenarioService;
import org.visico.neighborhoodpss.gwt.client.ScenarioServiceAsync;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;

import com.google.common.util.concurrent.Service;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;


public class IndicatorMediator {
	
	
	IndicatorSelectionPanel indicatorSelectionPanel;
	HashMap<String, IndicatorWidget> indicatorWidgets = new HashMap<String, IndicatorWidget>();

	private ProjectDTO project;
	
	public IndicatorMediator(ProjectDTO project)
	{
		this.project = project;
	}
	
	
	public ProjectDTO getProject() {
		return project;
	}


	public void registerIndicatorSelectionPanel(IndicatorSelectionPanel indicatorSelectionPanel)
	{
		this.indicatorSelectionPanel = indicatorSelectionPanel;
		addExistingIndicators();
	}

	private void addExistingIndicators()
	{
		IndicatorServiceAsync service = GWT.create(IndicatorService.class);
		
		AsyncCallback<ArrayList<IndicatorDTO>> callback = new AsyncCallback<ArrayList<IndicatorDTO>>()
		{

			@Override
			public void onFailure(Throwable caught) 
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(ArrayList<IndicatorDTO> result) 
			{
				for (IndicatorDTO ind : result)
				{
					IndicatorWidget wid = new IndicatorWidget();
					wid.setAuthor(ind.getAuthor());
					wid.setDescription(ind.getDescription());
					wid.setName(ind.getName());
					wid.setVersion(ind.getVersion());
					indicatorWidgets.put(ind.getName(), wid);
					indicatorSelectionPanel.addIndicatorWidget(wid);
				}
			}
		};
		
		service.getIndicatorList(getProject(), callback);
	}
	
}
