package org.visico.neighborhoodpss.deterioationindicator;

import java.util.ArrayList;
import java.util.List;

import org.visico.neighborhoodpss.domain.project.BuildingDTO;
import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;
import org.visico.neighborhoodpss.domain.project.GeoPointDTO;

import delaunay_triangulation.Point_dt;


public class BuildingNode extends Point_dt
{
	BuildingDTO building;
	
	int condition = 1;
	
	public BuildingNode(BuildingDTO building)  {
		this.building = building;
		calculateCenterPoint();
		
		for (BuildingDataTypeDTO datat : building.getData().keySet())  {
			if (datat.getName().equals("Condition"))
				condition = Integer.parseInt(building.getData().get(datat).getValue());
		}
	}

	private void calculateCenterPoint() {
		double Cx = 0.0, Cy = 0.0, A = 0.0;
		
		List<GeoPointDTO> points = new ArrayList<GeoPointDTO>(building.getPoints());
		
		// close the polygon for the formula to work
		points.add(points.get(0));
		
		for (int i=0; i < points.size() - 1; i++)  {
			GeoPointDTO pti = points.get(i);
			GeoPointDTO pti1 = points.get(i+1);
			
			Cx = Cx + (pti.getLongitude() + pti1.getLongitude()) *
					(pti.getLongitude() * pti1.getLatitude() - 
					 pti1.getLongitude() * pti.getLatitude());
			Cy = Cy + (pti.getLatitude() + pti1.getLatitude()) *
					(pti.getLongitude() * pti1.getLatitude() - 
							 pti1.getLongitude() * pti.getLatitude());
			A = A + (pti.getLongitude() * pti1.getLatitude() - 
					 pti1.getLongitude() * pti.getLatitude());
		}
		
		A = A * 0.5;
		setX(Cx / (6 * A));
		setY(Cy / (6 * A));
		
	}

	public BuildingDTO getBuilding() {
		return building;
	}

	public void setBuilding(BuildingDTO building) {
		this.building = building;
	}

	public int getCondition() {
		return condition;
	}

	public void reduceCondition(int i) {
		if (condition - i >= 1)
			condition = condition - i;
	}

	@Override
	public int hashCode() {
		int hashCode = 31 * (building !=null ? building.hashCode() : 0);
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
	        return false;
     	if (obj == this)
            return true;
        if (!(obj instanceof BuildingNode))
            return false;
        
        BuildingNode node = (BuildingNode) obj;
        
        if ( (node.x() - this.x()) < 10e-6 
        		&& (node.y() - this.y()) < 10e-6 )
        	return true;
        else
        	return false;
	}
	
	
}
