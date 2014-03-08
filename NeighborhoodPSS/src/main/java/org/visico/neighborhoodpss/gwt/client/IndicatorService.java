package org.visico.neighborhoodpss.gwt.client;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.IndicatorDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("indicator")
public interface IndicatorService extends RemoteService 
{
	ArrayList<IndicatorDTO> getIndicatorList(ProjectDTO project);
	String activateIndicator (ProjectDTO project, String indicatorName);
	String deactivateIndicator (ProjectDTO project, String indicatorName);
	Set<String> buildingDataTypes (ProjectDTO project);
	String caluclateIndicator (ScenarioDTO scenario, IndicatorDTO indicator);
}
