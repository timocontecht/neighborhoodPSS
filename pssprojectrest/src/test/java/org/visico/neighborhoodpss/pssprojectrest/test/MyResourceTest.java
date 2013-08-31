package org.visico.neighborhoodpss.pssprojectrest.test;


import static org.junit.Assert.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.visico.neighborhoodpss.pssprojectrest.MyResource;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;


public class MyResourceTest extends JerseyTest{

	 private WebTarget target;
	 
	 protected Application configure()
	 {
		 return new ResourceConfig(MyResource.class);
	 }
	 
	 @Test
	 public void test() {
		String responseMsg = target("myresource").request().get(String.class);
        assertEquals("Got it new!", responseMsg);
	 }

}
