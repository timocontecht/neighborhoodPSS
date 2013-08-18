package org.visico.neighborhoodpss.pssprojectrest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;

/**
 * Root resource (exposed at "myresource" path)
 */
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
    public ProjectDTO getProject(@PathParam("id") String id, 
    		@PathParam("user") String user, 
    		@PathParam("pass") String pass) {
        return new ProjectDTO();
    }
   
    @GET @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectList(@PathParam("user") String user, 
    		@PathParam("pass") String pass)
    {
    	ArrayList<ProjectDTO> projects = new ArrayList<ProjectDTO>();
    	final GenericEntity<ArrayList<ProjectDTO>> entity = new GenericEntity<ArrayList<ProjectDTO>>(projects) { };
    	return Response.ok(entity).build();
    }
    
}
