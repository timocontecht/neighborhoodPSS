package org.visico.neighborhoodpss.server.tests;

import java.util.ArrayList;

import org.visico.neighborhoodpss.shared.ScenarioDTO;

import com.google.gwt.dev.util.collect.HashSet;

public class CaseProject 
{
	public CaseProject()
	{
		labels_dto = new ArrayList<String>();
		scenarios_parents_dto = new HashSet<ScenarioDTO>();
		all_scenarios_dto = new HashSet<ScenarioDTO>();
		
		ScenarioDTO parent1 = new ScenarioDTO("Parent 1");
		//parent1.setId(1);
		labels_dto.add(parent1.label());
		scenarios_parents_dto.add(parent1);
		
		ScenarioDTO parent2 = new ScenarioDTO("Parent 2");
		//parent2.setId(2);
		labels_dto.add(parent2.label());
		scenarios_parents_dto.add(parent2);
		all_scenarios_dto.addAll(scenarios_parents_dto);
		
		ScenarioDTO child1_parent1 = parent1.createChild();
		//child1_parent1.setId(3);
		labels_dto.add(child1_parent1.label());
		all_scenarios_dto.add(child1_parent1);
		
		ScenarioDTO child2_parent1 = parent1.createChild();
		//child2_parent1.setId(4);
		labels_dto.add(child2_parent1.label());
		all_scenarios_dto.add(child2_parent1);
		
		ScenarioDTO child_child1_parent1 = child1_parent1.createChild();
		labels_dto.add(child_child1_parent1.label());
		all_scenarios_dto.add(child_child1_parent1);
		//child_child1_parent1.setId(5);
		
		ScenarioDTO child_parent2 = parent2.createChild();
		//child_parent2.setId(6);
		labels_dto.add(child_parent2.label());
		all_scenarios_dto.add(child_parent2);
	}
	
	public ArrayList<String> getLabels_dto() {
		return labels_dto;
	}
	public void setLabels_dto(ArrayList<String> labels_dto) {
		this.labels_dto = labels_dto;
	}
	public HashSet<ScenarioDTO> getScenarios_parents_dto() {
		return scenarios_parents_dto;
	}
	public void setScenarios_parents_dto(HashSet<ScenarioDTO> scenarios_parents_dto) {
		this.scenarios_parents_dto = scenarios_parents_dto;
	}
	public HashSet<ScenarioDTO> getAll_scenarios_dto() {
		return all_scenarios_dto;
	}
	public void setAll_scenarios_dto(HashSet<ScenarioDTO> all_scenarios_dto) {
		this.all_scenarios_dto = all_scenarios_dto;
	}

	
	ArrayList<String> labels_dto;
	HashSet<ScenarioDTO> scenarios_parents_dto;
	HashSet<ScenarioDTO> all_scenarios_dto;
	
	
}
