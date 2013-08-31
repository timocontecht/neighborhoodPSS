package org.visico.neighborhoodpss.pssprojectrest;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
import org.visico.neighborhoodpss.pssprojectrest.db.HibernateUtil;
import org.visico.neighborhoodpss.pssprojectrest.db.Project;


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
    public ProjectDTO getProject(@Context SecurityContext sc, @PathParam("id") String id) {
        return new ProjectDTO();
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
    	return Response.ok(entity).build();
    }
    
    @POST @Path("/saveProject")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public void saveProject(@Context SecurityContext sc, ProjectDTO projectdto)
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
    }
    
}
