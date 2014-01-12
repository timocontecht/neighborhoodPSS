package org.visico.neighborhoodpss.gwt.server.project;


import java.util.ArrayList;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.gwt.client.IndicatorService;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;








import org.visico.neighborhoodpss.plugin.IndicatorManager;


import org.visico.neighborhoodpss.plugin.IndicatorPluginInfo;

//import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class IndicatorServiceImpl extends RemoteServiceServlet implements
		IndicatorService 
{
	IndicatorManager manager = null;
	
	static String pathToIndicators = "res/plugins"; 

	@Override
	public ArrayList<IndicatorDTO> getIndicatorList(ProjectDTO project) {
		if (manager == null)
			manager = new IndicatorManager(project);
		
		ArrayList<IndicatorDTO> indicators = new ArrayList<IndicatorDTO>();
		
		for (IndicatorPluginInfo indInfo : manager.availableIndicators(pathToIndicators))
		{
			IndicatorDTO indicatordto = new IndicatorDTO(indInfo.getName(),
					indInfo.getDescription(), indInfo.getAuthor(), indInfo.getVersion());
			indicators.add(indicatordto);	
		}
		
		return indicators;
	}
	
	
}
