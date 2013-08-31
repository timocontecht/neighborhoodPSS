package org.visico.neighborhoodpss.gwt.client;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ScenarioServiceAsync {
	void saveProject(ProjectDTO project, AsyncCallback<ProjectDTO> callback)
			throws IllegalArgumentException;
	void getProjects(UserDTO user,
			AsyncCallback<ArrayList<ProjectDTO>> asyncCallback);
	void login(String user, String password, AsyncCallback<UserDTO> asyncCallback);
}
