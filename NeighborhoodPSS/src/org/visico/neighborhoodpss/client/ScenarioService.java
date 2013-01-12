package org.visico.neighborhoodpss.client;


import java.util.Set;

import org.visico.neighborhoodpss.shared.ProjectDTO;
import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("scenario")
public interface ScenarioService extends RemoteService 
{
	Set<ScenarioDTO> saveScenarios(Set<ScenarioDTO> scenarios) throws IllegalArgumentException;

	ProjectDTO saveProject(ProjectDTO project);
	
}
