package org.visico.neighborhoodpss.gwt.client;


import java.util.ArrayList;
import java.util.Set;

import org.visico.neighborhoodpss.gwt.shared.dto.ProjectDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.ScenarioDTO;
import org.visico.neighborhoodpss.gwt.shared.dto.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface ScenarioServiceAsync {
	void saveScenarios(Set<ScenarioDTO> parentScenarios, AsyncCallback< Set<ScenarioDTO> > callback)
			throws IllegalArgumentException;
	void saveProject(ProjectDTO project, AsyncCallback<ProjectDTO> callback)
			throws IllegalArgumentException;
	void getProjects(UserDTO user,
			AsyncCallback<ArrayList<ProjectDTO>> asyncCallback);
	void login(String user, String password, AsyncCallback<UserDTO> asyncCallback);
}
