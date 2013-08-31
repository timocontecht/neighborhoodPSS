package org.visico.neighborhoodpss.gwt.server.project;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

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
		
	}
	
	@Override
	public ArrayList<ProjectDTO> getProjects(UserDTO user) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Query q = session.createQuery("from Project p join fetch p.users u where u.id = :id");
	    q.setInteger("id", user.getId());
	    ArrayList<Project> p = (ArrayList<Project>) q.list();
	    ArrayList<ProjectDTO> dtos = Project.getDTOList(p); 
	    return dtos;
		
	}

	@Override
	public UserDTO login(String user, String password) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Query q = session.createQuery("from User u where u.name = :name");
	    q.setString("name", user);
	    User u = (User) q.uniqueResult();
	    if (u != null && u.getPassword().equals(password) )
	    	return u.getDto_object();
	    else
	    	return null;
	}
}
