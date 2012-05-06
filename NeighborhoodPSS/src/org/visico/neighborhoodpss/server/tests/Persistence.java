package org.visico.neighborhoodpss.server.tests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.hibernate.Session;
import org.junit.Test;
import org.visico.neighborhoodpss.server.HibernateUtil;
import org.visico.neighborhoodpss.server.Scenario;
import org.visico.neighborhoodpss.server.ScenarioServiceImpl;

public class Persistence {

	@Test
	public void test() 
	{
		
	}
	
	@Test
	public void persistSecnarios()
	{
		CaseProject casep = new CaseProject();
		ScenarioServiceImpl impl = new ScenarioServiceImpl();
		impl.saveScenarios(casep.getScenarios_parents_dto());
		
		impl.saveScenarios(casep.getScenarios_parents_dto());
		// TODO: build in junit db tests
	}
	

}
