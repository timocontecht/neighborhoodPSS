package org.visico.neighborhoodpss.client;


import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;


public class BuildingTable extends ScrollPanel 
{
/*	private static BuildingTable instance = null;
	
	public static BuildingTable getInstance()
	{
		if (instance == null)
			instance = new BuildingTable();
		
		return instance;
		
	}
	*/
	public BuildingTable()
	{
		setSize("500px", "150px");
		
		buildingGrid = new Grid();
		buildingGrid.setStyleName("BuildingTable");
		this.add(buildingGrid);
		
		draw();
	}
	
	public void draw()
	{
		buildingGrid.clear();
		buildingGrid.resize(BuildingPolygon.buildings.size() + 1, 3);
		
		buildingGrid.getRowFormatter().addStyleName(0, "BuildingTableHeader");
		buildingGrid.setText(0, 0, "Number");
		buildingGrid.setText(0, 1, "Area");
		buildingGrid.setText(0, 2, "Industry");
		
		
		
		for (int i=0; i<BuildingPolygon.buildings.size(); i++)
		{
			buildingGrid.setText(i+1, 0, Integer.toString(i+1));
			buildingGrid.setText(i+1, 1, Double.toString(BuildingPolygon.buildings.get(i).getArea()) + "m2");
			//buildingGrid.setText(i+1, 2, BuildingPolygon.buildings.get(i).getType());
			buildingGrid.getRowFormatter().addStyleName(i+1, "BuildingTableRow");
		}
	}
	
	Grid buildingGrid = null;

}
