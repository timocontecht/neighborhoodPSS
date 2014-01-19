package org.visico.neighborhoodpss.gwt.server.project;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.gwt.client.IndicatorService;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;
import org.visico.neighborhoodpss.plugin.IndicatorManager;
import org.visico.neighborhoodpss.plugin.domain.BuildingProperty;
import org.visico.neighborhoodpss.plugin.domain.Plugin;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class IndicatorServiceImpl extends RemoteServiceServlet implements
		IndicatorService 
{
	IndicatorManager manager = null;
	
	static String pathToIndicators = "res/plugins/"; 

	@Override
	public ArrayList<IndicatorDTO> getIndicatorList(ProjectDTO project) {
		if (manager == null)
			manager = new IndicatorManager(project);
		
		ArrayList<IndicatorDTO> indicators = new ArrayList<IndicatorDTO>();
		
		for (Plugin indInfo : manager.availableIndicators(pathToIndicators))
		{
			IndicatorDTO indicatordto = new IndicatorDTO(indInfo.getName(),
					indInfo.getDescription(), indInfo.getAuthor(), indInfo.getVersion());
			
			for (BuildingProperty bp : indInfo.getData().getBuildingProperty() )
			{
				BuildingDataTypeDTO dt = new BuildingDataTypeDTO();
				dt.setDefault_val(bp.getDefault());
				dt.setMaximum(Double.parseDouble(bp.getMax()));
				dt.setMinimum(Double.parseDouble(bp.getMin()));
				dt.setName(bp.getName());
				dt.setType(bp.getType());
				indicatordto.getBuildingDataTypes().add(dt);
			}
				
			if (manager.getPlugins().containsKey(indInfo.getClassName()))
				indicatordto.setActivated(true);
			indicators.add(indicatordto);	
		}
		
		return indicators;
	}

	@Override
	public String activateIndicator(ProjectDTO project, String indicatorName) {
		
		try {
			if (manager == null)
				manager = new IndicatorManager(project);
		
			manager.initIndicatorByIndicatorName(indicatorName, pathToIndicators, IndicatorServiceImpl.class.getClassLoader());
			return "Indicator " + indicatorName + " activated!";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Indicator " + indicatorName + " activation failed!";
		}
	}

	@Override
	public String deactivateIndicator(ProjectDTO project, String indicatorName)  {
		try {
			if (manager == null)
				manager = new IndicatorManager(project);
		
			manager.unregisterIndicatorByIndicatorName(indicatorName, pathToIndicators);
		
			return "Indicator " + indicatorName + " deactivated!";
		}
	    catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Indicator " + indicatorName + " activation failed!";
	    }
	}

	@Override
	public Set<String> buildingDataTypes(ProjectDTO project) {
		if (manager == null)
			manager = new IndicatorManager(project);
		
		return manager.getBuildingDataTypes();
	}
}
