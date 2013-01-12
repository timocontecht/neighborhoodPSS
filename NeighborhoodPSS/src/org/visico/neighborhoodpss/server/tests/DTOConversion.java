package org.visico.neighborhoodpss.server.tests;

import static org.junit.Assert.*;


import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.visico.neighborhoodpss.server.Scenario;
import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.dev.util.collect.HashSet;

public class DTOConversion {

	@Test
	public void test() 
	{
		
	}
	
	@Test
	public void scenarioConversion()
	{
		// create exemplary scenario tree with DTOs
		//         p1            p2
		//         |              |
		//    ----------          c1
		//    c1      c2
		//    |
		//    c1
		
		CaseProject casep = new CaseProject(1);
		
		// prepare the test, check the labels
		Iterator<ScenarioDTO> it = casep.getScenarios_parents_dto().iterator();
		
		Set<Scenario> result = new HashSet<Scenario>(); 
		while(it.hasNext())
		{
			result.addAll(Scenario.createFromParentDTO(it.next()));
		}
				
		// test result
		assertEquals(result.size(), casep.getAll_scenarios_dto().size());
		
	}

}
