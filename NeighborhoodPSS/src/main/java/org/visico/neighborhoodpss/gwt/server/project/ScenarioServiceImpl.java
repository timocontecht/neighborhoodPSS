package org.visico.neighborhoodpss.gwt.server.project;


import java.util.ArrayList;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.visico.neighborhoodpss.gwt.client.ScenarioService;
import org.visico.neighborhoodpss.gwt.server.project.db.BuildingDataType;
import org.visico.neighborhoodpss.gwt.server.project.db.HibernateUtil;
import org.visico.neighborhoodpss.gwt.server.project.db.Project;
import org.visico.neighborhoodpss.gwt.server.project.db.User;
import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;
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
		saveDataTypes(projectdto);
    	
		Session session = HibernateUtil.getSessionFactory().openSession();
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
			session.close();
			return projectdto;
    	}
    	catch (Exception e)
	    {
	    	//if (tx != null) tx.rollback();
	    	e.printStackTrace();
	    	session.close();
	    	return null;
	    }
	}
	
	 private void saveDataTypes(ProjectDTO projectdto) {
			Session session = HibernateUtil.getSessionFactory().openSession();
	    	Transaction tx = null;
		    
	    	try  
	    	{ 
			    for (BuildingDataTypeDTO bd_dto : projectdto.getBuildingDataTypes())  {
					BuildingDataType bd = new BuildingDataType(bd_dto);
				    try
					{
				    	// check whether constraint exist, if yes, update ids
				    	Query q = session.createQuery("from BuildingDataType dt where dt.name = :name");
			 		    q.setString("name", bd_dto.getName());
			 		    BuildingDataType dt = (BuildingDataType) q.uniqueResult();
				    	
			 		    if (dt != null)  {
			 		    	bd.setId(dt.getId());
			 		    	bd.update_dtoIds();
			 		    	projectdto.synchronizeDataTypeIds();
			 		    }
			 		    else  {
			 		    	tx = session.beginTransaction();
							session.saveOrUpdate(bd);
							bd.update_dtoIds();
							projectdto.synchronizeDataTypeIds();
							 tx.commit();
			 		    }
						
					}
					catch (NonUniqueObjectException e) 
					{
						session.merge(bd);
					}
				    catch (ConstraintViolationException e)
				    {
				    	e.printStackTrace();
				    	
				    }
	    		}
			   
			    session.flush();
	    	}
	    	catch (Exception e)
		    {
		    	if (tx != null) tx.rollback();
		    	e.printStackTrace();
		    	
		    }
	    	session.close();
		
	}

	@Override
     public ArrayList<ProjectDTO> getProjects(UserDTO user) {
             
		 	Session session = HibernateUtil.getSessionFactory().openSession();
	    	Transaction tx = null;
		    try  
		    {
		    	tx = session.beginTransaction();
		    
			    Query q = session.createQuery("from Project p join fetch p.users u where u.name = :name");
			    q.setString("name", user.getName());
			    ArrayList<Project> p = (ArrayList<Project>) q.list();
			    ArrayList<ProjectDTO> projects = Project.getDTOList(p); 
			    tx.commit();
			    session.close();
			    return projects;
		    }
		    catch (Exception e)
		    {
		    	if (tx != null) tx.rollback();
		    	session.close();
		    	return null;
		    }
		   
		   
     }

     @Override
     public UserDTO login(String user, String password) 
     {
    	Session session = HibernateUtil.getSessionFactory().openSession();
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
 		    	session.close();
 		    	return null;
 		    }
     	}
     	catch (Exception e)
 	    {
 	    	if (tx != null) tx.rollback();
 	    	session.close();
 	    	return null;
 	    }
     }
}
