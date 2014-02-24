package org.visico.neighborhoodpss.pssprojectrest.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;


import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ProjectNameDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;
import org.visico.neighborhoodpss.pssprojectrest.ProjectRestService;

public class ProjectRestServiceTest extends JerseyTest{
	
		
	/* protected Application configure()
	 {
		 return new ResourceConfig(ProjectRestService.class);
	 }
	 
	 @Test
	 public void getProjectTest() {
		Invocation invocation = target("project").queryParam("id", "1").request().buildGet();
		ProjectDTO responseMsg = invocation.invoke(ProjectDTO.class);
        assertEquals(responseMsg.getId(), 1);
	 }
	 
	 @Test
	 public void saveProjectTest() {
		ProjectDTO project = new ProjectDTO();
		project.setName("Test");
		GenericEntity<ProjectDTO> entity = new GenericEntity<ProjectDTO>(project) { };
    	Response r = Response.ok(entity).build();
		Invocation invocation = target("project/saveProject").request().header("project", r).
				buildPost(Entity.entity(project, MediaType.APPLICATION_JSON_TYPE));
		invocation.invoke();
        
	 }
	 
	 @Test
	 public void loginTest() {
		Invocation invocation = target("project/login").queryParam("user", "User 1").
				queryParam("pass", "pass1").request().buildGet();
		 UserDTO user = invocation.invoke(UserDTO.class);
	     assertEquals(user.getName(), "User 1");
        
	 }
	 
	 @Test
	 public void addProjectTest() {
		 Invocation invocation = target("project/add").queryParam("name", "Test1")
				 .queryParam("latitude", "52.366612")
				 .queryParam("longitude", "6.644061")
				 .request().buildGet();
			Integer responseMsg = invocation.invoke(Integer.class);
	        assertNotNull(responseMsg);
	 }*/
	 
	 /*@Test
	 // this test does not work as it is not possible to inject a security context with the grizley server
	 public void getProjectListTest() {
		 Invocation invocation = target("project/list").request()
				 .header("user", "Timo")
				 .header("pass", "timopass")
				 .buildGet();
		 ArrayList<ProjectDTO> projectList = invocation.invoke(new GenericType<ArrayList<ProjectDTO>>() {});
		 assertNotNull(projectList);
	 }*/
	 
	 /*@Test
	// this test does not work as it is not possible to inject a security context with the grizley server
	 public void getProjectNames() {
		 Invocation invocation = target("project/names").request().buildGet();
		 ArrayList<ProjectNameDTO> projectList = invocation.invoke(new GenericType<ArrayList<ProjectNameDTO>>() {});
		 assertNotNull(projectList);
	 }*/
}
