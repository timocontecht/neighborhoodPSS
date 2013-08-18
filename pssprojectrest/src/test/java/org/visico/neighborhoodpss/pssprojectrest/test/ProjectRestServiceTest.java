package org.visico.neighborhoodpss.pssprojectrest.test;

import static org.junit.Assert.*;

import java.util.ArrayList;


import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.pssprojectrest.ProjectRestService;

public class ProjectRestServiceTest extends JerseyTest{
	
	 protected Application configure()
	 {
		 return new ResourceConfig(ProjectRestService.class);
	 }
	 
	 @Test
	 public void getProjectTest() {
		 Invocation invocation = target("project").request().header("id", "1")
				 .header("user", "Timo")
				 .header("pass", "timopass")
				 .buildGet();
		ProjectDTO responseMsg = invocation.invoke(ProjectDTO.class);
        assertNotNull(responseMsg);
        
	 }
	 
	 @Test
	 public void getProjectListTest() {
		 Invocation invocation = target("project/list").request()
				 .header("user", "Timo")
				 .header("pass", "timopass")
				 .buildGet();
		 ArrayList<ProjectDTO> projectList = invocation.invoke(new GenericType<ArrayList<ProjectDTO>>() {});
		 assertNotNull(projectList);
	 }
}
