package org.visico.neighborhoodpss.server.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.junit.Test;
import org.visico.neighborhoodpss.server.Building;
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
	
	@Test
	public void persistBuilding()
	{
		Set<Building> buildings = new HashSet<Building>();
		
		Building b = new Building();
		b.addVertex(0.0, 0.0);
		b.addVertex(1.0, 0.0);
		b.addVertex(1.0, 1.0);
		b.addVertex(0.0, 1.0);
		
		b.setType("Construction");
		
		buildings.add(b);
		
		ScenarioServiceImpl impl = new ScenarioServiceImpl();
		impl.persistBuildings(buildings);
		
	}
	

}
