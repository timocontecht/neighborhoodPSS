package org.visico.neighborhoodpss.domain.project;


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
		
		Iterator<UserDTO> uit = this.getUsers().iterator();
		while(sit.hasNext())
		{
			this.addUser( new UserDTO(uit.next()));
		}
		
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
	
	
}
