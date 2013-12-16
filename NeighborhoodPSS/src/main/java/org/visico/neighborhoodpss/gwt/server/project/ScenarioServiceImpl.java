package org.visico.neighborhoodpss.gwt.server.project;


import java.util.ArrayList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;









import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.visico.neighborhoodpss.gwt.client.ScenarioService;
import org.visico.neighborhoodpss.pssprojectrest.db.HibernateUtil;
import org.visico.neighborhoodpss.pssprojectrest.db.Project;
import org.visico.neighborhoodpss.pssprojectrest.db.User;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;








//import com.google.gwt.dev.util.collect.HashSet;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ScenarioServiceImpl extends RemoteServiceServlet implements
		ScenarioService 
{
	
	@Override
	public ProjectDTO saveProject(ProjectDTO projectdto)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	Transaction tx = null;
	    
    	try  
    	{
	    	Project project = new Project(projectdto);
		    tx = session.beginTransaction();
		    
		    try
			{
				session.saveOrUpdate(project);
				project.update_dtoIds();
			}
			catch (NonUniqueObjectException e) 
			{
				session.merge(project);
			}
		
			tx.commit();
			return projectdto;
    	}
    	catch (Exception e)
	    {
	    	if (tx != null) tx.rollback();
	    	return null;
	    }
	}
	
	 @Override
     public ArrayList<ProjectDTO> getProjects(UserDTO user) {
             
		 	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    	Transaction tx = null;
		    try  
		    {
		    	tx = session.beginTransaction();
		    
			    Query q = session.createQuery("from Project p join fetch p.users u where u.name = :name");
			    q.setString("name", user.getName());
			    ArrayList<Project> p = (ArrayList<Project>) q.list();
			    ArrayList<ProjectDTO> projects = Project.getDTOList(p); 
			    tx.commit();
			    return projects;
		    }
		    catch (Exception e)
		    {
		    	if (tx != null) tx.rollback();
		    	return null;
		    }
		   
     }

     @Override
     public UserDTO login(String user, String password) 
     {
    	 Session session = HibernateUtil.getSessionFactory().getCurrentSession();
     	Transaction tx = null;
 	    
     	try  
     	{
     		tx = session.beginTransaction();
     	
 		    Query q = session.createQuery("from User u where u.name = :name");
 		    q.setString("name", user);
 		    User u = (User) q.uniqueResult();
 		    
 		    if (u != null && u.getPassword().equals(password) )
 		    {
 		    	UserDTO dto_object = u.getDto_object();
 		    	tx.commit();
 		    	return dto_object;
 		    }
 		    else
 		    {
 		    	tx.commit();
 		    	return null;
 		    }
     	}
     	catch (Exception e)
 	    {
 	    	if (tx != null) tx.rollback();
 	    	return null;
 	    }
     }
}
