package org.visico.neighborhoodpss.pssprojectrest;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ProjectNameDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;

import org.visico.neighborhoodpss.pssprojectrest.db.HibernateUtil;
import org.visico.neighborhoodpss.pssprojectrest.db.Project;
import org.visico.neighborhoodpss.pssprojectrest.db.User;


@Path("project")
public class ProjectRestService {

	/**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public ProjectDTO getProject(@Context SecurityContext sc, @QueryParam("id") Integer id) {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Query q = session.createQuery("from Project p where id = :id");
	    q.setInteger("id", id);
	    Project p = (Project) q.uniqueResult();
	    if (p != null  )
	    {
	    	ProjectDTO dto_object = p.getDto_object();
		    session.getTransaction().commit();
	    	return dto_object;
	    }
	    else
	    	return null;
    }
   
    @GET @Path("/loadProjects")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response getProjectList(@Context SecurityContext sc)
    {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Query q = session.createQuery("from Project p join fetch p.users u where u.name = :name");
	    String name = sc.getUserPrincipal().getName();
	    q.setString("name", name);
	    ArrayList<Project> p = (ArrayList<Project>) q.list();
	    ArrayList<ProjectDTO> projects = Project.getDTOList(p); 
	    final GenericEntity<ArrayList<ProjectDTO>> entity = new GenericEntity<ArrayList<ProjectDTO>>(projects) { };
	    session.getTransaction().commit();
	    return Response.ok(entity).build();
    }
    
    @GET @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response getProjectNames(@Context SecurityContext sc)
    {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Query q = session.createQuery("from Project p join fetch p.users u where u.name = :name");
	   
	    String name = sc.getUserPrincipal().getName();
	    q.setString("name", name);
	    ArrayList<Project> p = (ArrayList<Project>) q.list();
	    
	    // set up the hash map and return it
	    ArrayList<ProjectNameDTO> projectNames = new ArrayList<ProjectNameDTO>();
	    for (Project project : p)
	    {
	    	ProjectNameDTO n = new ProjectNameDTO();
	    	n.setDb_id(project.getId());
	    	n.setName(project.getName());
	    	projectNames.add(n);
	    }
	    
	    final GenericEntity<ArrayList<ProjectNameDTO>> entity = 
	    		new GenericEntity<ArrayList<ProjectNameDTO>>(projectNames) { };
	    session.getTransaction().commit();
    	return Response.ok(entity).build();
    }
    
    @POST @Path("/saveProject")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public ProjectDTO saveProject(@Context SecurityContext sc, ProjectDTO projectdto)
    {
    	Project project = new Project(projectdto);
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    
	    try
		{
			session.saveOrUpdate(project);
			project.update_dtoIds();
		}
		catch (NonUniqueObjectException e) 
		{
			session.merge(project);
		}
		
		session.getTransaction().commit();
		return projectdto;
    }
    
    @GET @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public UserDTO login(@Context SecurityContext sc, @QueryParam("user") String user, 
    		@QueryParam("pass") String pass)
    {
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	    session.beginTransaction();
	    Query q = session.createQuery("from User u where u.name = :name");
	    q.setString("name", user);
	    User u = (User) q.uniqueResult();
	    
	    if (u != null && u.getPassword().equals(pass) )
	    {
	    	UserDTO dto_object = u.getDto_object();
	    	session.getTransaction().commit();
	    	return dto_object;
	    }
	    else
	    	return null;
    }
}
