package org.visico.neighborhoodpss.gwt.server.project;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.JerseyClient;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.visico.neighborhoodpss.gwt.client.ScenarioService;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;

import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ScenarioServiceImpl extends RemoteServiceServlet implements
		ScenarioService 
{

	@Override
	public ProjectDTO saveProject(ProjectDTO project)
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8443").
				path("pssprojectrest/webapi/project/saveProject");
		Invocation invocation = target.request().header("project", project).
				buildPost(Entity.entity(project, MediaType.APPLICATION_JSON_TYPE));
		return invocation.invoke(ProjectDTO.class);
	}
	
	@Override
	public ArrayList<ProjectDTO> getProjects(UserDTO user) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8443").
				path("pssprojectrest/webapi/project/list");
		Invocation invocation = target.request().buildGet();
		ArrayList<ProjectDTO> projectList = invocation.invoke(new GenericType<ArrayList<ProjectDTO>>() {});
		if (projectList != null)
			return projectList;
		else
			return null;
		
	}

	@Override
	public UserDTO login(String user, String password) 
	{
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8443").
				path("pssprojectrest/webapi/project/login");
		Invocation invocation = target.queryParam("user", user).
				queryParam("pass", password).request().buildGet();
		UserDTO u =  invocation.invoke(UserDTO.class);
	    if (u != null && u.getPassword().equals(password) )
	    	return u;
	    else
	    	return null;
	}
}
