package org.visico.neighborhoodpss.server;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.visico.neighborhoodpss.client.ScenarioService;
import org.visico.neighborhoodpss.shared.dto.ProjectDTO;
import org.visico.neighborhoodpss.shared.dto.ScenarioDTO;
import org.visico.neighborhoodpss.shared.dto.UserDTO;

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
	public Set<ScenarioDTO> saveScenarios(Set<ScenarioDTO> scenarios)
			throws IllegalArgumentException 
	{
		createAndSaveScenarioObjects(scenarios);
		return scenarios;
	}
	
	@Override
	public ProjectDTO saveProject(ProjectDTO project)
	{
		persistProject(new Project(project));
		return project;
	}
	
	private void createAndSaveScenarioObjects(Set<ScenarioDTO> scenarios_dto)
	{
		Set<Scenario> scenarios = createScenarioObjects(scenarios_dto);
		persistScenarios(scenarios);
	}
	
	private void persistScenarios(Set<Scenario> scenarios) 
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
		Iterator<Scenario> it = scenarios.iterator();
		while (it.hasNext())
		{
			Scenario s = it.next();
			
			try
			{
				session.saveOrUpdate(s);
				s.update_dtoIds();
			}
			catch (NonUniqueObjectException e) 
			{
				session.merge(s);
			}
		}
		
		session.getTransaction().commit();
	}

	/** function to convert all ScenarioDTO objects into normal Scenario objects for persistence
	 * 
	 * @param scenarios A Set of all root parents of the existing scenarios
	 * @return A Set of all scenarios with assigned parent ids for persistence
	 */
	private Set<Scenario> createScenarioObjects(Set<ScenarioDTO> scenarios)
	{
		HashSet<Scenario> objects = new HashSet<Scenario>();
		
		Iterator<ScenarioDTO> it = scenarios.iterator();
		while (it.hasNext())
		{
			ScenarioDTO parent = it.next();
			objects.addAll(Scenario.createFromParentDTO(parent));
		}
		return objects;
	}
	
	public void persistBuildings(Set<Building> buildings)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
		Iterator<Building> it = buildings.iterator();
		while (it.hasNext())
		{
			Building b = it.next();
			session.saveOrUpdate(b);
			
		}
		
		session.getTransaction().commit();
	}
	
	public void persistProject(Project p)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    try
		{
			session.saveOrUpdate(p);
			p.update_dtoIds();
		}
		catch (NonUniqueObjectException e) 
		{
			session.merge(p);
		}
		
		session.getTransaction().commit();
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
