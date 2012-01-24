package org.visico.neighborhoodpss.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.TabPanel;

public class DataPanel extends TabPanel 
{
	static private DataPanel instance = null;
	
	static public  DataPanel getInstance()
	{
		if (instance == null)
			instance = new DataPanel();
		return instance;
	}

	private DataPanel()
	{
		this.setWidth("20px");
		add(BuildingTable.getInstance(), "Buildings");
	    add(IndicatorPanel.getInstance(), "Inidcators");

	    // Show the 'bar' tab initially.
	    selectTab(0);
	}
	
	
}
