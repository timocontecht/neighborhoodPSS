package org.visico.neighborhoodpss.client;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.shared.dto.ProjectDTO;
import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;
import org.visico.neighborhoodpss.shared.dto.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
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
	ArrayList<ProjectDTO> getProjects(UserDTO user);
	UserDTO login(String user, String password);
	
}
