package org.visico.neighborhoodpss.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.visico.neighborhoodpss.client.ScenarioService;
import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ScenarioServiceImpl extends RemoteServiceServlet implements
		ScenarioService 
{

	

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	} */

	@Override
	public Set<ScenarioDTO> saveScenarios(Set<ScenarioDTO> scenarios)
			throws IllegalArgumentException 
	{
		
		createAndSaveScenarioObjects(scenarios);
		return scenarios;
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
			session.saveOrUpdate(s);
			s.getDto_object().setId(s.getId());
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
			//TODO: b.getDto_object().setId(b.getId());
		}
		
		session.getTransaction().commit();
	}
}
