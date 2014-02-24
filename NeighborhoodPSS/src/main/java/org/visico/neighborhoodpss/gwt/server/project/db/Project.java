package org.visico.neighborhoodpss.gwt.server.project.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.visico.neighborhoodpss.domain.project.BuildingDataTypeDTO;
import org.visico.neighborhoodpss.domain.project.ProjectDTO;
import org.visico.neighborhoodpss.domain.project.ScenarioDTO;
import org.visico.neighborhoodpss.domain.project.UserDTO;


@Entity
@Table(name="PROJECT")
public class Project implements Cloneable, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1996881128129978288L;
	
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private double latitude;
	@Column
	private double longitude;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="project_id")
	Set<Scenario> parentScenarios = new HashSet<Scenario>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	        name="PROJECT_USER",
	        joinColumns=@JoinColumn(name="project_id"),
	        inverseJoinColumns=@JoinColumn(name="user_id")
	    )
	Set<User> users = new HashSet<User>();
	
	@ManyToMany
	@JoinTable(
	        name="BUILDING_DATA_TYPE_PROJECT",
	        joinColumns=@JoinColumn(name="project_id"),
	        inverseJoinColumns=@JoinColumn(name="building_data_type_id")
	    )
	Set<BuildingDataType> buildingDataTypes = new HashSet<BuildingDataType>();
	
	@Transient
	private ProjectDTO dto_object = null;
	
	public Project()
	{
		
	}
	
	public Project(ProjectDTO dto)
	{
		this.dto_object = dto;
		this.id = dto.getId();
		this.name = dto.getName();
		this.latitude = dto.getLatitude();
		this.longitude = dto.getLongitude();
		
		Iterator<ScenarioDTO> it = this.dto_object.getParent_scenarios().iterator();
		while(it.hasNext())
		{
			this.addParentScenario( new Scenario(it.next()));
		}
		
		Iterator<UserDTO> uit = this.dto_object.getUsers().iterator();
		while(uit.hasNext())
		{
			this.addUser( new User(uit.next()));
		}
		
		for (BuildingDataTypeDTO type : this.dto_object.getBuildingDataTypes())
			this.buildingDataTypes.add(new BuildingDataType(type));
		
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

	

	public Set<Scenario> getParentScenarios() {
		return parentScenarios;
	}

	public void setParentScenarios(Set<Scenario> parentScenarios) {
		this.parentScenarios = parentScenarios;
	}

	public void addParentScenario(Scenario s)
	{
		this.parentScenarios.add(s);
	}
	
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void addUser(User u)
	{
		this.users.add(u);
	}
	
	public Set<BuildingDataType> getBuildingDataTypes() {
		return buildingDataTypes;
	}

	public void setBuildingDataTypes(Set<BuildingDataType> buildingDataTypes) {
		this.buildingDataTypes = buildingDataTypes;
	}

	public ProjectDTO getDto_object() {
		if (dto_object == null)
		{
			dto_object = new ProjectDTO();
			dto_object.setId(id);
			dto_object.setLatitude(latitude);
			dto_object.setLongitude(longitude);
			dto_object.setName(name);
			
			for (Scenario s : getParentScenarios())
			{
				dto_object.addParentScenario(s.getDto_object());
			}
			
			for (User u : getUsers())
			{
				dto_object.addUser(u.getDto_object());
			}
			
			for (BuildingDataType d : getBuildingDataTypes())
			{
				dto_object.getBuildingDataTypes().add(d.getDto_object());
			}
		}
		return dto_object;
	}

	public void setDto_object(ProjectDTO dto_object) {
		this.dto_object = dto_object;
	}
	
	public void update_dtoIds()
	{
		this.dto_object.setId(this.id);
		
		Iterator<Scenario> sit = this.getParentScenarios().iterator();
		while(sit.hasNext())
		{
			sit.next().update_dtoIds();
		}
		
		Iterator<User> uit = this.getUsers().iterator();
		while(uit.hasNext())
		{
			uit.next().update_dtoIds();
		}
		
		for (BuildingDataType d : buildingDataTypes)
			d.update_dtoIds();
		
		
	}
	
	public static ArrayList<ProjectDTO> getDTOList(ArrayList<Project> projects)
	{
		ArrayList<ProjectDTO> dtos = new ArrayList<ProjectDTO>();
		for (Project p : projects)
			dtos.add(p.getDto_object());
		return dtos;
			
	}
}
