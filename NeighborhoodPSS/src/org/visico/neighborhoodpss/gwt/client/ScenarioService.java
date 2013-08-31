package org.visico.neighborhoodpss.gwt.client;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("scenario")
public interface ScenarioService extends RemoteService 
{
	ProjectDTO saveProject(ProjectDTO project);
	ArrayList<ProjectDTO> getProjects(UserDTO user);
	UserDTO login(String user, String password);
	
}
