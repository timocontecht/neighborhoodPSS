package org.visico.neighborhoodpss.shared;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProjectDTO  implements Cloneable, Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6872680811993685942L;

	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private Set<ScenarioDTO> parent_scenarios = new HashSet<ScenarioDTO>();
	private Set<UserDTO> users = new HashSet<UserDTO>();
	
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
	
	protected Object clone()
	{
		// do not clone id - id is assigned by a database
				// the clone should not have yet an id to signify 
				// that it is not yet in the db and has to created
				// instead of updated
		ProjectDTO child = new ProjectDTO();
		
		child.name = this.getName();
		
		child.latitude = this.getLatitude();
		child.longitude = this.getLongitude();
		
		Iterator<ScenarioDTO> sit = this.getParent_scenarios().iterator();
		while(sit.hasNext())
		{
			child.addParentScenario((ScenarioDTO) sit.next().clone());
		}
		
		Iterator<UserDTO> uit = this.getUsers().iterator();
		while(sit.hasNext())
		{
			child.addUser((UserDTO) uit.next().clone());
		}
		
		return child;
	}
}
