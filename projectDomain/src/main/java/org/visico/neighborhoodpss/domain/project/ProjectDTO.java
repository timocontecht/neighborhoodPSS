package org.visico.neighborhoodpss.domain.project;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gwt.user.client.rpc.IsSerializable;

public class ProjectDTO  implements Cloneable, IsSerializable
{


	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private Set<ScenarioDTO> parent_scenarios = new HashSet<ScenarioDTO>();
	private Set<UserDTO> users = new HashSet<UserDTO>();
	private Set<BuildingDataTypeDTO> buildingDataTypes = new HashSet<BuildingDataTypeDTO>();
	
	public ProjectDTO()
	{
		
	}
	
	public ProjectDTO (ProjectDTO toCopy)
	{
		// do not copy id - id is assigned by a database
		// the copy should not have yet an id to signify 
		// that it is not yet in the db and has to created
		// instead of updated
		this.name = toCopy.getName();
		
		this.latitude = toCopy.getLatitude();
		this.longitude = toCopy.getLongitude();
		
		Iterator<ScenarioDTO> sit = toCopy.getParent_scenarios().iterator();
		while(sit.hasNext())
		{
			this.addParentScenario(new ScenarioDTO(sit.next()));
		}
		
		Iterator<UserDTO> uit = toCopy.getUsers().iterator();
		while(sit.hasNext())
		{
			this.addUser( new UserDTO(uit.next()));
		}
		
		for (BuildingDataTypeDTO d : toCopy.getBuildingDataTypes() )
			this.getBuildingDataTypes().add(new BuildingDataTypeDTO(d));
	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public Set<ScenarioDTO> getParent_scenarios() {
		return parent_scenarios;
	}
	public void setParent_scenarios(Set<ScenarioDTO> parent_scenarios) {
		this.parent_scenarios = parent_scenarios;
	}
	
	public void addParentScenario(ScenarioDTO s)
	{
		this.parent_scenarios.add(s);
	}
	
	
	public Set<UserDTO> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDTO> users) {
		this.users = users;
	}
	public void addUser(UserDTO user)
	{
		this.users.add(user);
	}

	public Set<BuildingDataTypeDTO> getBuildingDataTypes() {
		return buildingDataTypes;
	}

	public void setBuildingDataTypes(Set<BuildingDataTypeDTO> buildingDataTypes) {
		this.buildingDataTypes = buildingDataTypes;
	}

	public BuildingDataTypeDTO getBuildingData(String typeName) throws Exception {
		for (BuildingDataTypeDTO type : buildingDataTypes) {
			if (type.getName().equals(typeName) )
				return type;
		}
		throw new Exception("Requested data type does not exist!");
	}

	public void synchronizeDataTypeIds() {
		
		for (ScenarioDTO pscenario : parent_scenarios)  {
			synchronizeDataTypes(pscenario);
		}
	}

	private void synchronizeDataTypes(ScenarioDTO scenario) {
		for (ScenarioDTO child : scenario.getChildren())
			synchronizeDataTypes(child);
		
		for (BuildingDTO building : scenario.getBuildingDTOs())  {
			for (BuildingDataTypeDTO buildingDataTypeObject : building.getData().keySet())  {
				for (BuildingDataTypeDTO typeRef : buildingDataTypes)  {
					if (typeRef.equals(buildingDataTypeObject))  {
						buildingDataTypeObject.setId(typeRef.getId());
					}
				}
			}
		}
			
	}

	public ScenarioDTO getScenarioByLabel(String name, String label) throws Exception {
		for (ScenarioDTO p : parent_scenarios)
		{
			if (p.getName().equals(name) == true)
				return getScenarioByLabelRecursive(p, label);
		}
			
		throw new Exception ("Scenario with label " + label + " does not exist!");
	}

	private ScenarioDTO getScenarioByLabelRecursive(ScenarioDTO scenario,
			String label)  {
		if (scenario.getLabel().equals(label))
			return scenario;
		
		for (ScenarioDTO child : scenario.getChildren())  {
			if ((child.getLabel().equals(label)))
				return child;
			else
				return getScenarioByLabelRecursive(child, label);
		}
		return null;
	}

	
}
